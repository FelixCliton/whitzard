package com.newpi.data.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/11/29 11:04 PM
 * @desc: 网关黑白名单配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.cloud.gateway")
public class GatewayBlackWhiteListConfig {

    private List<String> whiteList;

    private List<String> blackList;

}
