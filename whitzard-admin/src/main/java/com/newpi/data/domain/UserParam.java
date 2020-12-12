package com.newpi.data.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/12/10 4:19 PM
 * @desc:
 */
@Data
public class UserParam {
    @ApiModelProperty(value = "用户名", required = true)
    @NotEmpty
    private String username;

    @ApiModelProperty(value = "密码", required = true)
    @NotEmpty
    private String password;
    @ApiModelProperty(value = "用户头像")
    private String icon;

    @ApiModelProperty(value = "邮箱")
    @Email
    private String email;

    @ApiModelProperty(value = "电话")
    @Pattern(regexp = "^(13[0-9]|14[01456879]|15[0-3,5-9]|16[2567]|17[0-8]|18[0-9]|19[0-3,5-9])\\d{8}$")
    private String phone;

    @ApiModelProperty(value = "备注")
    private String description;
}
