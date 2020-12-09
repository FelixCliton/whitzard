package com.newpi.data.dao;

import com.newpi.data.entity.Role;
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
public interface RoleDao extends CrudRepository<Role, Integer> {

    /**
     * find by ids
     *
     * @param ids
     *
     * @return
     */
    @Query("select c from Role c where c.id in ?1")
    List<Role> findByIds(List<Integer> ids);


}
