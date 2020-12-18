package com.newpi.data.controller;

import com.google.common.collect.Maps;
import com.newpi.data.domain.UpdatePasswordParam;
import com.newpi.data.domain.UserDTO;
import com.newpi.data.domain.UserParam;
import com.newpi.data.entity.Role;
import com.newpi.data.entity.User;
import com.newpi.data.model.ResultEntity;
import com.newpi.data.service.RoleService;
import com.newpi.data.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/11/26 5:55 PM
 * @desc:
 */
@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "用户注册")
    @PostMapping(value = "register")
    public ResultEntity add(@RequestBody UserParam param) {
        userService.add(param);
        return new ResultEntity();
    }

    @ApiOperation("用户登陆，成功则返回token")
    @GetMapping(value = "login")
    public ResultEntity login(@RequestParam(name = "username") String userName,
                              @RequestParam(name = "password") String password) {
        return userService.login(userName, password);
    }

    @ApiOperation(value = "获取当前登陆用户信息")
    @GetMapping(value = "info")
    public ResultEntity currentLoginUser() {
        User user = userService.findCurrentUser();
        Map<String, Object> data = Maps.newHashMap();
        data.put("username", user.getUsername());
        data.put("menus", userService.findMenuList(user.getId()));
        data.put("icon", user.getIcon());

        List<Role> roleList = userService.findRoleList(user.getId());
        if (null != roleList && !roleList.isEmpty()) {
            List<String> roles = roleList.stream().map(role -> role.getEnName()).collect(Collectors.toList());
            data.put("roles", roles);
        }
        return new ResultEntity(data);
    }


    @ApiOperation("退出登录")
    @GetMapping(value = "logout")
    public ResultEntity logout(@RequestParam("username") String userName) {
        return new ResultEntity();
    }

    @ApiOperation("获取指定用户信息")
    @GetMapping(value = "/get/{username}")
    public ResultEntity fetchUserByUsername(@PathVariable("username") String username) {
        User user = userService.findByUsername(username);
        return new ResultEntity(user);
    }

    @ApiOperation("获取指定用户信息")
    @GetMapping(value = "/{id}")
    public ResultEntity fetchUserById(@PathVariable("id") Integer id) {
        User user = userService.findById(id);
        return new ResultEntity(user);
    }

    @ApiOperation("根据用户名获取通用用户信息")
    @GetMapping("loadByUsername")
    public ResultEntity<UserDTO> loadUserByUsername(@RequestParam("username") String username) {
        UserDTO userDTO = userService.loadUserByUsername(username);
        return new ResultEntity<>(userDTO);
    }

    @ApiOperation("删除用户")
    @DeleteMapping(value = "/{id}")
    public ResultEntity delete(@PathVariable("id") Integer id) {
        userService.delete(id);
        return new ResultEntity();
    }

    @ApiOperation("更新用户信息")
    @PutMapping(value = "/update")
    public ResultEntity update(@RequestBody User user) {
        userService.update(user);
        return new ResultEntity();
    }

    @ApiOperation("更改用户密码")
    @PostMapping("/updatePassword")
    public ResultEntity updatePassword(UpdatePasswordParam passwordParam) {
        User user = userService.updatePassword(passwordParam);
        return new ResultEntity(user);
    }

    @ApiOperation("查询用户列表")
    @GetMapping(value = "list")
    public ResultEntity list() {
        List<User> userDtoList = userService.findAll();
        return new ResultEntity(userDtoList);
    }


    @ApiOperation("给用户分配角色")
    @GetMapping(value = "bindRole")
    public ResultEntity bindRole(@RequestParam("userId") Integer userId, @RequestParam("roleId") List<Integer> roleIds) {

        userService.bindRole(userId, roleIds);

        return new ResultEntity();
    }
}
