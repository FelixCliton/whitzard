package com.newpi.data.service.impl;

import com.newpi.data.dao.UserRoleDao;
import com.newpi.data.entity.UserRole;
import com.newpi.data.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/12/2 4:54 PM
 * @desc:
 */
@Slf4j
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public List<UserRole> findAllByUser(Integer userId) {
        return userRoleDao.findAllByUser(userId);
    }

    @Override
    public List<UserRole> findAllByRole(Integer roleId) {
        return userRoleDao.findAllByRole(roleId);
    }

    @Override
    public void add(UserRole userRole) {
        userRoleDao.save(userRole);
    }

    @Override
    public void delete(Integer id) {
        userRoleDao.deleteById(id);
    }

    @Override
    public void deleteByUserAndRole(Integer userId, Integer roleId) {
        userRoleDao.deleteByUserAndRole(userId, roleId);
    }

    @Override
    public void update(UserRole userRole) {
        userRoleDao.save(userRole);
    }
}

