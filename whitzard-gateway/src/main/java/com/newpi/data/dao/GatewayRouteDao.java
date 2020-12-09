package com.newpi.data.dao;

import com.newpi.data.entity.GatewayRoute;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/11/22 11:55 PM
 * @desc:
 */
@Repository
public interface GatewayRouteDao extends CrudRepository<GatewayRoute, String> {

}
