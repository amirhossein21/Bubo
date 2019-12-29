package com.Bubo.bubo.app.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 *In this class we perform tasks such as
 * getting, adding, deleting and editing user
 * information using relevant methods.
 *
 * @author farzan & amirhosein
 */
@RestController
@RequestMapping("Customer")
public class CustomerController {

    private final CustomerService customerService;

    //constructor
    @Autowired
    public CustomerController(CustomerService us) {
        this.customerService = us;
    }

    /**
     * finding a customer by its uuid
     * @param id
     * @return a customer
     */
    @GetMapping("{id}")
    ResponseEntity one(@PathVariable(value = "id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomer(id));
    }

    /**
     * find all customers
     * @return list of customer
     */
    @GetMapping("")
    ResponseEntity all(){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getAllCustomer());
    }

    /**
     * create a new customer with this parameters:
     * name and phoneNumber and uuid
     * @param customer
     * @return new customer information
     */
    @PostMapping("")
    ResponseEntity create(@RequestBody Customer customer){
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(customer));
    }

    /**
     * delete a customer by its id
     * @param id
     */
    @DeleteMapping("{id}")
    ResponseEntity deleteOne(@PathVariable String id){
        customerService.deleteOne(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    /**
     * delete all customers
     */
    @DeleteMapping("")
    ResponseEntity deleteAll(){
        customerService.deleteAll();
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    /**
     * edit a customer information like
     * name or phoneNumber by its id
     * @param id
     * @param customer
     * @return new customer information
     */
    @PutMapping("{id}")
    ResponseEntity editCustomer(@PathVariable String id,@RequestBody Customer customer){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.editCustomer(id, customer));
    }

}
