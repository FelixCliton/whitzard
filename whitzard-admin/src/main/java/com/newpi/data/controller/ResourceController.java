package com.newpi.data.controller;

import com.newpi.data.entity.Resource;
import com.newpi.data.model.ResultEntity;
import com.newpi.data.service.ResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/12/12 4:37 PM
 * @desc:
 */
@Api(description = "后台资源管理")
@RestController
@RequestMapping(value = "resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @ApiOperation("添加后台资源")
    @PostMapping(value = "/")
    public ResultEntity add(@RequestBody Resource resource) {
        resourceService.add(resource);
        return new ResultEntity();
    }

    @ApiOperation("修改后台资源")
    @PutMapping(value = "/")
    public ResultEntity update(
            @RequestBody Resource resource) {
        resourceService.update(resource);
        return new ResultEntity();
    }

    @ApiOperation("根据ID获取资源详情")
    @GetMapping(value = "/{resourceId}")
    public ResultEntity<Resource> findById(@PathVariable Integer resourceId) {
        Resource resource = resourceService.findById(resourceId);
        return new ResultEntity<>(resource);
    }

    @ApiOperation("根据ID删除后台资源")
    @DeleteMapping(value = "/{resourceId}")
    public ResultEntity deleteById(@PathVariable Integer resourceId) {
        resourceService.deleteById(resourceId);
        return new ResultEntity();
    }

    @ApiOperation("查询所有后台资源")
    @GetMapping(value = "/list")
    public ResultEntity<List<Resource>> list() {
        List<Resource> resourceList = resourceService.findAll();
        return new ResultEntity<>(resourceList);
    }
}
