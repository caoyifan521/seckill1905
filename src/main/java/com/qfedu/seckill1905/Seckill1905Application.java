package com.qfedu.seckill1905;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.qfedu.seckill1905.dao")
public class Seckill1905Application {

	public static void main(String[] args) {
		SpringApplication.run(Seckill1905Application.class, args);
	}

}
