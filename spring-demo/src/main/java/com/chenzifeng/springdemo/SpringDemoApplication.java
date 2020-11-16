package com.chenzifeng.springdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

@SpringBootApplication
//@MapperScan
public class SpringDemoApplication {
	//日志打印
	private static final Logger logger = LoggerFactory.getLogger(SpringDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringDemoApplication.class, args);
	}

}
