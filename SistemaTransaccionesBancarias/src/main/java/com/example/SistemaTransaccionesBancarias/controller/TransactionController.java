package com.example.SistemaTransaccionesBancarias.controller;

import com.example.SistemaTransaccionesBancarias.model.Transaction;
import com.example.SistemaTransaccionesBancarias.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public Transaction registrarTransaction(@RequestBody Transaction transaction) {
        return transactionService.registerTransaction(transaction);
    }

    @GetMapping
    public List<Transaction> getTransaction() {
        return transactionService.getTransaction();
    }
}

