package com.example.moneytransferapp.service;

import com.example.moneytransferapp.repository.MoneyTransferRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class MoneyTransferLogServiceImpl implements MoneyTransferLogService{

    public MoneyTransferLogServiceImpl(MoneyTransferRepository repository) {
        this.repository = repository;
    }

    private final MoneyTransferRepository repository;

    @Override
    public String getLogs() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime localDate = LocalDateTime.now();

        StringBuilder logs = new StringBuilder();
        logs.append("Progress report of Money Transfer Service program execution for the date ");
        logs.append(dateFormatter.format(localDate));
        logs.append("\n");

        for (int i = 0; i < repository.getTransactions().size(); i++) {
            logs.append("\t").append(i + 1).append(".");
            logs.append(" Transaction ").append(repository.getTransactions().get(i));
            logs.append(" ").append(repository.getOperations().get(i));
            logs.append(" was completed with result: ").append(repository.getResults().get(i));
            logs.append("\n");
        }

        return logs.toString();
    }
}
