package com.newpi.data.dao;

import com.newpi.data.entity.Permission;
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
public interface PermissionDao extends CrudRepository<Permission, Integer> {


    /**
     * findByIds
     *
     * @param ids
     *
     * @return
     */
    @Query("select c from Permission c where c.id in ?1")
    List<Permission> findByIds(List<Integer> ids);


}
