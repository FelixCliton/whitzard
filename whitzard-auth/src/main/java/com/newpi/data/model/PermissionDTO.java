package com.newpi.data.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/12/2 4:56 PM
 * @desc:
 */
@Data
@Accessors(chain = true)
public class PermissionDTO {

    private Integer id;

    private String parentId;

    private String name;

    private String enName;

    private String description;

    private String url;

}
