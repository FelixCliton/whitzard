package com.newpi.data.model;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/11/25 8:50 PM
 * @desc:
 */
@Data
@Accessors(chain = true)
public class GatewayRouteDto {

    private String id;

    private String uri;

    private List<String> predicates = Lists.newArrayList();

    private List<String> filters = Lists.newArrayList();

    private Integer routeOrder;

    private String comment;
}
