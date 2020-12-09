package com.newpi.data.dao;

import com.newpi.data.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/12/2 4:11 PM
 * @desc:
 */
@Repository
public interface UserDao extends CrudRepository<User, Integer> {

    /**
     * find by email
     *
     * @param email
     *
     * @return
     */
    User findByEmail(String email);

}
