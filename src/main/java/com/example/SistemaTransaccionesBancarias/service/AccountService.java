package com.example.SistemaTransaccionesBancarias.service;

import com.example.SistemaTransaccionesBancarias.exceptions.AccountNotFoundException;
import com.example.SistemaTransaccionesBancarias.exceptions.InsufficientFundsException;
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
        } else {
            throw new AccountNotFoundException("Account not found");
        }
    }

    public void transfer(Long accountNumberFrom, Long accountNumberTo, Double amount) {
        Account accountFrom = accountRepository.findById(accountNumberFrom)
                .orElseThrow(() -> new AccountNotFoundException("Account not found: " + accountNumberFrom));
        Account accountTo = accountRepository.findById(accountNumberTo)
                .orElseThrow(() -> new AccountNotFoundException("Account not found: " + accountNumberTo));

        if (accountFrom.getBalance() < amount) {
            throw new InsufficientFundsException("Insufficient funds in account: " + accountNumberFrom);
        }

        accountFrom.setBalance(accountFrom.getBalance() - amount);
        accountTo.setBalance(accountTo.getBalance() + amount);
        accountRepository.save(accountFrom);
        accountRepository.save(accountTo);
    }

    public void blockAccount(Long accountNumber) {
        Account account = accountRepository.findById(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found: " + accountNumber));
        account.setBlocked(true);
        accountRepository.save(account);
    }

    public void unblockAccount(Long accountNumber) {
        Account account = accountRepository.findById(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found: " + accountNumber));
        account.setBlocked(false);
        accountRepository.save(account);
    }


}
