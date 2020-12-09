package com.newpi.data.controller;

import com.newpi.data.entity.UserRole;
import com.newpi.data.model.ResultEntity;
import com.newpi.data.model.UserDTO;
import com.newpi.data.service.UserRoleService;
import com.newpi.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    private UserRoleService userRoleService;

    @PostMapping(value = "add")
    public ResultEntity add(@RequestBody UserDTO userDto) {
        userService.add(userDto);
        return new ResultEntity();
    }

    @DeleteMapping(value = "delete/{id}")
    public ResultEntity delete(@PathVariable("id") Integer id) {
        userService.delete(id);
        return new ResultEntity();
    }

    @PutMapping(value = "put")
    public ResultEntity update(@RequestBody UserDTO userDto) {
        userService.update(userDto);
        return new ResultEntity();
    }

    @GetMapping(value = "list")
    public ResultEntity list() {
        List<UserDTO> userDtoList = userService.findAll();
        return new ResultEntity(userDtoList);
    }

    @GetMapping(value = "get/{email}")
    public ResultEntity getByEmail(@PathVariable("email") String email) {
        UserDTO userDto = userService.findByEmail(email);
        return new ResultEntity(userDto);
    }

    @GetMapping(value = "bindRole")
    public ResultEntity bindRole(@RequestParam("userId") Integer userId, @RequestParam("roleId") Integer roleId) {
        UserRole userRole = new UserRole()
                .setUserId(userId)
                .setRoleId(roleId);
        userRoleService.add(userRole);
        return new ResultEntity();
    }

    @GetMapping(value = "unbindRole")
    public ResultEntity unbindRole(@RequestParam("userId") Integer userId, @RequestParam("roleId") Integer roleId) {
        userRoleService.deleteByUserAndRole(userId, roleId);
        return new ResultEntity();
    }
}
