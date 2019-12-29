package com.example.Hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * In this class we receive and display
 * the message from the queue
 *
 * @author Farzan & amirhosein
 */
@Component
public class Consumer {

    static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @RabbitListener(queues = RabbitConfig.queueName)
    public void receive(SendMessageProto message) {
        System.out.println(message.text);
        logger.info("Message Received: "+message);
    }

}
