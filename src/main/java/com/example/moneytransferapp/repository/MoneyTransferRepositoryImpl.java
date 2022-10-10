package com.example.moneytransferapp.repository;

import com.example.moneytransferapp.model.Operation;
import com.example.moneytransferapp.model.Transaction;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Repository
public class MoneyTransferRepositoryImpl implements MoneyTransferRepository {

    private final ArrayList<Transaction> transactions;
    private final ArrayList<Operation> operations;
    private final ArrayList<String> results;

    public MoneyTransferRepositoryImpl() {
        transactions = new ArrayList<>();
        operations = new ArrayList<>();
        results = new ArrayList<>();
    }

    @Override
    public int registerTransaction(Transaction transaction) {
        transactions.add(transaction);
        return transactions.indexOf(transaction) + 1;
    }

    @Override
    public int registerOperation(Operation operation) {
        operations.add(operation);
        return operations.indexOf(operation) + 1;
    }

    @Override
    public void registerResult(String result) {
        results.add(result);
    }

    @Override
    public String getLogs() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime localDate = LocalDateTime.now();

        StringBuilder logs = new StringBuilder();
        logs.append("Progress report of Money Transfer Service program execution for the date ");
        logs.append(dateFormatter.format(localDate));
        logs.append("\n");

        for (int i = 0; i < transactions.size(); i++) {
            logs.append("\t").append(i + 1).append(".");
            logs.append(" Transaction ").append(this.transactions.get(i));
            logs.append(" ").append(this.operations.get(i));
            logs.append(" was completed with result: ").append(this.results.get(i));
            logs.append("\n");
        }

        return logs.toString();
    }
}
