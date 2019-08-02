package com.limy.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling//启动定时任务注解
@EnableAsync//开启线程池注解
public class MultithreadtestApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultithreadtestApplication.class, args);
	}

}
