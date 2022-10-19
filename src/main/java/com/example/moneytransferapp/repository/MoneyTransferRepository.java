package com.example.moneytransferapp.repository;

import com.example.moneytransferapp.model.Operation;
import com.example.moneytransferapp.model.Transaction;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public interface MoneyTransferRepository {
    int registerTransaction(Transaction transaction);

    int registerOperation(Operation operation);

    void registerResult(String result);

    ConcurrentHashMap<Integer, Transaction> getTransactions();

    ConcurrentHashMap<Integer, Operation> getOperations();

    CopyOnWriteArrayList<String> getResults();
}
