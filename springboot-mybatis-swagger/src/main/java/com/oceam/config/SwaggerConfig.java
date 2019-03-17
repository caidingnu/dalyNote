package com.oceam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Author: caidingnu
 * Date:   2018/10/30
 * Time：  14:36
 * Content:
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig{

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.oceam.controller"))////扫描com.oceam.controller路径下的api文档
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
//                标题
                .title("Spring Boot中使用Swagger2构建RESTful API")
//                摘要
                .description("rest api 文档构建利器")
                .termsOfServiceUrl("https://blog.csdn.net/caidingnu")//（不可见）条款地址
                .contact("itguang22222")  //作者
                .version("1.0")
                .build();//版本号
    }

}
