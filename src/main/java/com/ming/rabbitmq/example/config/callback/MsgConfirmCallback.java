package com.ming.rabbitmq.example.config.callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;

/**
 * MsgSendConfimCallback
 *
 * @author : Liu Zeming
 * @date : 2019-05-22 14:09
 */
public class MsgConfirmCallback implements ConfirmCallback {

    private static final Logger logger = LoggerFactory.getLogger(MsgConfirmCallback.class);

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            logger.info("消息消费成功！" + "MsgSendConfirmCallBack  , 回调id:" + correlationData.toString());
        } else {
            logger.info("MsgSendConfirmCallBack  , 回调id:" + correlationData.toString() + " 。 消息消费失败:" + cause + "重新发送");
        }
    }

}
