/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hegazy.ssecurity.user_in_custom_db_5.controller;

import com.hegazy.ssecurity.user_in_custom_db_5.model.Customer;
import com.hegazy.ssecurity.user_in_custom_db_5.repository.CustomerRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mahmoud
 */
@RestController
public class UserController {
    
    private final CustomerRepo customerRepo;
    private final PasswordEncoder passEncoder;


    public UserController(CustomerRepo customerRepo, PasswordEncoder passEncoder) {
        this.customerRepo = customerRepo;
        this.passEncoder = passEncoder;
    }
    
    
    /*
        {
            "email": "user1@gmail.com",
            "pwd": "User-2@12345",
            "role": "read"
        }
    */
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer){
        try{
            String hashPass = passEncoder.encode(customer.getPwd());
            customer.setPwd(hashPass);
            Customer savedCustomer = customerRepo.save(customer);

            if(savedCustomer.getId() > 0){
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body("Given user details are successfully registered!");
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("User registration failed!");
            }
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occured: "+ ex.getMessage());
        }
    }
    
}
