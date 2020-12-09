package com.newpi.data.controller;

import com.newpi.data.entity.GatewayRoute;
import com.newpi.data.enums.ResultCode;
import com.newpi.data.model.GatewayRouteDto;
import com.newpi.data.model.ResultEntity;
import com.newpi.data.service.GatewayRouteHandler;
import com.newpi.data.service.GatewayRouteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/11/25 8:47 PM
 * @desc:
 */
@RestController
@RequestMapping(value = "gateway")
public class GatewayRouteController {

    @Autowired
    private GatewayRouteService routeService;

    @Autowired
    private GatewayRouteHandler gatewayRouteHandler;

    @PostMapping(value = "add")
    public ResultEntity addRoute(@RequestBody @Valid GatewayRouteDto route) {
        routeService.add(route);
        GatewayRoute gatewayRoute = new GatewayRoute();
        BeanUtils.copyProperties(route, gatewayRoute);
        gatewayRouteHandler.saveRoute(gatewayRoute);
        return new ResultEntity();
    }

    @DeleteMapping(value = "delete/{id}")
    public ResultEntity delete(@PathVariable String id) {
        routeService.delete(id);
        gatewayRouteHandler.deleteRoute(id);
        return new ResultEntity();
    }

    @PutMapping(value = "put")
    public ResultEntity update(@RequestBody @Valid GatewayRouteDto route) {
        routeService.updateRoute(route);
        GatewayRoute gatewayRoute = new GatewayRoute();
        BeanUtils.copyProperties(route, gatewayRoute);
        gatewayRouteHandler.update(gatewayRoute);
        return new ResultEntity();
    }

    @GetMapping(value = "list")
    public ResultEntity<List> list() {
        return new ResultEntity<>(routeService.findAll());
    }

    @GetMapping(value = "get/{id}")
    public ResultEntity findById(@PathVariable String id) {
        return new ResultEntity(routeService.findById(id));
    }

    @GetMapping(value = "refresh")
    public ResultEntity refresh() {
        ResultCode resultCode = gatewayRouteHandler.loadRouteConfig();
        return new ResultEntity(resultCode);
    }
}
