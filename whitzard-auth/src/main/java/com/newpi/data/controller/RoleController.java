package com.newpi.data.controller;

import com.newpi.data.model.ResultEntity;
import com.newpi.data.model.RoleDTO;
import com.newpi.data.model.RolePermissionBindDTO;
import com.newpi.data.service.RolePermissionService;
import com.newpi.data.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/12/2 8:15 PM
 * @desc:
 */
@RestController
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @PostMapping(value = "add")
    public ResultEntity add(@RequestBody RoleDTO roleDto) {
        roleService.add(roleDto);
        return new ResultEntity();
    }

    @DeleteMapping(value = "delete/{id}")
    public ResultEntity delete(@PathVariable("id") Integer id) {
        roleService.delete(id);
        return new ResultEntity();
    }

    @PutMapping(value = "put")
    public ResultEntity update(@RequestBody RoleDTO roleDto) {
        roleService.update(roleDto);
        return new ResultEntity();
    }

    @GetMapping(value = "list")
    public ResultEntity list() {
        List<RoleDTO> roleDtoList = roleService.findAll();
        return new ResultEntity(roleDtoList);
    }

    @GetMapping(value = "get/{id}")
    public ResultEntity getById(@PathVariable("id") Integer id) {
        RoleDTO roleDto = roleService.findById(id);
        return new ResultEntity(roleDto);
    }

    @PostMapping(value = "bindPermission")
    public ResultEntity bindRole(@RequestBody RolePermissionBindDTO rolePermissionBindDto) {
        rolePermissionService.bindPermission(rolePermissionBindDto);
        return new ResultEntity();
    }

    @GetMapping(value = "unbindRole")
    public ResultEntity unbindRole(@RequestBody RolePermissionBindDTO rolePermissionBindDto) {
        rolePermissionService.unbindPermission(rolePermissionBindDto);
        return new ResultEntity();
    }

}
