package com.shan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
//
@SpringBootApplication
//@ComponentScan(basePackages = {"com.shan.*"})
@MapperScan(basePackages = {"com.shan.mapper"})
public class Day02springbootMybatisApplication  {
	public static void main(String[] args) {
		SpringApplication.run(Day02springbootMybatisApplication.class, args);
	}





}

