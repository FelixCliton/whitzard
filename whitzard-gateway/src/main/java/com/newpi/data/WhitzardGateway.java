package com.newpi.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/11/18 3:51 PM
 * @desc:
 */
@EnableDiscoveryClient
@SpringBootApplication
public class WhitzardGateway {

    public static void main(String[] args) {
        SpringApplication.run(WhitzardGateway.class);
    }
}
