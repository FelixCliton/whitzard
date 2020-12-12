package com.newpi.data.service;


import com.newpi.data.domain.UpdatePasswordParam;
import com.newpi.data.domain.UserDTO;
import com.newpi.data.domain.UserParam;
import com.newpi.data.entity.*;
import com.newpi.data.model.ResultEntity;

import java.util.List;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/12/2 4:17 PM
 * @desc:
 */
public interface UserService {

    /**
     * add
     *
     * @param user
     */
    void add(UserParam user);


    /**
     * delete
     *
     * @param id
     */
    void delete(Integer id);


    /**
     * update
     *
     * @param user
     */
    void update(User user);


    /**
     * find by username
     *
     * @param username
     *
     * @return
     */
    User findByUsername(String username);

    /**
     * find user by name
     *
     * @param id
     *
     * @return
     */
    User findById(Integer id);

    /**
     * find all
     *
     * @return
     */
    List<User> findAll();


    /**
     * find role list
     *
     * @param userId
     *
     * @return
     */
    List<Role> findRoleList(Integer userId);


    /**
     * find resource list
     *
     * @param userId
     *
     * @return
     */
    List<Resource> findResourceList(Integer userId);


    /**
     * find permission list
     *
     * @param userId
     *
     * @return
     */
    List<Permission> findPermissionList(Integer userId);


    /**
     * find menu list
     *
     * @param userId
     *
     * @return
     */
    List<Menu> findMenuList(Integer userId);

    /**
     * 登陆
     *
     * @param username
     * @param password
     *
     * @return
     */
    ResultEntity login(String username, String password);

    /**
     * 获取当前登陆用户
     *
     * @return
     */
    User findCurrentUser();


    /**
     * 修改密码
     *
     * @param passwordParam
     *
     * @return
     */
    User updatePassword(UpdatePasswordParam passwordParam);

    /**
     * load user by username
     *
     * @param username
     *
     * @return
     */
    UserDTO loadUserByUsername(String username);

    /**
     * bind role
     *
     * @param userId
     * @param roleIds
     */
    void bindRole(Integer userId, List<Integer> roleIds);
}
