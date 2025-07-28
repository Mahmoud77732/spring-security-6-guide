package com.hegazy.ssecuritypart16.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hegazy.ssecuritypart16.model.AccountTransactions;
import com.hegazy.ssecuritypart16.repo.AccountTransactionsRepository;

@RestController
public class BalanceController {

    private final AccountTransactionsRepository accountTrxRepo;

    public BalanceController(AccountTransactionsRepository accountTransactionsRepository) {
        this.accountTrxRepo = accountTransactionsRepository;
    }

    @GetMapping("/myBalance")
    public List<AccountTransactions> getBalanceDetails(@RequestParam long id) {
        List<AccountTransactions> accountTransactions = 
            accountTrxRepo.findByCustomerIdOrderByTransactionDtDesc(id);
        if (accountTransactions != null) {
            return accountTransactions;
        } else {
            return null;
        }
    }

}