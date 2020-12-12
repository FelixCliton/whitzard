package com.newpi.data.service;

import com.newpi.data.entity.Menu;
import com.newpi.data.entity.RoleMenu;

import java.util.List;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/12/11 10:52 AM
 * @desc:
 */
public interface RoleMenuService {

    /**
     * find all by role Ids
     *
     * @param roleIds
     *
     * @return
     */
    List<RoleMenu> findAllByRoles(List<Integer> roleIds);


    /**
     * find menus
     *
     * @param roleIds
     *
     * @return
     */
    List<Menu> findMenu(List<Integer> roleIds);

    /**
     * delete by roleId
     *
     * @param roleId
     */
    void deleteByRoleId(Integer roleId);


    /**
     * save all
     *
     * @param roleMenuList
     */
    void saveAll(List<RoleMenu> roleMenuList);
}
