package com.example.SistemaTransaccionesBancarias.controller;

import com.example.SistemaTransaccionesBancarias.model.Pocket;
import com.example.SistemaTransaccionesBancarias.service.PocketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@Tag(name = "Sistema Transacciones Bancarias resource")
@RestController
@RequestMapping("api/v1/pocket")
public class PocketController {

    private final PocketService pocketService;

    @Autowired
    public PocketController(PocketService pocketService) {
        this.pocketService = pocketService;
    }

    @Operation(summary = "post in DB a pocket given a pocket from body")
    @PostMapping("/open")
    public ResponseEntity<Pocket> openPocket(@RequestBody Pocket pocket) {
        Pocket newPocket = pocketService.pocketOpening(pocket);
        return ResponseEntity.ok(newPocket);
    }

    @Operation(summary = "get a pocket given a pocket name")
    @GetMapping("/check/{pocketNumber}")
    public ResponseEntity<Pocket> checkPocket(@PathVariable Long pocketNumber) {
        Optional<Pocket> pocket = pocketService.checkPocket(pocketNumber);
        return pocket.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "post in DB a pocket given a pocket from body")
    @PostMapping("/transfer/{pocketNumber}")
    public ResponseEntity<String> transfer(@PathVariable Long pocketNumber, @RequestBody Double amount) {
        try {
            pocketService.transfer(pocketNumber, amount);
            return ResponseEntity.ok("Transfer successful");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Transfer failed");
        }
    }
}
