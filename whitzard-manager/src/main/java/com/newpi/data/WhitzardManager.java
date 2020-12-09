package com.newpi.data;

import com.newpi.data.annotation.EnableSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/11/22 5:46 PM
 * @desc:
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
public class WhitzardManager {

    public static void main(String[] args) {
        SpringApplication.run(WhitzardManager.class);
    }

}
