package com.newpi.data.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

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

    private String clientId;

    private Boolean enabled;

    private Boolean accountExpired;

    private Boolean accountLocked;

    private Boolean passwordExpired;

    private List<String> roles;
}
