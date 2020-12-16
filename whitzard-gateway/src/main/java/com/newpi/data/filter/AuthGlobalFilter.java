package com.newpi.data.filter;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.newpi.data.constant.AuthConstant;
import com.nimbusds.jose.JWSObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.text.ParseException;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/11/29 7:33 PM
 * @desc:
 */
@Component
@Slf4j
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    private static final String EMAIL_HEADER = "email";
    private static final String USER_NAME_HEADER = "username";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst(AuthConstant.JWT_TOKEN_HEADER);
        if (Strings.isNullOrEmpty(token)) {
            return chain.filter(exchange);
        }
        try {
            //从token中解析用户信息并设置到Header中去
            String realToken = token.replace(AuthConstant.JWT_TOKEN_PREFIX, "");
            JWSObject jwsObject = JWSObject.parse(realToken);
            String userStr = jwsObject.getPayload().toString();
            String email = JSONObject.parseObject(userStr).getString(EMAIL_HEADER);
            String username = JSONObject.parseObject(userStr).getString(EMAIL_HEADER);
            ServerHttpRequest request = exchange.getRequest().mutate()
                    .header(EMAIL_HEADER, email)
                    .header(USER_NAME_HEADER, username)
                    .build();
            exchange = exchange.mutate().request(request).build();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
