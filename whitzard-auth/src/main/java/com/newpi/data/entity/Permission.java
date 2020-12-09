package com.newpi.data.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/12/2 3:35 PM
 * @desc:
 */

@Data
@Accessors(chain = true)
@Entity
@Table(name = "permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String parentId;

    private String name;

    private String enName;

    private String description;

    private String url;

    private Date createTime;

    private Date updateTime;
}
