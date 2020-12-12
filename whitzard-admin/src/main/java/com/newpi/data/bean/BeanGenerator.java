package com.newpi.data.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/12/10 5:53 PM
 * @desc:
 */
@Component
public class BeanGenerator {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
