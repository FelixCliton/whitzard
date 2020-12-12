package com.newpi.data;

import com.newpi.data.annotation.EnableSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/12/12 4:37 PM
 * @desc:
 */
@EnableSwagger2
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class WhitzardAdmin {

    public static void main(String[] args) {
        SpringApplication.run(WhitzardAdmin.class, args);
    }

}

