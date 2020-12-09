package com.newpi.data.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger API相关配置
 *
 * @author liujie3
 */
@Slf4j
public class Swagger2Config {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${swagger.enabled}")
    private Boolean enableSwagger = false;

    @Value("${swagger.base-package}")
    private String basePackage;

    @Bean
    public Docket createRestApi() {
        if (!enableSwagger) {
            return new Docket(DocumentationType.SWAGGER_2);
        }
        if (null == basePackage || "".equalsIgnoreCase(basePackage)) {
            log.error("swagger handler unknown the base package,please specific the param of swagger.base-package in config file.");
            return new Docket(DocumentationType.SWAGGER_2);
        }
        log.info("applicationName:" + applicationName);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(applicationName)
                .description(applicationName + "服务API文档")
                .version("1.0")
                .build();
    }
}
