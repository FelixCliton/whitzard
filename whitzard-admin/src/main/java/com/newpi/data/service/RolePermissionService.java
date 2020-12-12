package com.newpi.data.service;

import com.newpi.data.domain.RolePermissionBindDTO;
import com.newpi.data.entity.Permission;
import com.newpi.data.entity.RolePermission;

import java.util.List;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/12/2 5:05 PM
 * @desc:
 */
public interface RolePermissionService {

    /**
     * find all by role
     *
     * @param roleId
     *
     * @return
     */
    List<RolePermission> findAllByRole(Integer roleId);


    /**
     * find all by role
     *
     * @param roleIds
     *
     * @return
     */
    List<RolePermission> findAllByRoles(List<Integer> roleIds);

    /**
     * find all by Permission
     *
     * @param permissionId
     *
     * @return
     */
    List<RolePermission> findAllByPermission(Integer permissionId);

    /**
     * add
     *
     * @param rolePermission
     */
    void add(RolePermission rolePermission);

    /**
     * delete
     *
     * @param id
     */
    void delete(Integer id);

    /**
     * update
     *
     * @param rolePermission
     */
    void update(RolePermission rolePermission);

    /**
     * bind permission
     *
     * @param permissionBindDto
     */
    void bindPermission(RolePermissionBindDTO permissionBindDto);


    /**
     * unbind permission
     *
     * @param permissionBindDto
     */
    void unbindPermission(RolePermissionBindDTO permissionBindDto);


    /**
     * find permission
     *
     * @param roleIds
     *
     * @return
     */
    List<Permission> findPermission(List<Integer> roleIds);
}
