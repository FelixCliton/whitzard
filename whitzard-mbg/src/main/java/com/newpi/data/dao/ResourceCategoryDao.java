package com.newpi.data.dao;

import com.newpi.data.entity.Menu;
import com.newpi.data.entity.ResourceCategory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/12/2 4:11 PM
 * @desc:
 */
@Repository
public interface ResourceCategoryDao extends CrudRepository<ResourceCategory, Integer> {
    
}
