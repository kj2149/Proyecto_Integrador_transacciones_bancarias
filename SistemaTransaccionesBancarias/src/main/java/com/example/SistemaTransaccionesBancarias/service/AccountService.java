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
   public Optional<Account>checkAccount(Long accountNumber){
        return accountRepository.findById(accountNumber);
   }

    public void deposit(Long accountNumber, Double amount) {
        Optional<Account> accountOpt = accountRepository.findById(accountNumber);
        if (accountOpt.isPresent()) {
            Account account = accountOpt.get();
            account.setBalance(account.getBalance() + amount);
            accountRepository.save(account);
        }
    }









}
