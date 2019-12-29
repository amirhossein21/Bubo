package com.Bubo.bubo.rabbitMQ;


import com.Bubo.bubo.app.customer.Customer;
import com.Bubo.bubo.app.customer.CustomerRepository;
import com.Bubo.bubo.app.message.Message;
import com.Bubo.bubo.app.message.MessageRepository;
import com.Bubo.bubo.app.message.proto.SendMessageProto;
import com.Bubo.bubo.concern.notFoundException.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.UUID;

/**
 * In this class, we check from time to time
 * to send unsent messages to the sending queue.
 * And we'll change the status of those messages to Sent
 *
 * @author Farzan & amirhosein
 */
@Configuration
@EnableScheduling
public class Producer {

    private final MessageRepository messageRepository;
    private final CustomerRepository customerRepository;

    //for RabbitMQ
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;


    //constructor
    @Autowired
    Producer(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper,MessageRepository mr,CustomerRepository cr){
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
        this.messageRepository=mr;
        this.customerRepository=cr;
    }

    @Scheduled(fixedRate = 10000)
    public void sendMessage(){
        SendMessageProto sendMessage=new SendMessageProto();

        if (messageRepository.findUnSendMessages().isPresent()){

            List<Message> messages = messageRepository.findUnSendMessages().get();


            for (int i=0;i<messages.size();i++){
                sendMessage.setText(messages.get(i).getText());
                sendMessage.setPhoneNumber(findCustomer(messages.get(i).getCustomerID()).getPhoneNumber());
                sendMessage.setId(messages.get(i).getUuid());
                //TODO: log
                System.out.println("message is sending:");
                this.rabbitTemplate.convertAndSend(RabbitConfig.topicExchangeName,"foo.bar.b",sendMessage);
                messageRepository.updateSentStatus(messages.get(i).getId());

            }
        }

        }

    //find customer
    private Customer findCustomer(Long id){
      return   customerRepository.findById(id).orElseThrow(()-> new NotFoundException("customer_not_found"));
    }

}
