package com.newpi.data.service;

import com.newpi.data.entity.Menu;
import com.newpi.data.entity.Permission;
import com.newpi.data.entity.Resource;
import com.newpi.data.entity.Role;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/12/2 5:02 PM
 * @desc:
 */
public interface RoleService {

    /**
     * add
     *
     * @param role
     */
    void add(Role role);


    /**
     * find role list
     *
     * @param userId
     *
     * @return
     */
    List<Role> findRoleList(Integer userId);

    /**
     * delete
     *
     * @param id
     */
    void delete(Integer id);


    /**
     * update
     *
     * @param role
     */
    void update(Role role);

    /**
     * find by ids
     *
     * @param ids
     *
     * @return
     */
    List<Role> findByIds(List<Integer> ids);


    /**
     * find by id
     *
     * @param id
     *
     * @return
     */
    Role findById(Integer id);

    /**
     * find all
     *
     * @return
     */
    List<Role> findAll();

    /**
     * 更新用户对应角色
     *
     * @param userId
     * @param roleIds
     */
    @Transactional(rollbackFor = Exception.class)
    void bindRole(Integer userId, List<Integer> roleIds);

    /**
     * find resource
     *
     * @param roleIds
     *
     * @return
     */
    List<Resource> findResource(List<Integer> roleIds);

    /**
     * find permission
     *
     * @param roleIds
     *
     * @return
     */
    List<Permission> findPermission(List<Integer> roleIds);

    /**
     * find menus
     *
     * @param roleIds
     *
     * @return
     */
    List<Menu> findMenu(List<Integer> roleIds);


    /**
     * allocate menus
     *
     * @param roleId
     * @param menuIds
     */
    @Transactional(rollbackFor = Exception.class)
    void allocateMenu(Integer roleId, List<Integer> menuIds);

    /**
     * allocate resource
     *
     * @param roleId
     * @param resourceIds
     */
    @Transactional(rollbackFor = Exception.class)
    void allocateResource(Integer roleId, List<Integer> resourceIds);

    /**
     * init resource role map
     */
    void initResourceRolesMap();
}
