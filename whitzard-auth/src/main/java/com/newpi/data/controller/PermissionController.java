package com.newpi.data.controller;

import com.newpi.data.model.PermissionDTO;
import com.newpi.data.model.ResultEntity;
import com.newpi.data.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/12/2 8:36 PM
 * @desc:
 */
@RestController
@RequestMapping(value = "permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @PostMapping(value = "add")
    public ResultEntity add(@RequestBody PermissionDTO permissionDto) {
        permissionService.add(permissionDto);
        return new ResultEntity();
    }

    @DeleteMapping(value = "delete/{id}")
    public ResultEntity delete(@PathVariable("id") Integer id) {
        permissionService.delete(id);
        return new ResultEntity();
    }

    @PutMapping(value = "put")
    public ResultEntity update(@RequestBody PermissionDTO permissionDto) {
        permissionService.update(permissionDto);
        return new ResultEntity();
    }

    @GetMapping(value = "list")
    public ResultEntity list() {
        List<PermissionDTO> permissionDtoList = permissionService.findAll();
        return new ResultEntity(permissionDtoList);
    }

    @GetMapping(value = "get/{id}")
    public ResultEntity getById(@PathVariable("id") Integer id) {
        PermissionDTO permissionDto = permissionService.findById(id);
        return new ResultEntity(permissionDto);
    }
}
