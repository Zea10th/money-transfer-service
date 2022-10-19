package com.example.moneytransferapp.service;

import com.example.moneytransferapp.model.Operation;
import com.example.moneytransferapp.model.Transaction;
import org.springframework.http.ResponseEntity;

public interface MoneyTransferService {

    ResponseEntity<?> transfer(Transaction transaction);

    ResponseEntity<?> confirmOperation(Operation operation);
}
