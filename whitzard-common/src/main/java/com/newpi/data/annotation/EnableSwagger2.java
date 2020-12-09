package com.newpi.data.annotation;


import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.newpi.data.config.Swagger2Config;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration
@springfox.documentation.swagger2.annotations.EnableSwagger2
@EnableKnife4j
@Import(Swagger2Config.class)
public @interface EnableSwagger2 {

}
