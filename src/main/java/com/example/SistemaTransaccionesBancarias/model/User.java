package com.example.SistemaTransaccionesBancarias.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id" )
    private Long userId;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private Double password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Account account;

    public User(){

    }

    public User(Long userId, String name, Double password) {
        this.userId = userId;
        this.name = name;
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPassword() {
        return password;
    }

    public void setPassword(Double password) {
        this.password = password;
    }
}
