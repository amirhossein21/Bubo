package com.Bubo.bubo.app.messageParent;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * In this class we perform tasks such as
 * getting, adding, deleting and editing messageParent
 * information using relevant methods.
 *
 * @author farzan & amirhosein
 */
@RestController
@RequestMapping("MessageParent")
public class MessageParentController {

    private final MessageParentService messageParentService;

    //constructor
    @Autowired
    public MessageParentController(MessageParentService mps) {
        this.messageParentService = mps;
    }

    /**
     * finding a messageParent by its uuid
     * @param id
     * @return a messageParent information
     */
    @GetMapping("{id}")
     ResponseEntity one(@PathVariable(value = "id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(messageParentService.getMessageParent(id));
    }

    /**
     * finding all messageParents
     * @return list of messageparents
     */
    @GetMapping("")
    ResponseEntity all(){
        return ResponseEntity.status(HttpStatus.OK).body(messageParentService.getAllMessageParent());
    }

    /**
     * create new messageParent with this parameters:
     * text and uuid
     * @param messageParent
     * @return new messageParent information
     */
    @PostMapping("")
    ResponseEntity create(@RequestBody MessageParent messageParent){
        return ResponseEntity.status(HttpStatus.CREATED).body(messageParentService.createMessageParent(messageParent));
    }

    /**
     * deleting a messageParent by its ID
     * @param id
     */
    @DeleteMapping("{id}")
    ResponseEntity deleteOne(@PathVariable String id){
        messageParentService.deleteOne(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    /**
     * deleting all messageParents
     */
    @DeleteMapping("")
    ResponseEntity deleteAll(){
        messageParentService.deleteAll();
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    /**
     * editing a messageParent information
     * @param id
     * @param messageParent
     * @return new messageParent information
     */
    @PutMapping("{id}")
    ResponseEntity editMessageParent(@PathVariable String id,@RequestBody MessageParent messageParent){
        return ResponseEntity.status(HttpStatus.OK).body(messageParentService.editMessageParent(id,messageParent));
    }


}
