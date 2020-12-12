package com.newpi.data.dao;

import com.newpi.data.entity.UserRole;
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
public interface UserRoleDao extends CrudRepository<UserRole, Integer> {


    /**
     * find all by user
     *
     * @param userId
     *
     * @return
     */
    @Query("select c from UserRole c where c.userId = ?1")
    List<UserRole> findAllByUser(Integer userId);


    /**
     * find all by role
     *
     * @param roleId
     *
     * @return
     */
    @Query("select c from UserRole c where c.roleId = ?1")
    List<UserRole> findAllByRole(Integer roleId);


    /**
     * delete by userId and roleId
     *
     * @param userId
     * @param roleIds
     */
    @Query("delete from UserRole c where c.userId = ?1 and c.roleId in ?2")
    void deleteByUserAndRole(Integer userId, List<Integer> roleIds);


    /**
     * delete all by user
     *
     * @param userId
     *
     * @return
     */
    @Query("select c from UserRole c where c.userId = ?1")
    void deleteAllByUserId(Integer userId);


    /**
     * delete all by role
     *
     * @param roleId
     *
     * @return
     */
    @Query("select c from UserRole c where c.roleId = ?1")
    void deleteAllByRoleId(Integer roleId);

}
