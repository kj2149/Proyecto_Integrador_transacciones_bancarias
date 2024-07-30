package com.example.SistemaTransaccionesBancarias;

import com.example.SistemaTransaccionesBancarias.model.Pocket;
import com.example.SistemaTransaccionesBancarias.repository.PocketRepository;
import com.example.SistemaTransaccionesBancarias.service.PocketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PocketServiceTest {

    private PocketService pocketService;
    private PocketRepository pocketRepository;

    @BeforeEach
    public void setUp() {

        pocketRepository = mock(PocketRepository.class);
        pocketService = new PocketService(pocketRepository);
    }

    @Test
    public void whenPocketOpening_thenSavePocket() {

        Pocket pocket = new Pocket();
        when(pocketRepository.save(any(Pocket.class))).thenReturn(pocket);

        Pocket result = pocketService.pocketOpening(pocket);

        verify(pocketRepository).save(pocket);
        assertEquals(pocket, result);
    }

    @Test
    public void whenCheckPocket_thenFindById() {

        Long pocketNumber = 1L;
        Optional<Pocket> pocketOptional = Optional.of(new Pocket()); // Asume que Pocket es una clase con sus propiedades
        when(pocketRepository.findById(pocketNumber)).thenReturn(pocketOptional);

        Optional<Pocket> result = pocketService.checkPocket(pocketNumber);

        verify(pocketRepository).findById(pocketNumber);
        assertTrue(result.isPresent());
        assertEquals(pocketOptional, result);
    }

    @Test
    public void whenTransfer_thenUpdateBalance() {

        Long pocketNumber = 1L;
        Double amount = 100.0;
        Pocket pocket = new Pocket();
        pocket.setBalance(200.0);
        when(pocketRepository.findById(pocketNumber)).thenReturn(Optional.of(pocket));

        pocketService.transfer(pocketNumber, amount);


        verify(pocketRepository).findById(pocketNumber);
        verify(pocketRepository).save(pocket);
        assertEquals(300.0, pocket.getBalance());
    }

}