package com.example.SistemaTransaccionesBancarias.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pocket")
public class Pocket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pocket_number")
    private Long pocketNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "balance")
    private Double balance;

    @ManyToOne
    @JoinColumn(name = "account_number", referencedColumnName = "account_number")
    private Account account;

    @OneToMany(mappedBy = "pocket", cascade = CascadeType.ALL)
    private List<Transaction> transaction = new ArrayList<>();

    public Pocket() {

    }

    public Pocket(Long pocketNumber, String name, Double balance) {
        this.pocketNumber = pocketNumber;
        this.name = name;
        this.balance = balance;
    }

    public Long getPocketNumber() {
        return pocketNumber;
    }

    public void setPocketNumber(Long pocketNumber) {
        this.pocketNumber = pocketNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
