package com.Bubo.bubo.app.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * In this class we perform tasks such as
 * getting, adding, deleting and editing user
 * information using relevant methods.
 *
 * @author farzan & amirhosein
 */
@RestController
@RequestMapping(path = "/users")
public class UserControllerNew {


    private final UserService userService;

    //constructor
    @Autowired
    public UserControllerNew(UserService us) {
        this.userService= us;
    }


    /**
     * finding a user by its uuid
     * @param id
     * @return a user information
     */
    @GetMapping("{id}")
    ResponseEntity one(@PathVariable(value = "id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(id));
    }

    /**
     * finding all users
     * @return list of users
     */
    @GetMapping("")
    ResponseEntity all(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    /**
     * create new user with this parameters:
     * name, uuid and phoneNumber
     * @param user
     * @return new user information
     */
    @PostMapping("")
    ResponseEntity create(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }

    /**
     * deleting a user by its ID
     * @param id
     */
    @DeleteMapping("{id}")
    ResponseEntity deleteOne(@PathVariable String id){
        userService.deleteOne(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    /**
     * deleting all messages
     */
    @DeleteMapping("")
    ResponseEntity deleteAll(){
        userService.deleteAll();
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    /**
     * editing a user information
     * @param id
     * @param user
     * @return new user information
     */
    @PutMapping("{id}")
    ResponseEntity editUser(@PathVariable String id,@RequestBody User user){
        return ResponseEntity.status(HttpStatus.OK).body(userService.editUser(id, user));
    }


}
