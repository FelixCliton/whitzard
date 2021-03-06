package com.newpi.data;

import com.newpi.data.annotation.EnableSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/11/26 5:41 PM
 * @desc:
 */
@EnableSwagger2
@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
public class WhitzardAuth {

    public static void main(String[] args) {
        SpringApplication.run(WhitzardAuth.class);
    }

}
