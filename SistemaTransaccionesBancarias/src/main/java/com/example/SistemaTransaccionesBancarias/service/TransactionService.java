package com.example.SistemaTransaccionesBancarias.service;

import com.example.SistemaTransaccionesBancarias.model.Transaction;
import com.example.SistemaTransaccionesBancarias.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction registerTransaction(Transaction transaccion) {
        return transactionRepository.save(transaccion);
    }

    public List<Transaction> getTransaction() {
        return transactionRepository.findAll();
    }
}