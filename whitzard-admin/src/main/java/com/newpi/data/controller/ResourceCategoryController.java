package com.newpi.data.controller;

import com.newpi.data.entity.ResourceCategory;
import com.newpi.data.model.ResultEntity;
import com.newpi.data.service.ResourceCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后台资源分类管理Controller
 *
 * @author liujie3
 * @date 2020/12/5
 */
@RestController
@Api(description = "后台资源分类管理")
@RequestMapping("/resourceCategory")
public class ResourceCategoryController {

    @Autowired
    private ResourceCategoryService resourceCategoryService;

    @ApiOperation("查询所有后台资源分类")
    @GetMapping(value = "/list")
    public ResultEntity<List<ResourceCategory>> listAll() {
        List<ResourceCategory> resourceList = resourceCategoryService.list();
        return new ResultEntity<>(resourceList);
    }

    @ApiOperation("添加后台资源分类")
    @PostMapping(value = "/")
    public ResultEntity create(@RequestBody ResourceCategory resourceCategory) {
        resourceCategoryService.add(resourceCategory);
        return new ResultEntity();
    }

    @ApiOperation("修改后台资源分类")
    @PutMapping(value = "/")
    public ResultEntity update(@RequestBody ResourceCategory resourceCategory) {
        resourceCategoryService.update(resourceCategory);
        return new ResultEntity();
    }

    @ApiOperation("根据ID删除后台资源")
    @DeleteMapping(value = "/{id}")
    public ResultEntity delete(@PathVariable Integer id) {
        resourceCategoryService.delete(id);
        return new ResultEntity();
    }
}
