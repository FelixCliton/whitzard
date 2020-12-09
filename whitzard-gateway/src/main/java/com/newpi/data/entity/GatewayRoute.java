package com.newpi.data.entity;

import com.google.common.collect.Lists;
import com.newpi.data.converter.JpaListJsonConverter;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/11/22 11:19 PM
 * @desc:
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "whitzard_route")
public class GatewayRoute {

    @Id
    private String id;

    private String uri;

    @Convert(converter = JpaListJsonConverter.class)
    private List<String> predicates = Lists.newArrayList();

    @Convert(converter = JpaListJsonConverter.class)
    private List<String> filters = Lists.newArrayList();

    private Integer routeOrder;

    private Date addTime;

    private Date updateTime;

    private String comment;

}
