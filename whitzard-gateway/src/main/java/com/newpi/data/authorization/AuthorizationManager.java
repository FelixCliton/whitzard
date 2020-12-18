package com.newpi.data.authorization;

import cn.hutool.core.convert.Convert;
import com.google.common.collect.Lists;
import com.newpi.data.constant.AuthConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import reactor.core.publisher.Mono;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.newpi.data.constant.AuthConstant.RESOURCE_ROLES_MAP_KEY;

/**
 * 鉴权管理器，用于判断是否有资源的访问权限
 *
 * @author macro
 * @date 2020/6/19
 */
@Component
@Slf4j
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {

        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        // 1. 对应跨域的预检请求直接放行
        if (request.getMethod() == HttpMethod.OPTIONS) {
            return Mono.just(new AuthorizationDecision(true));
        }

        // 2. token为空拒绝访问
        String token = request.getHeaders().getFirst(AuthConstant.JWT_TOKEN_HEADER);
        if (Strings.isNotEmpty(token)) {
            return Mono.just(new AuthorizationDecision(false));
        }


        // 3.缓存取资源权限角色关系列表
        Map<Object, Object> resourceRolesMap = redisTemplate.opsForHash().entries(RESOURCE_ROLES_MAP_KEY);
        Iterator<Object> iterator = resourceRolesMap.keySet().iterator();

        // 4.请求路径匹配到的资源需要的角色权限集合authorities统计
        PathMatcher pathMatcher = new AntPathMatcher();
        List<String> authorities = Lists.newArrayList();
        while (iterator.hasNext()) {
            String pattern = (String) iterator.next();
            if (pathMatcher.match(pattern, request.getURI().getPath())) {
                authorities.addAll(Convert.toList(String.class, resourceRolesMap.get(pattern)));
            }
        }

        Mono<AuthorizationDecision> authorizationDecisionMono = mono
                .filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(role -> {
                    // 5. roleId是请求用户的角色(格式:ROLE_{roleId})，authorities是请求资源所需要角色的集合
                    log.info("访问路径：{}", request.getURI().getPath());
                    log.info("用户角色：{}", role);
                    log.info("资源需要权限：{}", authorities);
                    return authorities.contains(role);
                })
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(false));
        return authorizationDecisionMono;
    }

}
