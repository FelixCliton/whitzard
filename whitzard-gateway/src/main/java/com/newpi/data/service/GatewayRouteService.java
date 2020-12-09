package com.newpi.data.service;

import com.newpi.data.model.GatewayRouteDto;

import java.util.List;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/11/22 11:59 PM
 * @desc:
 */
public interface GatewayRouteService {

    /**
     * find all
     *
     * @return
     */
    List<GatewayRouteDto> findAll();

    /**
     * find by id
     *
     * @param id
     *
     * @return
     */
    GatewayRouteDto findById(String id);

    /**
     * add route
     *
     * @param route
     *
     * @return
     */
    void add(GatewayRouteDto route);


    /**
     * delete route
     *
     * @param id
     */
    void delete(String id);

    /**
     * update route
     *
     * @param route
     *
     * @return
     */
    void updateRoute(GatewayRouteDto route);
}
