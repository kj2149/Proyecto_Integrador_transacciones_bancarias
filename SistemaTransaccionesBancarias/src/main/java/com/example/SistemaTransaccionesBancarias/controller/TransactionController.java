package com.example.SistemaTransaccionesBancarias.controller;

import com.example.SistemaTransaccionesBancarias.model.Transaction;
import com.example.SistemaTransaccionesBancarias.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@Tag(name = "Sistema Transacciones Bancarias resource")
@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @Operation(summary = "post in DB a transaction given a transaction from body")
    @PostMapping
    public Transaction registrarTransaction(@RequestBody Transaction transaction) {
        return transactionService.registerTransaction(transaction);
    }

    @Operation(summary = "get a transaction given a transaction name")
    @GetMapping
    public List<Transaction> getTransaction() {
        return transactionService.getTransaction();
    }
}

