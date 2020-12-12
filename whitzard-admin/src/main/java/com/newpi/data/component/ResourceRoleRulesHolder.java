package com.newpi.data.component;

import com.newpi.data.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 资源与角色访问对应关系操作组件
 *
 * @author macro
 * @date 2020/7/17
 */
@Component
public class ResourceRoleRulesHolder {

    @Autowired
    private RoleService roleService;

    @PostConstruct
    public void initResourceRolesMap() {
        roleService.initResourceRolesMap();
    }
}
