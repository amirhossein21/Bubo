package com.Bubo.bubo.app.messageParent;

import com.Bubo.bubo.concern.notFoundException.NotFoundException;
import com.Bubo.bubo.concern.Check;
import com.Bubo.bubo.concern.requestBodyException.RequestBodyException;
import com.Bubo.bubo.concern.uuidException.UuidException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * This service defines the functions
 * used in the messageParent controller.
 *
 * @author Farzan & amirhosein
 */
@Service
public class MessageParentService {


    private final MessageParentRepository messageParentRepository;

    MessageParentService(MessageParentRepository mrp){
        this.messageParentRepository=mrp;
    }

    //get one messageParent
    /**
     * finding a messageParent by its uuid
     * @param id
     * @return a messageParent information
     */
     MessageParent getMessageParent(String id){
        try {
            UUID uuid = UUID.fromString(id);
            return messageParentRepository.findByUUID(uuid).orElseThrow(() -> new NotFoundException("messageParent_not_found"));
        }catch (Exception e){
            throw new UuidException("uuid_is_invalid");
        }
        }

    //geting all messageParentes
    /**
     * finding all messageParents
     * @return list of messageparents
     */
    List<MessageParent> getAllMessageParent(){
        return messageParentRepository.findAll();
    }

    //create messageParent
    MessageParent createMessageParent(MessageParent messageParent){
        checkCreation(messageParent);
        return messageParentRepository.save(messageParent);

    }

    //checking messageParent fields
    /**
     * create new messageParent with this parameters:
     * text and uuid
     * @param messageParent
     * @return new messageParent information
     */
    private void checkCreation(MessageParent messageParent) {

        if (Check.checkNullOrEmpty(messageParent.getText())) {
            throw new RequestBodyException("text_is_invalid");
        }

        if (Check.checkNull(messageParent.getUuid())) {
            throw new RequestBodyException("uuid_is_invalid");
        }


    }

    //deleting one messageParent
    /**
     * deleting a messageParent by its ID
     * @param id
     */
    void deleteOne(String id){
        messageParentRepository.delete(getMessageParent(id));
    }

    //deleting all messageParent
    /**
     * deleting all messageParents
     */
    void deleteAll(){
        messageParentRepository.deleteAll();
    }

    //edit messageparents
    /**
     * editing a messageParent information
     * @param id
     * @param newMessageParent
     * @return new messageParent information
     */
    MessageParent editMessageParent(String id,MessageParent newMessageParent){

        checkCreation(newMessageParent);
        MessageParent messageParent=getMessageParent(id);

        messageParent.setText(newMessageParent.getText());
        messageParent.setUuid(newMessageParent.getUuid());

        return messageParentRepository.save(messageParent);
    }

}


