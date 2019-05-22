package com.ming.rabbitmq.example.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * QueueConfig
 *
 * @author : Liu Zeming
 * @date : 2019-05-22 11:09
 */
@Configuration
public class QueueConfig {

    @Bean("testQueue")
    public Queue testQueue() {
        /**
         * durable="true" 持久化 rabbitmq重启的时候不需要创建新的队列
         *  auto-delete 表示消息队列没有在使用时将被自动删除 默认是false
         * exclusive  表示该消息队列是否只在当前connection生效,默认是false
         */
        return new Queue("testQueue", true, false, false);
    }

}
