package com.example.SistemaTransaccionesBancarias.repository;

import com.example.SistemaTransaccionesBancarias.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
}
