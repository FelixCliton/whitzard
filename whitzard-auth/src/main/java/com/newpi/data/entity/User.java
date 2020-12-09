package com.newpi.data.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/12/2 2:44 PM
 * @desc:
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String userName;

    private String password;

    private String email;

    private String phone;

    private Boolean enabled;

    private Boolean accountExpired;

    private Boolean accountLocked;

    private Boolean passwordExpired;

    private Date createTime;

    private Date updateTime;
}
