package com.newpi.data.controller;

import com.newpi.data.entity.Role;
import com.newpi.data.model.ResultEntity;
import com.newpi.data.service.RolePermissionService;
import com.newpi.data.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/12/2 8:15 PM
 * @desc:
 */
@Api(description = "后台用户角色管理")
@RestController
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation("添加角色")
    @PostMapping("/")
    public ResultEntity add(@RequestBody Role role) {
        roleService.add(role);
        return new ResultEntity();
    }

    @ApiOperation("删除角色")
    @DeleteMapping(value = "/{id}")
    public ResultEntity delete(@PathVariable("id") Integer id) {
        roleService.delete(id);
        return new ResultEntity();
    }

    @ApiOperation("更新角色")
    @PutMapping("/")
    public ResultEntity update(@RequestBody Role role) {
        roleService.update(role);
        return new ResultEntity();
    }

    @ApiOperation("查询所有角色信息")
    @GetMapping(value = "list")
    public ResultEntity list() {
        List<Role> roleList = roleService.findAll();
        return new ResultEntity(roleList);
    }

    @ApiOperation("给角色分配菜单")
    @GetMapping(value = "/allocateMenu")
    public ResultEntity allocateMenu(@RequestParam("roleId") Integer roleId, @RequestParam("menuIds") List<Integer> menuIds) {
        roleService.allocateMenu(roleId, menuIds);
        return new ResultEntity();
    }

    @ApiOperation("给角色分配资源")
    @GetMapping(value = "allocateResource")
    public ResultEntity allocateResource(@RequestParam("roleId") Integer roleId, @RequestParam("resourceIds") List<Integer> resourceIds) {
        roleService.allocateResource(roleId, resourceIds);
        return new ResultEntity();
    }
}
