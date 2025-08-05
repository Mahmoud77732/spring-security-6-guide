package com.hegazy.ssecuritypart21.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hegazy.ssecuritypart21.model.Customer;
import com.hegazy.ssecuritypart21.repo.CustomerRepo;

import java.util.Optional;

@RestController
public class UserController {

    private final CustomerRepo customerRepo;


    public UserController(CustomerRepo customerRepo) 
    {
        this.customerRepo = customerRepo;
    }
    

    @RequestMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication) {
        Optional<Customer> optionalCustomer = customerRepo.findByEmail(authentication.getName());
        return optionalCustomer.orElse(null);
    }


}
