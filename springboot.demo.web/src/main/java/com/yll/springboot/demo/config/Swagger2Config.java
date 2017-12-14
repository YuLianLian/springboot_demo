package com.yll.springboot.demo.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * Swagger 在线文件配置
 * 
 * @author  Administrator
 * @version  [版本号, 2017年10月20日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public SecurityScheme apiKey() {
        return new ApiKey("token", "accessToken", "header");
    }

    @Bean
    public Docket apiConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
        		.groupName("CONTROLLER")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jzg.springboot.demo.controller"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .securitySchemes(Arrays.asList(apiKey()));
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("service controller api ")// 大标题
                .description("spring boot webservice 平台 API")// 小标题
                .version("2.0").build();
    }
    
    @Bean
    public Docket restConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("JAX-RS")
                .apiInfo(restInfo())
                .forCodeGeneration(true)
                .pathMapping("/").select()
                .apis(RequestHandlerSelectors.basePackage("com.jzg.springboot.demo.service"))
                .paths(PathSelectors.any())
                .build().useDefaultResponseMessages(false);
    }
    
    private ApiInfo restInfo() {
        return new ApiInfoBuilder().title("service rest api ")// 大标题
                .description("spring boot webservice 平台 API")// 小标题
                .version("2.0").build();
    }
}