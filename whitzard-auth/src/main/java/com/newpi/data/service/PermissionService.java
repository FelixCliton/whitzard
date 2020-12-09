package com.newpi.data.service;

import com.newpi.data.model.PermissionDTO;

import java.util.List;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/12/2 4:54 PM
 * @desc:
 */
public interface PermissionService {


    /**
     * add
     *
     * @param permission
     */
    void add(PermissionDTO permission);


    /**
     * delete
     *
     * @param id
     */
    void delete(Integer id);


    /**
     * update
     *
     * @param permission
     */
    void update(PermissionDTO permission);


    /**
     * find by ids
     *
     * @param ids
     *
     * @return
     */
    List<PermissionDTO> findByIds(List<Integer> ids);

    /**
     * find by id
     *
     * @param id
     *
     * @return
     */
    PermissionDTO findById(Integer id);

    /**
     * find all
     *
     * @return
     */
    List<PermissionDTO> findAll();


}

