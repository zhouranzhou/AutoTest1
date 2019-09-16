package com.course.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration  //在springboot里面专门为了加载配置文件的标签
@EnableSwagger2   //自动加载配置文件
public class SwaggerConfig {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .pathMapping("/")
                .select()
                .paths(PathSelectors.regex("/.*"))   //匹配那些访问的方法
                .build();
    }

    private ApiInfo apiInfo() {
        //http://localhost:8888/swagger-ui.html
        return new ApiInfoBuilder().title("我的接口文档")
                .contact(new Contact("tuantuan","","1226104936@qq.com"))
                .description("这是我的swaggerui生成的接口文档")
                .version("1.0.0.0")
                .build();
    }

}