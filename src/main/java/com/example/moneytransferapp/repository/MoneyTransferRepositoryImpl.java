package com.example.moneytransferapp.repository;

import com.example.moneytransferapp.model.Operation;
import com.example.moneytransferapp.model.Transaction;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class MoneyTransferRepositoryImpl implements MoneyTransferRepository {

    private final ConcurrentHashMap<Integer, Transaction> transactions;
    private final ConcurrentHashMap<Integer, Operation> operations;
    private final CopyOnWriteArrayList<String> results;

    public MoneyTransferRepositoryImpl() {
        transactions = new ConcurrentHashMap<>();
        operations = new ConcurrentHashMap<>();
        results = new CopyOnWriteArrayList<>();
    }

    @Override
    public int registerTransaction(Transaction transaction) {
        int id = transactions.values().size();
        transactions.put(id, transaction);
        return id + 1;
    }

    @Override
    public int registerOperation(Operation operation) {
        int id = operations.size();
        operations.put(id, operation);
        return id + 1;
    }

    @Override
    public void registerResult(String result) {
        results.add(result);
    }

    @Override
    public ConcurrentHashMap<Integer, Transaction> getTransactions() {
        return transactions;
    }

    @Override
    public ConcurrentHashMap<Integer, Operation> getOperations() {
        return operations;
    }

    @Override
    public CopyOnWriteArrayList<String> getResults() {
        return results;
    }
}
