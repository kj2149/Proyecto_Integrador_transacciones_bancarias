package com.example.SistemaTransaccionesBancarias;

import com.example.SistemaTransaccionesBancarias.model.Transaction;
import com.example.SistemaTransaccionesBancarias.repository.TransactionRepository;
import com.example.SistemaTransaccionesBancarias.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransactionServiceTest {

    private TransactionService transactionService;
    private TransactionRepository transactionRepository;

    @BeforeEach
    public void setUp() {
        transactionRepository = mock(TransactionRepository.class);
        transactionService = new TransactionService(transactionRepository);
    }

    @Test
    public void whenRegisterTransaction_thenSaveTransaction() {

        Transaction transaction = new Transaction(1L, "Deposit", 100.0, LocalDate.now());
        when(transactionRepository.save(any(Transaction.class))).thenAnswer(invocation -> {
            Transaction savedTransaction = invocation.getArgument(0);

            ReflectionTestUtils.setField(savedTransaction, "transactionId", 1L);
            return savedTransaction;
        });

        Transaction savedTransaction = transactionService.registerTransaction(transaction);

        assertNotNull(savedTransaction.getTransactionId());
        verify(transactionRepository).save(transaction);
        assertEquals(transaction.getType(), savedTransaction.getType());
        assertEquals(transaction.getAmount(), savedTransaction.getAmount());
        assertEquals(transaction.getDate(), savedTransaction.getDate());
    }

    @Test
    public void whenGetTransaction_thenReturnAllTransactions() {

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(1L, "Deposit", 100.0, LocalDate.now()));
        transactions.add(new Transaction(1L, "Deposit", 100.0, LocalDate.now()));
        when(transactionRepository.findAll()).thenReturn(transactions);

        List<Transaction> resultTransactions = transactionService.getTransaction();

        verify(transactionRepository).findAll();
        assertEquals(transactions.size(), resultTransactions.size());
    }
}