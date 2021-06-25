package com.newpi.data.dao;

import com.newpi.data.entity.Menu;
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
public interface MenuDao extends CrudRepository<Menu, Integer> {

    /**
     * find by ids
     *
     * @param ids
     *
     * @return
     */
    @Query("select c from Menu c where c.id in ?1")
    List<Menu> findByIds(List<Integer> ids);


}
