package com.zh.springbootamqp;

import com.zh.springbootamqp.bean.Book;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SpringbootAmqpApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    @Test
    public void createExchange(){
//        amqpAdmin.declareExchange(new DirectExchange("amqpAdmin.exchange"));
//        System.out.println("交换器创建完成");

//        amqpAdmin.declareQueue(new Queue("amqpAdmin.queue",true));
//        System.out.println("队列创建完成");

        /**
         * 创建绑定规则
         */
//        amqpAdmin.declareBinding(
//                new Binding("amqpAdmin.queue", Binding.DestinationType.QUEUE, "amqpAdmin.exchange", "amqp.zzz",null));
    }

    /**
     * 单播（点对点）
     */
    @Test
    void contextLoads() {
        /**
         * .send三个参数（交换器，路由键，消息）
         * message需要自己构造一个，定义消息体内容和消息头
         */
        //rabbitTemplate.send(exchange,routeKey,message);

        /**
         * .convertAndSend
         * 只需要传入发送对象，该方法自动序列化发送给rabbitmq。
         */
        //rabbitTemplate.convertAndSend(exchange,routeKey,object);
        Map<String,Object> map = new HashMap<>();
        map.put("msg","第二个消息");
        map.put("data", Arrays.asList("hello",123,"mmmmmmm"));
        Book book = new Book(1,"西游记");
        rabbitTemplate.convertAndSend("exchange.direct","zh.news",book);
    }

    /**
     * 接收数据
     */
    @Test
    public void receive(){
        Object o = rabbitTemplate.receiveAndConvert("zh.news");
        System.out.println(o.getClass());
        System.out.println(o);

    }

    /**
     * 广播
     */
    @Test
    public void sendMsg(){
        rabbitTemplate.convertAndSend("exchange.fanout","",new Book(3,"红楼梦"));
    }
}
