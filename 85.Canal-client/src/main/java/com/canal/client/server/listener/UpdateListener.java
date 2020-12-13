package com.canal.client.server.listener;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * @Author: xy
 * @Date: 2020/12/13 21:21
 */
@RabbitListener(queues = "DATABASE.UPDATE")
public class UpdateListener {

    @RabbitHandler
    public void process(String message){
//        User user = JSON.parseObject(message,User.class);
        System.err.println("事件信息过来了：" + message);
    }

}
