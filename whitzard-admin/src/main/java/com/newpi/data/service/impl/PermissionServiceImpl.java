package com.newpi.data.service.impl;

import com.google.common.collect.Lists;
import com.newpi.data.dao.PermissionDao;
import com.newpi.data.dao.RolePermissionDao;
import com.newpi.data.domain.PermissionDTO;
import com.newpi.data.entity.Permission;
import com.newpi.data.entity.RolePermission;
import com.newpi.data.enums.ResultCode;
import com.newpi.data.exception.WhitzardException;
import com.newpi.data.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/12/2 4:54 PM
 * @desc:
 */
@Slf4j
@Service
public class PermissionServiceImpl implements PermissionService {


    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Override
    public void add(PermissionDTO permissionDto) {
        Permission permission = new Permission();
        BeanUtils.copyProperties(permissionDto, permission);
        permission.setCreateTime(new Date());
        permissionDao.save(permission);
    }

    @Override
    public void delete(Integer id) {
        List<RolePermission> RolePermissions = rolePermissionDao.findAllByPermission(id);
        if (null != RolePermissions || !RolePermissions.isEmpty()) {
            throw new WhitzardException(ResultCode.PERMISSION_CANNOT_DELETED);
        }
        permissionDao.deleteById(id);
    }

    @Override
    public void update(PermissionDTO permissionDto) {

        Optional<Permission> permissionOptional = permissionDao.findById(permissionDto.getId());
        if (!permissionOptional.isPresent()) {
            throw new WhitzardException(ResultCode.PERMISSION_NOT_FOUND);
        }
        Permission permission = permissionOptional.get();
        BeanUtils.copyProperties(permissionDto, permission);
        permission.setUpdateTime(new Date());
        permissionDao.save(permission);
    }

    @Override
    public List<Permission> findAllByIds(List<Integer> ids) {
        List<Permission> permissionList = permissionDao.findByIds(ids);
        return permissionList;
    }

    @Override
    public PermissionDTO findById(Integer id) {
        Optional<Permission> permissionOptional = permissionDao.findById(id);
        if (!permissionOptional.isPresent()) {
            throw new WhitzardException(ResultCode.PERMISSION_NOT_FOUND);
        }
        PermissionDTO permissionDto = new PermissionDTO();
        BeanUtils.copyProperties(permissionOptional.get(), permissionDto);
        return permissionDto;
    }

    @Override
    public List<PermissionDTO> findAll() {
        Iterator<Permission> iterator = permissionDao.findAll().iterator();
        List<PermissionDTO> permissionDtoList = Lists.newArrayList();
        while (iterator.hasNext()) {
            PermissionDTO permissionDto = new PermissionDTO();
            BeanUtils.copyProperties(iterator.next(), permissionDto);
            permissionDtoList.add(permissionDto);
        }
        return permissionDtoList;
    }
}

