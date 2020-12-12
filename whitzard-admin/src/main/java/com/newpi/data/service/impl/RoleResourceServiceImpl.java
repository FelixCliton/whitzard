package com.newpi.data.service.impl;

import com.google.common.collect.Lists;
import com.newpi.data.dao.RoleResourceDao;
import com.newpi.data.entity.Resource;
import com.newpi.data.entity.RoleResource;
import com.newpi.data.service.ResourceService;
import com.newpi.data.service.RoleResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
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
public class RoleResourceServiceImpl implements RoleResourceService {

    @Autowired
    private RoleResourceDao roleResourceDao;

    @Autowired
    private ResourceService resourceService;


    @Override
    public List<RoleResource> findAllByRoles(List<Integer> roleIds) {
        return roleResourceDao.findAllByRoles(roleIds);
    }


    @Override
    public List<Resource> findResource(List<Integer> roleIds) {
        List<RoleResource> roleResources = findAllByRoles(roleIds);
        if (null == roleResources || roleResources.isEmpty()) {
            return Lists.newArrayList();
        }
        List<Integer> resourceIds = roleResources.stream().map(roleResource -> roleResource.getResourceId()).collect(Collectors.toList());
        return resourceService.findAllByIds(resourceIds);
    }

    @Override
    public void deleteByRoleId(Integer roleId) {
        roleResourceDao.deleteAllByRoleId(roleId);
    }

    @Override
    public void saveAll(List<RoleResource> roleResources) {
        roleResourceDao.saveAll(roleResources);
    }

    @Override
    public List<RoleResource> findAll() {
        List<RoleResource> list = Lists.newArrayList();
        Iterator<RoleResource> iterator = roleResourceDao.findAll().iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

}
