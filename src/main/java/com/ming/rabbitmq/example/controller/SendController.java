package com.ming.rabbitmq.example.controller;

import com.ming.rabbitmq.example.sender.TestMsgSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * SendController
 *
 * @author : Liu Zeming
 * @date : 2019-04-29 16:15
 */
@RestController
public class SendController {

    private static final Logger logger = LoggerFactory.getLogger(SendController.class);

    @Autowired
    private TestMsgSender testMsgSender;

    @GetMapping("/send")
    public String send(String message) {
        String uuid = UUID.randomUUID().toString();
        testMsgSender.send(uuid, message);
        return uuid;
    }

}
