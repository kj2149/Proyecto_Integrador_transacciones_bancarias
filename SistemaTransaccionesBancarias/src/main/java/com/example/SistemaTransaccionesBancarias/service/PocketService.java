package com.example.SistemaTransaccionesBancarias.service;

import com.example.SistemaTransaccionesBancarias.model.Pocket;
import com.example.SistemaTransaccionesBancarias.repository.PocketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PocketService {
    private final PocketRepository pocketRepository;

    @Autowired
    public PocketService(PocketRepository pocketRepository) {
        this.pocketRepository = pocketRepository;
    }

    public Pocket pocketOpening(Pocket pocket) {
        return pocketRepository.save(pocket);
    }

    public Optional<Pocket> checkPocket(Long pocketNumber) {
        return pocketRepository.findById(pocketNumber);
    }

    public void transfer(Long pocketNumber, Double amount) {
        Optional<Pocket> pocketOpt = pocketRepository.findById(pocketNumber);
        if (pocketOpt.isPresent()) {
            Pocket pocket = pocketOpt.get();
            pocket.setBalance(pocket.getBalance() + amount);
            pocketRepository.save(pocket);
        }
    }


}
