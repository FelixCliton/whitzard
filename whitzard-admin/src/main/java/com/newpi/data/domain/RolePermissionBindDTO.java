package com.newpi.data.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/12/2 8:25 PM
 * @desc:
 */
@Data
@Accessors(chain = true)
public class RolePermissionBindDTO {

    private Integer roleId;

    private List<Integer> permissionIds;

}
