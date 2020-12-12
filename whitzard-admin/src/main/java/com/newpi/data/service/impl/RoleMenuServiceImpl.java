package com.newpi.data.service.impl;

import com.google.common.collect.Lists;
import com.newpi.data.dao.RoleMenuDao;
import com.newpi.data.entity.Menu;
import com.newpi.data.entity.RoleMenu;
import com.newpi.data.service.MenuService;
import com.newpi.data.service.RoleMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/12/11 10:55 AM
 * @desc:
 */
@Service
@Slf4j
public class RoleMenuServiceImpl implements RoleMenuService {

    @Autowired
    private RoleMenuDao roleMenuDao;

    @Autowired
    private MenuService menuService;

    @Override
    public List<RoleMenu> findAllByRoles(List<Integer> roleIds) {
        return roleMenuDao.findAllByRoles(roleIds);
    }


    @Override
    public List<Menu> findMenu(List<Integer> roleIds) {
        List<RoleMenu> roleMenus = findAllByRoles(roleIds);
        if (null == roleMenus || roleMenus.isEmpty()) {
            return Lists.newArrayList();
        }
        List<Integer> menuIds = roleMenus.stream().map(roleMenu -> roleMenu.getMenuId()).collect(Collectors.toList());
        return menuService.findAllByIds(menuIds);
    }

    @Override
    public void deleteByRoleId(Integer roleId) {
        roleMenuDao.deleteAllByRoleId(roleId);
    }

    @Override
    public void saveAll(List<RoleMenu> roleMenuList) {
        roleMenuDao.saveAll(roleMenuList);
    }
}
