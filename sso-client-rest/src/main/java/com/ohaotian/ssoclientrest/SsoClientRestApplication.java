package com.ohaotian.ssoclientrest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@MapperScan(basePackages="com.ohaotian.ssoclientrest.mapper")
public class SsoClientRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsoClientRestApplication.class, args);
	}
}
