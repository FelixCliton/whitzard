package com.newpi.data.config;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.newpi.data.dao.GatewayRouteDao;
import com.newpi.data.entity.GatewayRoute;
import com.newpi.data.service.GatewayRouteHandler;
import com.newpi.data.service.RedisRouteDefinitionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/11/26 7:31 PM
 * @desc:
 */
@Component
@Primary
@AllArgsConstructor
public class GatewaySwaggerProvider implements SwaggerResourcesProvider {

    private RouteLocator routeLocator;

    private GatewayProperties gatewayProperties;

    @Autowired
    private GatewayRouteDao gatewayRouteDao;

    @Autowired
    private GatewayRouteHandler routeHandler;

    @Override
    public List<SwaggerResource> get() {

        List<SwaggerResource> resources = Lists.newArrayList();
        Set<String> routes = Sets.newHashSet();

        routeLocator.getRoutes().subscribe(route -> routes.add(route.getId()));

        List<RouteDefinition> routeDefinitionList = Lists.newArrayList();
        routeDefinitionList.addAll(gatewayProperties.getRoutes());

        Iterator<GatewayRoute> iterator = gatewayRouteDao.findAll().iterator();
        while (iterator.hasNext()) {
            GatewayRoute gatewayRoute = iterator.next();
            RouteDefinition definition = routeHandler.handleData(gatewayRoute);
            routeDefinitionList.add(definition);
        }

        routeDefinitionList.stream()
                .filter(routeDefinition -> routes.contains(routeDefinition.getId()))
                .forEach(
                        routeDefinition -> {
                            routeDefinition.getPredicates().stream()
                                    .filter(predicateDefinition -> "Path".equalsIgnoreCase(predicateDefinition.getName()))
                                    .forEach(predicateDefinition -> resources.add(swaggerResource(routeDefinition.getId(),
                                            predicateDefinition.getArgs().get(NameUtils.GENERATED_NAME_PREFIX + "0").replace("**", "v2/api-docs"))));
                        });

        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }
}
