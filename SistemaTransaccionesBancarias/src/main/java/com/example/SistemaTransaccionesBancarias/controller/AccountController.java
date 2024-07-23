package com.example.SistemaTransaccionesBancarias.controller;

import com.example.SistemaTransaccionesBancarias.model.Account;
import com.example.SistemaTransaccionesBancarias.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/account")
public class AccountController {

    private AccountService accountService;

    @Autowired
    @PostMapping()
    public Account accountOpening(@RequestBody Account account) {
        return accountService.accountOpening(account);
    }

    @GetMapping()
    public Account checkAccount(@PathVariable Long accountNumber) {
        return accountService.checkAccount(accountNumber).orElse(null);
    }

    @PostMapping()
    public void deposit(@RequestParam Long accountNumber, @RequestParam Double amount) {
        accountService.deposit(accountNumber, amount);
    }




}
