package com.adanana;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer; 
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ServletComponentScan
@MapperScan(basePackages = {"com.adanana.blog.dao"})
@SpringBootApplication
@ComponentScan(value="com.adanana")
@EnableScheduling
public class AdananaApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(AdananaApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AdananaApplication.class);
	}

}