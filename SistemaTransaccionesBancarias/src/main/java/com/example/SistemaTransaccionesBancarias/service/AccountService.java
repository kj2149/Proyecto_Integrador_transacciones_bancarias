package com.example.SistemaTransaccionesBancarias.service;

import com.example.SistemaTransaccionesBancarias.model.Account;
import com.example.SistemaTransaccionesBancarias.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account accountOpening(Account account){
        return accountRepository.save(account);
    }
   



}
