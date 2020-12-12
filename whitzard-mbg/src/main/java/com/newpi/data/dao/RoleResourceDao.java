package com.newpi.data.dao;

import com.newpi.data.entity.RoleResource;
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
public interface RoleResourceDao extends CrudRepository<RoleResource, Integer> {

    /**
     * find all by roleIds
     *
     * @param roleIds
     *
     * @return
     */
    @Query("select c from RoleResource c where c.roleId in ?1")
    List<RoleResource> findAllByRoles(List<Integer> roleIds);

    /**
     * delete all by roleId
     *
     * @param roleId
     */
    void deleteAllByRoleId(Integer roleId);

}
