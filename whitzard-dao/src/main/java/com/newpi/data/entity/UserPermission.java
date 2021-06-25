package com.newpi.data.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/12/10 4:08 PM
 * @desc:
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "user_permission")
public class UserPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "权限ID")
    private Integer permissionId;

    @ApiModelProperty(value = "是否增加权限,true->增加权限，false->减少权限")
    private Boolean addPermission;

}
