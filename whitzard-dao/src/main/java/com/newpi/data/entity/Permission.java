package com.newpi.data.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/12/2 3:35 PM
 * @desc:
 */

@Data
@Accessors(chain = true)
@Entity
@Table(name = "permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty(value = "父级权限id")
    private Integer parentId;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "权限值")
    private String value;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）")
    private Integer type;

    @ApiModelProperty(value = "前端资源路径")
    private String uri;

    @ApiModelProperty(value = "启用状态；0->禁用；1->启用")
    private Integer status;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
