package com.newpi.data.service.impl;

import com.google.common.collect.Lists;
import com.newpi.data.dao.ResourceDao;
import com.newpi.data.entity.Resource;
import com.newpi.data.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/12/11 11:31 AM
 * @desc:
 */
@Service
@Slf4j
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceDao resourceDao;


    @Override
    public List<Resource> findAllByIds(List<Integer> ids) {
        return resourceDao.findByIds(ids);
    }

    @Override
    public List<Resource> findAll() {
        List<Resource> resources = Lists.newArrayList();

        Iterator<Resource> iterator = resourceDao.findAll().iterator();
        while (iterator.hasNext()) {
            resources.add(iterator.next());
        }
        return resources;
    }
}
