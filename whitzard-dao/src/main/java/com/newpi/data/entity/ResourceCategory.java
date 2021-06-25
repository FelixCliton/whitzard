package com.newpi.data.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/12/10 3:46 PM
 * @desc:
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "resource_category")
@ApiModel(description = "资源分类")
public class ResourceCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty(value = "分类名称")
    private String name;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "排序")
    private Integer sort;

}
