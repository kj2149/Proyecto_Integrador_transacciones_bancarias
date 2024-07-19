package com.example.SistemaTransaccionesBancarias.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table( name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    @Column(name = "transaction_type")
    private String type;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "date")
    private LocalDate date;

    public Transaction(Long transactionId, String type, Double amount, LocalDate date) {
        this.transactionId = transactionId;
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
