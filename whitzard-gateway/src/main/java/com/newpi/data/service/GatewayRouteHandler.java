package com.newpi.data.service;

import com.google.common.collect.Lists;
import com.newpi.data.dao.GatewayRouteDao;
import com.newpi.data.entity.GatewayRoute;
import com.newpi.data.enums.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Iterator;
import java.util.List;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/11/23 10:58 AM
 * @desc:
 */
@Service
@Slf4j
public class GatewayRouteHandler implements ApplicationEventPublisherAware, CommandLineRunner {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedisRouteDefinitionRepository routeDefinitionRepository;

    private ApplicationEventPublisher publisher;

    @Autowired
    private GatewayRouteDao gatewayRouteDao;

    @Override
    public void run(String... args) throws Exception {
        loadRouteConfig();
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public ResultCode loadRouteConfig() {
        log.info("====开始加载=====网关配置信息=========");
        //删除redis里面的路由配置信息
        redisTemplate.delete(RedisRouteDefinitionRepository.GATEWAY_ROUTES);

        //从数据库拿到基本路由配置
        Iterator<GatewayRoute> iterator = gatewayRouteDao.findAll().iterator();
        while (iterator.hasNext()) {
            GatewayRoute gatewayRoute = iterator.next();
            RouteDefinition definition = handleData(gatewayRoute);
            routeDefinitionRepository.save(Mono.just(definition)).subscribe();
        }

        this.publisher.publishEvent(new RefreshRoutesEvent(this));
        log.info("=======网关配置信息===加载完成======");

        return ResultCode.SUCCESS;
    }


    public void saveRoute(GatewayRoute gatewayRoute) {
        RouteDefinition definition = handleData(gatewayRoute);
        routeDefinitionRepository.save(Mono.just(definition)).subscribe();
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
    }

    public void update(GatewayRoute gatewayRoute) {
        RouteDefinition definition = handleData(gatewayRoute);
        try {
            this.routeDefinitionRepository.delete(Mono.just(definition.getId()));
            routeDefinitionRepository.save(Mono.just(definition)).subscribe();
            this.publisher.publishEvent(new RefreshRoutesEvent(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void deleteRoute(String routeId) {
        routeDefinitionRepository.delete(Mono.just(routeId)).subscribe();
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
    }

    /**
     * 路由数据转换公共方法
     *
     * @param gatewayRoute
     *
     * @return
     */
    public RouteDefinition handleData(GatewayRoute gatewayRoute) {
        RouteDefinition definition = new RouteDefinition();
        URI uri = null;
        if (gatewayRoute.getUri().startsWith("http")) {
            //http地址
            uri = UriComponentsBuilder.fromHttpUrl(gatewayRoute.getUri()).build().toUri();
        } else {
            //注册中心
            uri = UriComponentsBuilder.fromUriString(gatewayRoute.getUri()).build().toUri();
        }

        definition.setId(gatewayRoute.getId());
        definition.setUri(uri);

        List<PredicateDefinition> predicates = Lists.newArrayList();
        for (String predicate : gatewayRoute.getPredicates()) {
            PredicateDefinition predicateDefinition = new PredicateDefinition(predicate);
            predicates.add(predicateDefinition);
        }

        List<FilterDefinition> filters = Lists.newArrayList();
        for (String filter : gatewayRoute.getFilters()) {
            FilterDefinition filterDefinition = new FilterDefinition(filter);
            filters.add(filterDefinition);
        }

        definition.setPredicates(predicates);
        definition.setFilters(filters);
        definition.setOrder(gatewayRoute.getRouteOrder());

        return definition;
    }


}
