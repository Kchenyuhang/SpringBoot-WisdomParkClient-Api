package com.niit.soft.client.api;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
/**
 * @author Tao
 */ //@EnableRabbit //开启基于注解的RabbitMQ模式
//@ComponentScan(basePackages = {"com.niit.soft.client.api"})
//@EnableAsync  //作用于启动类，放置在启动类上开启异步任务注解
@EnableScheduling   //开启定时
@SpringBootApplication
@MapperScan("com.niit.soft.client.api.mapper")
public class ClientApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientApiApplication.class, args);
    }
}
