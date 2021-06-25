package com.newpi.data.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/12/2 3:32 PM
 * @desc:
 */
@Data
@Entity
@Table(name = "role")
@Accessors(chain = true)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String parentId;

    private String name;

    private String enName;

    private String description;

    private Date createTime;

    private Date updateTime;

}
