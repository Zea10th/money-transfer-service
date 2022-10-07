package com.example.moneytransferapp.service;

import com.example.moneytransferapp.model.Operation;
import com.example.moneytransferapp.model.Transaction;
import org.springframework.http.ResponseEntity;

public interface MoneyTransferService {

    ResponseEntity<?> transfer(int id, Transaction transaction);

    ResponseEntity<?> confirmOperation(int id, Operation operation);
}
