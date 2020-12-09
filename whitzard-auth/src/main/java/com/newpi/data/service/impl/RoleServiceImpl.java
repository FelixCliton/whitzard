package com.newpi.data.service.impl;

import com.google.common.collect.Lists;
import com.newpi.data.dao.RoleDao;
import com.newpi.data.dao.UserRoleDao;
import com.newpi.data.entity.Role;
import com.newpi.data.entity.UserRole;
import com.newpi.data.enums.ResultCode;
import com.newpi.data.exception.WhitzardException;
import com.newpi.data.model.RoleDTO;
import com.newpi.data.service.RoleService;
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
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public void add(RoleDTO roleDto) {
        Role role = new Role();
        BeanUtils.copyProperties(roleDto, role);
        role.setCreateTime(new Date());
        roleDao.save(role);
    }

    @Override
    public void delete(Integer id) {
        List<UserRole> userRoles = userRoleDao.findAllByRole(id);
        if (null != userRoles || !userRoles.isEmpty()) {
            throw new WhitzardException(ResultCode.ROLE_CANNOT_DELETED);
        }
        roleDao.deleteById(id);
    }

    @Override
    public void update(RoleDTO roleDto) {

        Optional<Role> roleOptional = roleDao.findById(roleDto.getId());
        if (!roleOptional.isPresent()) {
            throw new WhitzardException(ResultCode.ROLE_NOT_FOUND);
        }
        Role role = roleOptional.get();
        BeanUtils.copyProperties(roleDto, role);
        role.setUpdateTime(new Date());
        roleDao.save(role);
    }

    @Override
    public List<RoleDTO> findByIds(List<Integer> ids) {

        List<Role> roleList = roleDao.findByIds(ids);
        List<RoleDTO> roleDtoList = Lists.newArrayList();
        for (Role role : roleList) {
            RoleDTO roleDto = new RoleDTO();
            BeanUtils.copyProperties(role, roleDto);
            roleDtoList.add(roleDto);
        }
        return roleDtoList;
    }

    @Override
    public RoleDTO findById(Integer id) {
        Optional<Role> roleOptional = roleDao.findById(id);
        if (!roleOptional.isPresent()) {
            throw new WhitzardException(ResultCode.ROLE_NOT_FOUND);
        }
        RoleDTO roleDto = new RoleDTO();
        BeanUtils.copyProperties(roleOptional.get(), roleDto);
        return roleDto;
    }

    @Override
    public List<RoleDTO> findAll() {
        Iterator<Role> iterator = roleDao.findAll().iterator();
        List<RoleDTO> roleDtoList = Lists.newArrayList();
        while (iterator.hasNext()) {
            RoleDTO roleDto = new RoleDTO();
            BeanUtils.copyProperties(iterator.next(), roleDto);
            roleDtoList.add(roleDto);
        }
        return roleDtoList;
    }
}

