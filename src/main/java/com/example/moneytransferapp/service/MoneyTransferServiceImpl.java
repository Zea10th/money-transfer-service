package com.example.moneytransferapp.service;

import com.example.moneytransferapp.model.Operation;
import com.example.moneytransferapp.model.ResponseOK;
import com.example.moneytransferapp.model.Transaction;
import com.example.moneytransferapp.repository.MoneyTransferRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static com.example.moneytransferapp.utility.SerializeUtility.serialize;


@Service
public class MoneyTransferServiceImpl implements MoneyTransferService {
    public static final Logger LOGGER = LogManager.getLogger(MoneyTransferServiceImpl.class);
    private final MoneyTransferRepository repository;

    @Autowired
    public MoneyTransferServiceImpl(MoneyTransferRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<?> transfer(Transaction transaction) {
        int id = repository.registerTransaction(transaction);

        LOGGER.info("Trying to provide transaction ID {} " +
                        "from card No {} valid till {} CVV {} to card No {} amount {} {}",
                id,
                transaction.getCardFromNumber(),
                transaction.getCardFromValidTill(),
                transaction.getCardFromCVV(),
                transaction.getCardToNumber(),
                transaction.getAmount().getValue(),
                transaction.getAmount().getCurrency());

        return new ResponseEntity<>(serialize(new ResponseOK().id(id)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> confirmOperation(Operation operation) {
        int id = operation.getOperationId();

        if (id != repository.registerOperation(operation)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        String result = String.format("Transaction ID %d is successful.", id);
        repository.registerResult(result);
        LOGGER.info(result);
        return new ResponseEntity<>(serialize(new ResponseOK().id(id)), HttpStatus.OK);
    }
}
