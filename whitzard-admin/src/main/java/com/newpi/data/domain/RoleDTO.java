package com.newpi.data.domain;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/12/2 5:03 PM
 * @desc:
 */
@Data
@Accessors(chain = true)
public class RoleDTO {

    private Integer id;

    private String parentId;

    private String name;

    private String enName;

    private String description;

}
