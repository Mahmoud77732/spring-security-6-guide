package com.hegazy.ssecuritypart26.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hegazy.ssecuritypart26.model.Customer;
import com.hegazy.ssecuritypart26.model.Loans;
import com.hegazy.ssecuritypart26.repo.CustomerRepository;
import com.hegazy.ssecuritypart26.repo.LoanRepository;

@RestController
public class LoansController {

    private final LoanRepository loanRepository;
    private final CustomerRepository customerRepository;

    public LoansController(LoanRepository loanRepository, CustomerRepository customerRepository) {
        this.loanRepository = loanRepository;
        this.customerRepository = customerRepository;
    }

    @GetMapping("/myLoans")
    @PostAuthorize("hasRole('USER')")
    public List<Loans> getLoanDetails(@RequestParam String email) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);
        if (optionalCustomer.isPresent()) {
            List<Loans> loans = loanRepository.findByCustomerIdOrderByStartDtDesc(optionalCustomer.get().getId());
            if (loans != null) {
                return loans;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

}