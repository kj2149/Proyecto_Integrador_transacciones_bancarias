package com.example.SistemaTransaccionesBancarias.service;

import com.example.SistemaTransaccionesBancarias.model.Transaction;
import com.example.SistemaTransaccionesBancarias.repository.PocketRepository;
import com.example.SistemaTransaccionesBancarias.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction registerTransaction(Transaction transaccion) {
        return transactionRepository.save(transaccion);
    }

    public List<Transaction> getTransaction() {
        return transactionRepository.findAll();
    }
}