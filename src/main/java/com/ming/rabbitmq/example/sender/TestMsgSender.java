package com.ming.rabbitmq.example.sender;

import com.ming.rabbitmq.example.config.constant.Constants;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * TestMsgSender
 *
 * @author : Liu Zeming
 * @date : 2019-05-22 14:18
 */
@Component
public class TestMsgSender {

    @Autowired
    private RabbitTemplate testRabbitTemplate;

    public void send(String uuid, Object message) {
        CorrelationData correlationId = new CorrelationData(uuid);
        testRabbitTemplate.convertAndSend(Constants.EXCHANGE_NAME, Constants.ROUTING_KEY, message, correlationId);
    }

}
