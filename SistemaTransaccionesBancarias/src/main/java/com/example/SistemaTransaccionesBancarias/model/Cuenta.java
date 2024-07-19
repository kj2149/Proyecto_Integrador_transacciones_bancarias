package com.example.SistemaTransaccionesBancarias.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cuenta")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_Number")
    private Long accountNumber;

    @Column(name = "owner_name")
    private String ownerName;

    @Column(name ="")




}
