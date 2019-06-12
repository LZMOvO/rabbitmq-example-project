package com.ming.rabbitmq.example.config;

import com.ming.rabbitmq.example.config.callback.MsgConfirmCallback;
import com.ming.rabbitmq.example.config.callback.MsgReturnCallback;
import com.ming.rabbitmq.example.config.constant.Constants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMqConfig
 *
 * @author : Liu Zeming
 * @date : 2019-05-22 11:09
 */
@Configuration
public class RabbitMqConfig {

    @Autowired
    private Queue testQueue;

    @Autowired
    private DirectExchange testDirectExchange;

    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean
    public Binding testBind() {
        return BindingBuilder.bind(testQueue).to(testDirectExchange).with(Constants.ROUTING_KEY);
    }

//    @Bean
//    public SimpleMessageListenerContainer simpleMessageListenerContainerTest() {
//        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer(connectionFactory);
//        simpleMessageListenerContainer.addQueues(testQueue);
//        simpleMessageListenerContainer.setExposeListenerChannel(true);
//        simpleMessageListenerContainer.setMaxConcurrentConsumers(5);
//        simpleMessageListenerContainer.setConcurrentConsumers(1);
//        // 设置确认模式手工确认
//        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
//        return simpleMessageListenerContainer;
//    }

    @Bean
    public RabbitTemplate testRabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        /**
         * 若使用confirm-callback或return-callback，
         * 必须要配置publisherConfirms或publisherReturns为true
         * 每个rabbitTemplate只能有一个confirm-callback和return-callback
         */
        template.setConfirmCallback(msgConfirmCallback());
        template.setReturnCallback(msgReturnCallback());
        /**
         * 使用return-callback时必须设置mandatory为true，或者在配置中设置mandatory-expression的值为true，
         * 可针对每次请求的消息去确定’mandatory’的boolean值，
         * 只能在提供’return -callback’时使用，与mandatory互斥
         */
        //  template.setMandatory(true);
        return template;
    }

    /**
     * 消息确认机制
     * Confirms给客户端一种轻量级的方式，能够跟踪哪些消息被broker处理，
     * 哪些可能因为broker宕掉或者网络失败的情况而重新发布。
     * 确认并且保证消息被送达，提供了两种方式：发布确认和事务。(两者不可同时使用)
     * 在channel为事务时，不可引入确认模式；同样channel为确认模式下，不可使用事务。
     *
     * @return
     */
    @Bean
    public MsgConfirmCallback msgConfirmCallback() {
        return new MsgConfirmCallback();
    }

    @Bean
    public MsgReturnCallback msgReturnCallback() {
        return new MsgReturnCallback();
    }

}
