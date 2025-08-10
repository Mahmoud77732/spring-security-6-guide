package com.hegazy.ssecuritypart26.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hegazy.ssecuritypart26.model.Customer;
import com.hegazy.ssecuritypart26.repo.CustomerRepository;

import java.util.Optional;

@RestController
public class UserController {

    private final CustomerRepository customerRepo;


    public UserController(CustomerRepository customerRepo) 
    {
        this.customerRepo = customerRepo;
    }
    

    @RequestMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication) {
        Optional<Customer> optionalCustomer = customerRepo.findByEmail(authentication.getName());
        return optionalCustomer.orElse(null);
    }


}
