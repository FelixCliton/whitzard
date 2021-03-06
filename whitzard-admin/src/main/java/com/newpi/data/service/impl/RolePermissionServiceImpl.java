package com.newpi.data.service.impl;

import com.google.common.collect.Lists;
import com.newpi.data.dao.RolePermissionDao;
import com.newpi.data.domain.RolePermissionBindDTO;
import com.newpi.data.entity.Permission;
import com.newpi.data.entity.RolePermission;
import com.newpi.data.service.MenuService;
import com.newpi.data.service.PermissionService;
import com.newpi.data.service.RolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/12/2 4:54 PM
 * @desc:
 */
@Slf4j
@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Autowired
    private PermissionService permissionService;

    @Override
    public List<RolePermission> findAllByRole(Integer roleId) {
        return rolePermissionDao.findAllByRole(roleId);
    }

    @Override
    public List<RolePermission> findAllByRoles(List<Integer> roleIds) {
        return rolePermissionDao.findAllByRoles(roleIds);
    }

    @Override
    public List<RolePermission> findAllByPermission(Integer permissionId) {
        return rolePermissionDao.findAllByPermission(permissionId);
    }

    @Override
    public void add(RolePermission rolePermission) {
        rolePermissionDao.save(rolePermission);
    }

    @Override
    public void delete(Integer id) {
        rolePermissionDao.deleteById(id);
    }

    @Override
    public void update(RolePermission rolePermission) {
        rolePermissionDao.save(rolePermission);
    }

    @Override
    public void bindPermission(RolePermissionBindDTO permissionBindDto) {
        List<RolePermission> rolePermissionList = Lists.newArrayList();
        for (Integer permissionId : permissionBindDto.getPermissionIds()) {
            RolePermission rolePermission = new RolePermission()
                    .setRoleId(permissionBindDto.getRoleId())
                    .setPermissionId(permissionId);
            rolePermissionList.add(rolePermission);
        }
        rolePermissionDao.saveAll(rolePermissionList);
    }

    @Override
    public void unbindPermission(RolePermissionBindDTO permissionBindDto) {
        rolePermissionDao.deleteByRoleIdAndPermissionIds(permissionBindDto.getRoleId(), permissionBindDto.getPermissionIds());
    }

    @Override
    public List<Permission> findPermission(List<Integer> roleIds) {
        List<RolePermission> rolePermissions = findAllByRoles(roleIds);
        if (null == rolePermissions || rolePermissions.isEmpty()) {
            return Lists.newArrayList();
        }
        List<Integer> permissionIds = rolePermissions.stream().map(rolePermission -> rolePermission.getPermissionId()).collect(Collectors.toList());
        return permissionService.findAllByIds(permissionIds);
    }
}

