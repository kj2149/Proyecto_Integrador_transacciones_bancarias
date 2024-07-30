package com.example.SistemaTransaccionesBancarias;

import com.example.SistemaTransaccionesBancarias.exceptions.AccountNotFoundException;
import com.example.SistemaTransaccionesBancarias.exceptions.InsufficientFundsException;
import com.example.SistemaTransaccionesBancarias.model.Account;
import com.example.SistemaTransaccionesBancarias.repository.AccountRepository;
import com.example.SistemaTransaccionesBancarias.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class AccountServiceTest {

    private AccountService accountService;
    private AccountRepository accountRepository;

    @BeforeEach
    public void setUp() {
        accountRepository = mock(AccountRepository.class);
        accountService = new AccountService(accountRepository);
    }

    @Test
    public void testAccountOpening() {
        Account account = new Account();
        when(accountRepository.save(account)).thenReturn(account);

        Account result = accountService.accountOpening(account);

        assertEquals(account, result);
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    public void testCheckAccount() {
        Account account = new Account();
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        Optional<Account> result = accountService.checkAccount(1L);

        assertTrue(result.isPresent());
        assertEquals(account, result.get());
        verify(accountRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeposit() {
        Account account = new Account();
        account.setBalance(100.0);
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        accountService.deposit(1L, 50.0);

        assertEquals(150.0, account.getBalance());
        verify(accountRepository, times(1)).findById(1L);
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    public void testDepositAccountNotFound() {
        when(accountRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> accountService.deposit(1L, 50.0));
    }

    @Test
    public void testTransfer() {
        Account accountFrom = new Account();
        accountFrom.setAccountNumber(1L);
        accountFrom.setBalance(100.0);

        Account accountTo = new Account();
        accountTo.setAccountNumber(2L);
        accountTo.setBalance(50.0);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(accountFrom));
        when(accountRepository.findById(2L)).thenReturn(Optional.of(accountTo));

        accountService.transfer(1L, 2L, 50.0);

        assertEquals(50.0, accountFrom.getBalance());
        assertEquals(100.0, accountTo.getBalance());
        verify(accountRepository, times(2)).findById(anyLong());
        verify(accountRepository, times(1)).save(accountFrom);
        verify(accountRepository, times(1)).save(accountTo);
    }

    @Test
    public void testTransferInsufficientFunds() {
        Account accountFrom = new Account();
        accountFrom.setBalance(50.0);

        Account accountTo = new Account();
        accountTo.setBalance(50.0);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(accountFrom));
        when(accountRepository.findById(2L)).thenReturn(Optional.of(accountTo));

        assertThrows(InsufficientFundsException.class, () -> accountService.transfer(1L, 2L, 100.0));
    }

    @Test
    public void testBlockAccount() {
        Account account = new Account();
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        accountService.blockAccount(1L);

        assertTrue(account.isBlocked());
        verify(accountRepository, times(1)).findById(1L);
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    public void testUnblockAccount() {
        Account account = new Account();
        account.setBlocked(true);
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        accountService.unblockAccount(1L);

        assertFalse(account.isBlocked());
        verify(accountRepository, times(1)).findById(1L);
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    public void testBlockAccountNotFound() {
        when(accountRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> accountService.blockAccount(1L));
    }

    @Test
    public void testUnblockAccountNotFound() {
        when(accountRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> accountService.unblockAccount(1L));
    }
}
