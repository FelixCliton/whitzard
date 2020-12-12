package com.newpi.data.service;

import com.newpi.data.entity.Resource;
import com.newpi.data.entity.RoleResource;

import java.util.List;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/12/11 10:52 AM
 * @desc:
 */
public interface RoleResourceService {

    /**
     * find all by role Ids
     *
     * @param roleIds
     *
     * @return
     */
    List<RoleResource> findAllByRoles(List<Integer> roleIds);


    /**
     * find resource by roleIds
     *
     * @param roleIds
     *
     * @return
     */
    List<Resource> findResource(List<Integer> roleIds);

    /**
     * delete by roleId
     *
     * @param roleId
     */
    void deleteByRoleId(Integer roleId);

    /**
     * save all
     *
     * @param roleResources
     */
    void saveAll(List<RoleResource> roleResources);

    /**
     * find all
     *
     * @return
     */
    List<RoleResource> findAll();
}
