package com.Bubo.bubo.app.user;

import com.Bubo.bubo.app.user.UserRepository;
import com.Bubo.bubo.concern.Check;
import com.Bubo.bubo.concern.notFoundException.NotFoundException;
import com.Bubo.bubo.concern.requestBodyException.RequestBodyException;
import com.Bubo.bubo.concern.uuidException.UuidException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * This service defines the functions
 * used in the user controller.
 *
 * @author Farzan & amirhosein
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    UserService(UserRepository ur){
        this.userRepository =ur;
    }

    //get one user
    /**
     * finding a user by its uuid
     * @param id
     * @return a user information
     */
    User getUser(String id){

        try{
            UUID uuid=UUID.fromString(id);
            return userRepository.findByUUID(uuid).orElseThrow(()-> new NotFoundException("user_not_found"));
        }catch(Exception e){
            throw new UuidException("uuid_is_invalid");
        }

    }

    //get all user
    /**
     * finding all users
     * @return list of users
     */
    List<User> getAllUsers(){
        return userRepository.findAll();
    }


    //create new user
    /**
     * create new user with this parameters:
     * name, uuid and phoneNumber
     * @param user
     * @return new user information
     */
    User createUser(User user){
        checkCreation(user);
        return userRepository.save(user);

    }

    //checking user fields

    /**
     * checking user fields
     * @param user
     */
    private void checkCreation(User user) {
        if (Check.checkNullOrEmpty(user.getName())) {
            throw new RequestBodyException("name_is_invalid");
        }
        if (Check.checkNull(user.getPhoneNumber())) {
            throw new RequestBodyException("phoneNumber_is_invalid");
        }
        if (Check.checkNull(user.getUuid())) {
            throw new RequestBodyException("uuid_is_invalid");
        }


    }
    //deleting one user
    /**
     * deleting a user by its ID
     * @param id
     */
    void deleteOne(String id){
        userRepository.delete(getUser(id));
    }

    //deleting all users
    /**
     * deleting all messages
     */
    void deleteAll(){
        userRepository.deleteAll();
    }

    //editing user information
    /**
     * editing a user information
     * @param id
     * @param newUser
     * @return new user information
     */
    User editUser(String id, User newUser){

        checkCreation(newUser);
        User user =getUser(id);

        user.setName(newUser.getName());
        user.setPhoneNumber(newUser.getPhoneNumber());
        user.setUuid(newUser.getUuid());

        return userRepository.save(user);
    }

}
