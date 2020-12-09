package com.newpi.data.service;

import com.newpi.data.model.UserDTO;

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
    void add(UserDTO user);


    /**
     * delete
     *
     * @param id
     */
    void delete(Integer id);


    /**
     * update
     *
     * @param userDto
     */
    void update(UserDTO userDto);


    /**
     * find by email
     *
     * @param email
     *
     * @return
     */
    UserDTO findByEmail(String email);

    /**
     * find all
     *
     * @return
     */
    List<UserDTO> findAll();


}
