package com.newpi.data.service;

import com.newpi.data.entity.UserRole;

import java.util.List;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/12/2 5:00 PM
 * @desc:
 */
public interface UserRoleService {

    /**
     * find all by user
     *
     * @param userId
     *
     * @return
     */
    List<UserRole> findAllByUser(Integer userId);


    /**
     * find all by role
     *
     * @param roleId
     *
     * @return
     */
    List<UserRole> findAllByRole(Integer roleId);


    /**
     * add
     *
     * @param userRole
     */
    void add(UserRole userRole);

    /**
     * delete
     *
     * @param id
     */
    void delete(Integer id);

    /**
     * delete by userId and roleId
     * @param userId
     * @param roleId
     */
    void deleteByUserAndRole(Integer userId,Integer roleId);

    /**
     * update
     *
     * @param userRole
     */
    void update(UserRole userRole);
}
