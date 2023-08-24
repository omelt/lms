package com.lc.lms;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan(basePackages = "com.lc.lms.mapper")
@EnableCaching
public class LmsApplication {


	public static void main(String[] args) {
		SpringApplication.run(LmsApplication.class, args);
	}

}
