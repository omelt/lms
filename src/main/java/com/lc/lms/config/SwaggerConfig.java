package com.lc.lms.config;

import io.swagger.annotations.ApiOperation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableWebMvc
@EnableSwagger2// swagger2的自动配置
public class SwaggerConfig implements WebMvcConfigurer {
//运行后访问http://localhost:8080/swagger-ui.html#/
/**
 * 发现如果继承了WebMvcConfigurationSupport，则在yml中配置的相关内容会失效。 需要重新指定静态资源
 *
 * @param registry
 */
@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations(
        "classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations(
        "classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations(
        "classpath:/META-INF/resources/webjars/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
        }



    /*
        swagger会帮助我们生成接口文档
        1：配置生成的文档信息
        2：配置生成规则
         */
@Bean
public Docket getDocket(){
        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();

        //全局配置参数
        ParameterBuilder parameterBuilder=new ParameterBuilder();
        List<Parameter> list=new ArrayList<>();
        parameterBuilder.name("Authorization").description("用户token")
                        .modelRef(new ModelRef("string")).parameterType("header")
                        .required(false).build();
        list.add(parameterBuilder.build());

        //指定封面信息
        apiInfoBuilder.title("lc图书管理系统 接口说明")
        .description("此文档详细说明了接口规范")
        .version("version 1.0")
        .contact( new Contact("lc","omelt.github.io","@qq.com"))
        .build();

        ApiInfo apiInfo = apiInfoBuilder.build();

        //指定生成策略
        Docket docket = new Docket(DocumentationType.SWAGGER_2)//指定文档路径
        .apiInfo(apiInfo)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.lc.lms.control"))
        .paths(PathSelectors.any())
        .build().globalOperationParameters(list);

        return docket;
        }


}
