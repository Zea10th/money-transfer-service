package com.example.moneytransferapp.repository;

import com.example.moneytransferapp.model.Operation;
import com.example.moneytransferapp.model.Transaction;

public interface MoneyTransferRepository {
    int registerTransaction(Transaction transaction);

    int registerOperation(Operation operation);

    void registerResult(String result);

    String getLogs();
}
