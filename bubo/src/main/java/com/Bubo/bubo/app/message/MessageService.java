package com.Bubo.bubo.app.message;

import com.Bubo.bubo.app.customer.Customer;
import com.Bubo.bubo.app.customer.CustomerRepository;
import com.Bubo.bubo.app.message.proto.MessageCreationProto;
import com.Bubo.bubo.app.message.proto.SendMessageProto;
import com.Bubo.bubo.app.messageParent.MessageParent;
import com.Bubo.bubo.app.messageParent.MessageParentRepository;
import com.Bubo.bubo.app.user.User;
import com.Bubo.bubo.app.user.UserRepository;
import com.Bubo.bubo.concern.Check;
import com.Bubo.bubo.concern.notFoundException.NotFoundException;
import com.Bubo.bubo.concern.requestBodyException.RequestBodyException;
import com.Bubo.bubo.concern.uuidException.UuidException;
import com.Bubo.bubo.rabbitMQ.RabbitConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * This service defines the functions
 * used in the message controller.
 *
 * @author Farzan & amirhosein
 */
@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final MessageParentRepository messageParentRepository;
    private final CustomerRepository customerRepository;



    @Autowired
    MessageService(MessageRepository mr,UserRepository ur,MessageParentRepository mpr,CustomerRepository c){
        this.messageRepository=mr;
        this.userRepository=ur;
        this.messageParentRepository=mpr;
        this.customerRepository=c;
    }


    //get one message
    /**
     * finding a message by its uuid
     * @param id
     * @return a message information
     */
    Message getMessage(String id){
        try {
            UUID uuid=UUID.fromString(id);
            return messageRepository.findByUUID(uuid).orElseThrow(() -> new NotFoundException("message_not_found"));
        }catch (Exception e){
            throw new UuidException("uuid_is_invalid");
        }
        }

    //get all messages
    /**
     * finding all messages
     * @return list of messages
     */
    List<Message> getAllMessage(){
        return messageRepository.findAll();
    }

    //create new message
    /**
     * create new message with this parameters:
     * date, text, customer ID, user ID, uuid and message parent
     * @param messageProto
     * @return new message information
     */
    Message createMessage(MessageCreationProto messageProto){
        checkCreation(messageProto);
        Message message=createFromProto(messageProto);
        return messageRepository.save(message);
    }


    //create message from messageProto

    /**
     * this method create a message from
     * messageProto
     * @param messageProto
     */
    private Message createFromProto(MessageCreationProto messageProto){
        Message message=new Message();

        message.setCustomerID(findCustomer(messageProto.getCustomerID()).getId());
        message.setUserID(findUser(messageProto.getUserID()).getId());
        message.setMessageParent(findMessageParent(messageProto.getMessageParentID()));
        message.setUuid(messageProto.getId());

        MessageParent mp=findMessageParent(messageProto.getMessageParentID());
        message.setText(createText(mp.getText(),messageProto.getParams()));
        message.setDate(new Date());

        return message;
    }


    /**
     * create text of message object
     * @param parentText
     * @param params
     */
    private String createText(String parentText,List<String> params){
        String text=parentText;
        for (int i=0;i<params.size();i++){
            text=text.replace(";"+i+";",params.get(i));
        }

        return text;
    }

    /**
     * finding user by its uuid
     * @param id
     */
    private User findUser(UUID id){
        return userRepository.findByUUID(id).orElseThrow(()-> new NotFoundException("user_not_found"));
    }

    /**
     * finding a customer by its uuid
     * @param id
     */
    private Customer findCustomer(UUID id){
         return customerRepository.findByUUID(id).orElseThrow(()-> new NotFoundException("customer_not_found"));
    }

    /**
     * finding messageParent by its uuid
     * @param id
     */
    private MessageParent findMessageParent(UUID id){
        return messageParentRepository.findByUUID(id).orElseThrow(()-> new NotFoundException("messageParent_not_found"));
    }


    /**
     * checking message fields
     * @param messageProto
     */
    private void checkCreation(MessageCreationProto messageProto) {

        if (Check.checkNull(messageProto.getCustomerID())) {
            throw new RequestBodyException("customerID_is_invalid");
        }
        if (Check.checkNull(messageProto.getMessageParentID())) {
            throw new RequestBodyException("messageParentID_is_invalid");
        }
        if (Check.checkNull(messageProto.getUserID())) {
            throw new RequestBodyException("userID_is_invalid");
        }
        if (Check.checkNull(messageProto.getId())) {
            throw new RequestBodyException("id_is_invalid");
        }
        if (Check.checkNull(messageProto.getParams())) {
            throw new RequestBodyException("params_is_invalid");
        }



       // if one param was null or empty throw exception
        for (int i=0;i<messageProto.getParams().size();i++){
            if (Check.checkNullOrEmpty(messageProto.getParams().get(i))) {
                throw new RequestBodyException("param_is_invalid");
            }
        }

    }

    // deleting one message
    /**
     * deleting a message by its ID
     * @param id
     */
    void deleteOne(String id){
        messageRepository.delete(getMessage(id));
    }

    // deleting all messages
    /**
     * deleting all messages
     */
    void deleteAll(){
        messageRepository.deleteAll();
    }

    /**
     * editing a message information
     * @param id
     * @param messageProto
     * @return new message information
     */
    Message editMessage(String id,MessageCreationProto messageProto){

        checkCreation(messageProto);
        Message message=getMessage(id);

        message.setCustomerID(findCustomer(messageProto.getCustomerID()).getId());
        message.setUserID(findUser(messageProto.getUserID()).getId());
        message.setMessageParent(findMessageParent(messageProto.getMessageParentID()));

        MessageParent mp=findMessageParent(messageProto.getMessageParentID());
        message.setText(createText(mp.getText(),messageProto.getParams()));

        message.setDate(new Date());

        return messageRepository.save(message);
    }


}
