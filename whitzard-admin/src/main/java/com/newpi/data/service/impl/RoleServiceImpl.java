package com.newpi.data.service.impl;

import com.google.common.collect.Lists;
import com.newpi.data.constant.AuthConstant;
import com.newpi.data.dao.RoleDao;
import com.newpi.data.dao.UserRoleDao;
import com.newpi.data.entity.*;
import com.newpi.data.enums.ResultCode;
import com.newpi.data.exception.WhitzardException;
import com.newpi.data.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/12/2 4:54 PM
 * @desc:
 */
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private RoleResourceService roleResourceService;

    @Autowired
    private RoleMenuService roleMenuService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private ResourceService resourceService;

    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void add(Role role) {
        role.setCreateTime(new Date());
        roleDao.save(role);
    }

    @Override
    public List<Role> findRoleList(Integer userId) {
        List<UserRole> userRoles = userRoleDao.findAllByUser(userId);
        if (null == userRoles || userRoles.isEmpty()) {
            return Lists.newArrayList();
        }
        List<Integer> roleIds = userRoles.stream().map(userRole -> userRole.getRoleId()).collect(Collectors.toList());
        return roleDao.findByIds(roleIds);
    }

    @Override
    public void delete(Integer id) {
        List<UserRole> userRoles = userRoleDao.findAllByRole(id);
        if (null != userRoles || !userRoles.isEmpty()) {
            throw new WhitzardException(ResultCode.ROLE_CANNOT_DELETED);
        }
        roleDao.deleteById(id);
        initResourceRolesMap();
    }

    @Override
    public void update(Role role) {

        Optional<Role> roleOptional = roleDao.findById(role.getId());
        if (!roleOptional.isPresent()) {
            throw new WhitzardException(ResultCode.ROLE_NOT_FOUND);
        }
        role.setUpdateTime(new Date());
        roleDao.save(role);
    }

    @Override
    public List<Role> findByIds(List<Integer> ids) {

        List<Role> roleList = roleDao.findByIds(ids);
        return roleList;
    }

    @Override
    public Role findById(Integer id) {
        Optional<Role> roleOptional = roleDao.findById(id);
        if (roleOptional.isPresent()) {
            return roleOptional.get();
        }
        return null;
    }

    @Override
    public List<Role> findAll() {
        Iterator<Role> iterator = roleDao.findAll().iterator();
        List<Role> roleList = Lists.newArrayList();
        while (iterator.hasNext()) {
            roleList.add(iterator.next());
        }
        return roleList;
    }

    @Override
    public void bindRole(Integer userId, List<Integer> roleIds) {

        userRoleDao.deleteAllByUserId(userId);
        //建立新关系
        if (null != roleIds && !roleIds.isEmpty()) {
            List<UserRole> list = Lists.newArrayList();
            for (Integer roleId : roleIds) {
                UserRole userRole = new UserRole()
                        .setUserId(userId)
                        .setRoleId(roleId);

                list.add(userRole);
            }
            userRoleDao.saveAll(list);
        }
    }


    @Override
    public List<Resource> findResource(List<Integer> roleIds) {
        return roleResourceService.findResource(roleIds);
    }

    @Override
    public List<Permission> findPermission(List<Integer> roleIds) {
        return rolePermissionService.findPermission(roleIds);
    }

    @Override
    public List<Menu> findMenu(List<Integer> roleIds) {
        return roleMenuService.findMenu(roleIds);
    }

    @Override
    public void allocateMenu(Integer roleId, List<Integer> menuIds) {
        roleMenuService.deleteByRoleId(roleId);
        List<RoleMenu> roleMenus = Lists.newArrayList();
        for (Integer menuId : menuIds) {

            RoleMenu roleMenu = new RoleMenu()
                    .setRoleId(roleId)
                    .setMenuId(menuId);
            roleMenus.add(roleMenu);
        }
        roleMenuService.saveAll(roleMenus);
    }

    @Override
    public void allocateResource(Integer roleId, List<Integer> resourceIds) {
        roleResourceService.deleteByRoleId(roleId);
        List<RoleResource> roleResources = Lists.newArrayList();
        for (Integer resourceId : resourceIds) {

            RoleResource roleResource = new RoleResource()
                    .setRoleId(roleId)
                    .setResourceId(resourceId);
            roleResources.add(roleResource);
        }
        roleResourceService.saveAll(roleResources);
        initResourceRolesMap();
    }

    @Override
    public void initResourceRolesMap() {

        Map<String, List<String>> resourceRoleMap = new TreeMap<>();
        List<Resource> resourceList = resourceService.findAll();
        List<Role> roleList = findAll();
        List<RoleResource> relationList = roleResourceService.findAll();
        for (Resource resource : resourceList) {
            Set<Integer> roleIds = relationList.stream().filter(item -> item.getResourceId().equals(resource.getId())).map(RoleResource::getRoleId).collect(Collectors.toSet());
            List<String> roleNames = roleList.stream().filter(item -> roleIds.contains(item.getId())).map(item -> item.getEnName()).collect(Collectors.toList());
            resourceRoleMap.put("/" + applicationName + resource.getUrl(), roleNames);
        }
        redisTemplate.delete(AuthConstant.RESOURCE_ROLES_MAP_KEY);
        redisTemplate.opsForHash().putAll(AuthConstant.RESOURCE_ROLES_MAP_KEY, resourceRoleMap);
    }


}

