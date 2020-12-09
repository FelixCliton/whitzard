package com.newpi.data.service;

import com.newpi.data.model.RoleDTO;

import java.util.List;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/12/2 5:02 PM
 * @desc:
 */
public interface RoleService {

    /**
     * add
     *
     * @param role
     */
    void add(RoleDTO role);


    /**
     * delete
     *
     * @param id
     */
    void delete(Integer id);


    /**
     * update
     *
     * @param role
     */
    void update(RoleDTO role);

    /**
     * find by ids
     *
     * @param ids
     *
     * @return
     */
    List<RoleDTO> findByIds(List<Integer> ids);


    /**
     * find by id
     *
     * @param id
     *
     * @return
     */
    RoleDTO findById(Integer id);

    /**
     * find all
     *
     * @return
     */
    List<RoleDTO> findAll();

}
