package com.newpi.data.dao;

import com.newpi.data.entity.RolePermission;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/12/2 4:11 PM
 * @desc:
 */
@Repository
public interface RolePermissionDao extends CrudRepository<RolePermission, Integer> {

    /**
     * find all by roleId
     *
     * @param roleId
     *
     * @return
     */
    @Query("select c from RolePermission c where c.roleId = ?1")
    List<RolePermission> findAllByRole(Integer roleId);


    /**
     * find all by roleIds
     *
     * @param roleIds
     *
     * @return
     */
    @Query("select c from RolePermission c where c.roleId in ?1")
    List<RolePermission> findAllByRoles(List<Integer> roleIds);


    /**
     * find all by permissionId
     *
     * @param permissionId
     *
     * @return
     */
    @Query("select c from RolePermission c where c.permissionId = ?1")
    List<RolePermission> findAllByPermission(Integer permissionId);


    /**
     * delete by roleId and permissionIds
     *
     * @param roleId
     * @param permissionIds
     */
    @Query("delete from RolePermission c where c.roleId = ?1 and c.permissionId in ?2")
    void deleteByRoleIdAndPermissionIds(Integer roleId, List<Integer> permissionIds);
}
