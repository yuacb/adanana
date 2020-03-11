package com.adanana;

import com.adanana.blog.core.listener.InitWeiboUserTarget;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ServletComponentScan
@MapperScan(basePackages = {"com.adanana.blog.dao"})
@SpringBootApplication
@EnableScheduling
public class AdananaApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(AdananaApplication.class);
		springApplication.addListeners(new InitWeiboUserTarget());
		springApplication.run(args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AdananaApplication.class);
	}

}