package com.ming.rabbitmq.example.config.constant;

/**
 * 交换机类型
 *
 * @author : Liu Zeming
 * @date : 2019-05-20 14:42
 */
public enum ExchangeType {

    /**
     * 交换机类型：direct
     */
    DIRECT("direct"),

    /**
     * 交换机类型：fanout
     */
    FANOUT("fanout"),

    /**
     * 交换机类型：topic
     */
    TOPIC("topic"),

    /**
     * 交换机类型：headers
     */
    HEADERS("headers");

    private final String type;

    private ExchangeType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

}
