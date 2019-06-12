package com.ming.rabbitmq.example.config.callback;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;

/**
 * MsgReturnCallback
 *
 * @author : Liu Zeming
 * @date : 2019-05-22 14:11
 */
public class MsgReturnCallback implements ReturnCallback {

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println("MsgReturnCallback , " + message.toString() + "===replyCode:" + replyCode + "===replyText:"
                + replyText + "===routingKey:" + routingKey);
        // TODO 消息从交换机到队列失败，重新发送消息的处理1234
    }
}
