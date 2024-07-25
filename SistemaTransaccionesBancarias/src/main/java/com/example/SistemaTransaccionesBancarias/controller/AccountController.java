package com.example.SistemaTransaccionesBancarias.controller;

import com.example.SistemaTransaccionesBancarias.exceptions.AccountNotFoundException;
import com.example.SistemaTransaccionesBancarias.exceptions.InsufficientFundsException;
import com.example.SistemaTransaccionesBancarias.model.Account;
import com.example.SistemaTransaccionesBancarias.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/account")
public class AccountController {
    private AccountService accountService;


    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/open")
    public ResponseEntity<Account> openAccount(@RequestBody Account account) {
        Account newAccount = accountService.accountOpening(account);
        return ResponseEntity.ok(newAccount);
    }

    @GetMapping("/check/{accountNumber}")
    public ResponseEntity<Account> checkAccount(@PathVariable Long accountNumber) {
        Optional<Account> account = accountService.checkAccount(accountNumber);
        return account.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/deposit/{accountNumber}")
    public ResponseEntity<String> deposit(@PathVariable Long accountNumber, @RequestBody Double amount) {
        try {
            accountService.deposit(accountNumber, amount);
            return ResponseEntity.ok("Deposit successful");
        } catch (AccountNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestParam Long from, @RequestParam Long to, @RequestParam Double amount) {
        try {
            accountService.transfer(from, to, amount);
            return ResponseEntity.ok("Transfer successful");
        } catch (AccountNotFoundException | InsufficientFundsException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/block/{accountNumber}")
    public ResponseEntity<String> blockAccount(@PathVariable Long accountNumber) {
        try {
            accountService.blockAccount(accountNumber);
            return ResponseEntity.ok("Account blocked successfully");
        } catch (AccountNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PostMapping("/unblock/{accountNumber}")
    public ResponseEntity<String> unblockAccount(@PathVariable Long accountNumber) {
        try {
            accountService.unblockAccount(accountNumber);
            return ResponseEntity.ok("Account unblocked successfully");
        } catch (AccountNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
