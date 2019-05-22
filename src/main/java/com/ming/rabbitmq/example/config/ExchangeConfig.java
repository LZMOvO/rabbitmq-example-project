package com.ming.rabbitmq.example.config;

import com.ming.rabbitmq.example.config.constant.Constants;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ExchangeConfig
 *
 * @author : Liu Zeming
 * @date : 2019-05-22 11:10
 */
@Configuration
public class ExchangeConfig {

    @Bean
    public DirectExchange testDirectExchange() {
        return new DirectExchange(Constants.EXCHANGE_NAME, true, false);
    }
}
