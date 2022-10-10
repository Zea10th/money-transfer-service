package com.example.moneytransferapp.controller;

import com.example.moneytransferapp.model.Operation;
import com.example.moneytransferapp.model.Transaction;
import com.example.moneytransferapp.service.MoneyTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class MoneyTransferController {

    private final MoneyTransferService moneyTransferService;

    @Autowired
    public MoneyTransferController(MoneyTransferService moneyTransferService) {
        this.moneyTransferService = moneyTransferService;
    }

    @PostMapping(value = "/transfer")
    public ResponseEntity<?> transfer(@RequestBody Transaction transaction) {
        return moneyTransferService.transfer(transaction);
    }

    @PostMapping(value = "/confirmOperation")
    public ResponseEntity<?> confirmOperation(@RequestBody Operation operation) {
        return moneyTransferService.confirmOperation(operation);
    }

    @GetMapping(value = "getLogs")
    public String getLogs() {
        return moneyTransferService.getLogs();
    }
}
