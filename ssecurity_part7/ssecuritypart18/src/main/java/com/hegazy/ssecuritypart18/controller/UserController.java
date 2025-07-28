package com.hegazy.ssecuritypart18.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hegazy.ssecuritypart18.model.Customer;
import com.hegazy.ssecuritypart18.repo.CustomerRepo;

import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Date;
import java.util.Optional;


@RestController
public class UserController {

    private final CustomerRepo customerRepo;
    private final PasswordEncoder passwordEncoder;


    public UserController(CustomerRepo customerRepo, PasswordEncoder passwordEncoder) {
        this.customerRepo = customerRepo;
        this.passwordEncoder = passwordEncoder;
    }
    
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer){
        try {
            String hashPwd = passwordEncoder.encode(customer.getPwd());
            customer.setPwd(hashPwd);
            customer.setCreateAt(new Date(System.currentTimeMillis()));
            Customer savedCustomer = customerRepo.save(customer);
            // Check if the customer was saved successfully
            if(savedCustomer.getId() > 0){
                return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("Given user details are successfull registered");
            } else{
                return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("User registeration failed!"); 
            }
        } catch (Exception e) {
            return ResponseEntity.status(
                HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An exception occurred " + e.getMessage()
            );
        }
    }

    @RequestMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication) {
        Optional<Customer> optionalCustomer = customerRepo.findByEmail(authentication.getName());
        return optionalCustomer.orElse(null);
    }

}
