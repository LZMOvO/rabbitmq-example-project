package com.ming.rabbitmq.example.consumer;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * FirstConsumer
 *
 * @author : Liu Zeming
 * @date : 2019-04-29 16:11
 */

@Component
@RabbitListener(queues = "testQueue", containerFactory = "rabbitListenerContainerFactory")
public class Consumer {

    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @RabbitHandler
    public void handleMessage(String hello, Channel channel, Message message) throws Exception {
        try {
            // 告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            logger.info("\n消费消息: hello=" + hello + " ,\nchannel=" + JSON.toJSONString(channel) + " ,\nmessage=" + JSON.toJSONString(message));
        } catch (Exception e) {
            e.printStackTrace();
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            System.out.println("receiver fail");
        }

    }

}
