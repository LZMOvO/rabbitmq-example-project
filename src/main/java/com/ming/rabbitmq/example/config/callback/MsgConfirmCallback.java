package com.ming.rabbitmq.example.config.callback;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;

/**
 * MsgSendConfimCallback
 *
 * @author : Liu Zeming
 * @date : 2019-05-22 14:09
 */
public class MsgConfirmCallback implements ConfirmCallback {

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            System.out.println("消息消费成功！" + "MsgSendConfirmCallBack  , 回调id:" + correlationData);
        } else {
            System.out.println("MsgSendConfirmCallBack  , 回调id:" + correlationData + " 。 消息消费失败:" + cause + "重新发送");
        }
    }

}
