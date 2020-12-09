package com.newpi.data.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/11/29 4:04 PM
 * @desc:
 */
@Data
@Accessors(chain = true)
public class WebLog {

    /**
     * 操作用户
     */
    private String user;

    /**
     * 操作时间
     */
    private Long startTime;

    /**
     * 消耗时间
     */
    private Long spendTime;

    /**
     * URL
     */
    private String url;

    /**
     * request type
     */
    private String requestType;

    /**
     * 请求方法
     */
    private String method;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 请求参数
     */
    private Object parameter;

    /**
     * 返回结果
     */
    private Object result;


}
