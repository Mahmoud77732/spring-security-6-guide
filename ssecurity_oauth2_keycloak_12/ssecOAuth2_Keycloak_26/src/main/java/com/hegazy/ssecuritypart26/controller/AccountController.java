package com.hegazy.ssecuritypart26.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hegazy.ssecuritypart26.model.Accounts;
import com.hegazy.ssecuritypart26.model.Customer;
import com.hegazy.ssecuritypart26.repo.AccountsRepository;
import com.hegazy.ssecuritypart26.repo.CustomerRepository;

@RestController
public class AccountController {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepo;

    public AccountController(AccountsRepository accountsRepository, CustomerRepository customerRepo) {
        this.accountsRepository = accountsRepository;
        this.customerRepo = customerRepo;
    }

    @GetMapping("/myAccount")
    public Accounts getAccountDetails(@RequestParam String email) {
        Optional<Customer> optionalCustomer = customerRepo.findByEmail(email);
        if(optionalCustomer.isPresent()) {
            Accounts accounts = accountsRepository.findByCustomerId(optionalCustomer.get().getId());
            if(accounts != null) {
                return accounts;
            }
            else{
                return null;
            }
        } else {
            return null;

        }
    }

}
