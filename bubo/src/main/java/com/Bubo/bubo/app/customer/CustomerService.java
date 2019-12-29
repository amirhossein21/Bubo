package com.Bubo.bubo.app.customer;


import com.Bubo.bubo.concern.Check;
import com.Bubo.bubo.concern.notFoundException.NotFoundException;
import com.Bubo.bubo.concern.requestBodyException.RequestBodyException;
import com.Bubo.bubo.concern.uuidException.UuidException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * This service defines the functions
 * used in the customer controller.
 *
 * @author Farzan & amirhosein
 */
@Service
 class CustomerService {


    private final CustomerRepository customerRepository;

    CustomerService(CustomerRepository ur){
        this.customerRepository =ur;
    }

    //get customer

    /**
     * finding a customer by its uuid
     * @param id
     * @return customer information
     */
     Customer getCustomer(String id){

        try{
        UUID uuid=UUID.fromString(id);
            return customerRepository.findByUUID(uuid).orElseThrow(()-> new NotFoundException("customer_not_found"));
        }catch(Exception e){
            throw new UuidException("uuid_is_invalid");
        }

    }
    //get all costomer
    /**
     * find all customers
     * @return list of customer
     */
    List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }

    //create customer
    /**
     * create a new customer with this parameters:
     * name and phoneNumber and uuid
     * @param customer
     * @return new customer information
     */
    Customer createCustomer(Customer customer){
        checkCreation(customer);
        return customerRepository.save(customer);

    }

    //check customer features

    /**
     * checking customer feilds
     * @param customer
     */
    private void checkCreation(Customer customer) {
        if (Check.checkNullOrEmpty(customer.getName())) {
            throw new RequestBodyException("name_is_invalid");
        }
        if (Check.checkNull(customer.getPhoneNumber())) {
            throw new RequestBodyException("phoneNumber_is_invalid");
        }
        if (Check.checkNull(customer.getUuid())) {
            throw new RequestBodyException("uuid_is_invalid");
        }


    }

    //delete a customer
    /**
     * delete a customer by its id
     * @param id
     */
    void deleteOne(String id){
        customerRepository.delete(getCustomer(id));
    }

    //delete all customers
    /**
     * delete all customers
     */
    void deleteAll(){
        customerRepository.deleteAll();
    }

    //edit customer
    /**
     * edit a customer information like
     * name or phoneNumber by its id
     * @param id
     * @param newCustomer
     * @return new customer information
     */
    Customer editCustomer(String id, Customer newCustomer){

        checkCreation(newCustomer);
        Customer customer =getCustomer(id);

        customer.setName(newCustomer.getName());
        customer.setPhoneNumber(newCustomer.getPhoneNumber());
        customer.setUuid(newCustomer.getUuid());

        return customerRepository.save(customer);
    }


}
