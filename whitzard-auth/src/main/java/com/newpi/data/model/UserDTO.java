package com.newpi.data.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/12/2 2:43 PM
 * @desc:
 */
@Data
@Accessors(chain = true)
public class UserDTO {

    private Integer id;

    private String userName;

    private String password;

    private String email;

    private String phone;

    private Boolean enabled;

    private Boolean accountExpired;

    private Boolean accountLocked;

    private Boolean passwordExpired;
}
