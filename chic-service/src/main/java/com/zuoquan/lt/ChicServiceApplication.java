package com.zuoquan.lt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.zuoquan.lt.dao.mapper")
@SpringBootApplication
public class ChicServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChicServiceApplication.class, args);
	}
}
