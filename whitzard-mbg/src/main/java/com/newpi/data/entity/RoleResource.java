package com.newpi.data.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/12/10 4:05 PM
 * @desc:
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "role_resource")
public class RoleResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty(value = "角色ID")
    private Integer roleId;

    @ApiModelProperty(value = "资源ID")
    private Integer resourceId;

}
