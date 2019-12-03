package com.adanana;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
@ServletComponentScan
@ComponentScan(value="com.adanana")
@MapperScan(basePackages = {"com.adanana.dao"})
@SpringBootApplication
public class AdananaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdananaApplication.class, args);
	}

}
