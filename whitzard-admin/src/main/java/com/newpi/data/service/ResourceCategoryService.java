package com.newpi.data.service;

import com.newpi.data.entity.ResourceCategory;

import java.util.List;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/12/16 5:04 PM
 * @desc:
 */
public interface ResourceCategoryService {
    /**
     * find all
     *
     * @return
     */
    List<ResourceCategory> list();

    /**
     * add
     *
     * @param resourceCategory
     */
    void add(ResourceCategory resourceCategory);

    /**
     * update
     *
     * @param resourceCategory
     */
    void update(ResourceCategory resourceCategory);

    /**
     * delete
     *
     * @param id
     */
    void delete(Integer id);
}
