package com.newpi.data.service;

import com.newpi.data.entity.Resource;

import java.util.List;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/12/2 5:02 PM
 * @desc:
 */
public interface ResourceService {

    /**
     * find all by ids
     *
     * @param ids
     *
     * @return
     */
    List<Resource> findAllByIds(List<Integer> ids);

    /**
     * find all
     *
     * @return
     */
    List<Resource> findAll();

    /**
     * add resource
     *
     * @param resource
     */
    void add(Resource resource);

    /**
     * update
     *
     * @param resource
     */
    void update(Resource resource);

    /**
     * find by resourceId
     *
     * @param resourceId
     *
     * @return
     */
    Resource findById(Integer resourceId);

    /**
     * delete by id
     *
     * @param resourceId
     */
    void deleteById(Integer resourceId);
}
