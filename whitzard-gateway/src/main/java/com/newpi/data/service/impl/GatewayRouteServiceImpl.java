package com.newpi.data.service.impl;

import com.google.common.collect.Lists;
import com.newpi.data.dao.GatewayRouteDao;
import com.newpi.data.entity.GatewayRoute;
import com.newpi.data.model.GatewayRouteDto;
import com.newpi.data.service.GatewayRouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/11/23 9:36 AM
 * @desc:
 */
@Service
@Slf4j
public class GatewayRouteServiceImpl implements GatewayRouteService {

    @Autowired
    private GatewayRouteDao routeDao;

    @Override
    public List<GatewayRouteDto> findAll() {
        Iterator<GatewayRoute> iterator = routeDao.findAll().iterator();
        List<GatewayRouteDto> routeDtoList = Lists.newArrayList();
        while (iterator.hasNext()) {
            GatewayRoute route = iterator.next();
            GatewayRouteDto routeDto = new GatewayRouteDto();
            BeanUtils.copyProperties(route, routeDto);
            routeDtoList.add(routeDto);
        }
        return routeDtoList;
    }

    @Override
    public GatewayRouteDto findById(String id) {
        Optional<GatewayRoute> optional = routeDao.findById(id);
        if (!optional.isPresent()) {
            return null;
        }
        GatewayRoute route = optional.get();
        GatewayRouteDto routeDto = new GatewayRouteDto();
        BeanUtils.copyProperties(route, routeDto);
        return routeDto;
    }

    @Override
    public void add(GatewayRouteDto route) {
        GatewayRoute gatewayRoute = new GatewayRoute();
        BeanUtils.copyProperties(route, gatewayRoute);
        gatewayRoute.setAddTime(new Date());
        routeDao.save(gatewayRoute);
    }

    @Override
    public void delete(String id) {
        routeDao.deleteById(id);
    }

    @Override
    public void updateRoute(GatewayRouteDto route) {
        GatewayRoute gatewayRoute = new GatewayRoute();
        BeanUtils.copyProperties(route, gatewayRoute);
        routeDao.save(gatewayRoute);
    }
}
