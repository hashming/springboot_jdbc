package com.duoduo.springbootjdbc.springboot_jdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.duoduo.springbootjdbc.springboot_jdbc.dao")
public class SpringbootJdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJdbcApplication.class, args);
	}

}
