package com.newpi.data.service.impl;

import com.google.common.collect.Lists;
import com.newpi.data.dao.ResourceCategoryDao;
import com.newpi.data.entity.ResourceCategory;
import com.newpi.data.service.ResourceCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/12/16 5:11 PM
 * @desc:
 */
@Service
@Slf4j
public class ResourceCategoryServiceImpl implements ResourceCategoryService {

    @Autowired
    private ResourceCategoryDao resourceCategoryDao;

    @Override
    public List<ResourceCategory> list() {
        List<ResourceCategory> resourceCategoryList = Lists.newArrayList();
        Iterator<ResourceCategory> iterator = resourceCategoryDao.findAll().iterator();
        while (iterator.hasNext()) {
            ResourceCategory resourceCategory = iterator.next();
            resourceCategoryList.add(resourceCategory);
        }

        return resourceCategoryList;
    }

    @Override
    public void add(ResourceCategory resourceCategory) {
        resourceCategoryDao.save(resourceCategory);
    }

    @Override
    public void update(ResourceCategory resourceCategory) {
        resourceCategoryDao.save(resourceCategory);
    }

    @Override
    public void delete(Integer id) {
        resourceCategoryDao.deleteById(id);
    }
}
