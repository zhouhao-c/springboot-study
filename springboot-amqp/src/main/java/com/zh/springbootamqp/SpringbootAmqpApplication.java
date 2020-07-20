package com.zh.springbootamqp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 自动配置
 *      1、RabbitAutoConfiguration
 *      2、自动配置了连接工厂ConnectionFactory
 *      3、RabbitProperties封装了 RabbitMQ的配置
 *      4、RabbitTemplate：给RabbitMQ的配置发送和接收消息
 *      5、AmqpAdmin：RabbitMQ系统管理功能组件
 */
@SpringBootApplication
public class SpringbootAmqpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAmqpApplication.class, args);
    }

}
