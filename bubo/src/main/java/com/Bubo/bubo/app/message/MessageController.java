package com.Bubo.bubo.app.message;


import com.Bubo.bubo.app.message.proto.MessageCreationProto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * In this class we perform tasks such as
 * getting, adding, deleting and editing message
 * information using relevant methods.
 *
 * @author farzan & amirhosein
 */
@RestController
@RequestMapping("Message")
public class MessageController {
    private final MessageService messageService;

    //constructor
    @Autowired
    public MessageController(MessageService ms) {
        this.messageService = ms;
    }


    /**
     * finding a message by its uuid
     * @param id
     * @return a message information
     */
    @GetMapping("{id}")
    ResponseEntity one(@PathVariable(value = "id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(messageService.getMessage(id));
    }

    /**
     * finding all messages
     * @return list of messages
     */
    @GetMapping("")
    ResponseEntity all(){
        return ResponseEntity.status(HttpStatus.OK).body(messageService.getAllMessage());
    }

    /**
     * create new message with this parameters:
     * date, text, customer ID, user ID, uuid and message parent
     * @param messageProto
     * @return new message information
     */
    @PostMapping("")
    ResponseEntity create(@RequestBody MessageCreationProto messageProto){
        return ResponseEntity.status(HttpStatus.CREATED).body(messageService.createMessage(messageProto));
    }


    /**
     * deleting a message by its ID
     * @param id
     */
    @DeleteMapping("{id}")
    ResponseEntity deleteOne(@PathVariable String id){
        messageService.deleteOne(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    /**
     * deleting all messages
     */
    @DeleteMapping("")
    ResponseEntity deleteAll(){
        messageService.deleteAll();
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    /**
     * editing a message information
     * @param id
     * @param messageProto
     * @return new message information
     */
    @PutMapping("{id}")
    ResponseEntity editMessage(@PathVariable String id,@RequestBody MessageCreationProto messageProto){
        return ResponseEntity.status(HttpStatus.OK).body(messageService.editMessage(id,messageProto));
    }





}
