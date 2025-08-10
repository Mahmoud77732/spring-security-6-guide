package com.hegazy.ssecuritypart26.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hegazy.ssecuritypart26.model.AccountTransactions;
import com.hegazy.ssecuritypart26.model.Customer;
import com.hegazy.ssecuritypart26.repo.AccountTransactionsRepository;
import com.hegazy.ssecuritypart26.repo.CustomerRepository;

@RestController
public class BalanceController {

    private final AccountTransactionsRepository accountTrxRepo;
    private final CustomerRepository customerRepository;

    public BalanceController(AccountTransactionsRepository accountTrxRepo, CustomerRepository customerRepository) {
        this.accountTrxRepo = accountTrxRepo;
        this.customerRepository = customerRepository;
    }

    @GetMapping("/myBalance")
    public List<AccountTransactions> getBalanceDetails(@RequestParam String email) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);
        if (optionalCustomer.isPresent()) {
            List<AccountTransactions> accountTransactions = accountTrxRepo.
                    findByCustomerIdOrderByTransactionDtDesc(optionalCustomer.get().getId());
            if (accountTransactions != null) {
                return accountTransactions;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

}