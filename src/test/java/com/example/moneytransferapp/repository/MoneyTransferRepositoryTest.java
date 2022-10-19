package com.example.moneytransferapp.repository;

import com.example.moneytransferapp.model.Operation;
import com.example.moneytransferapp.model.Transaction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MoneyTransferRepositoryTest {

    private MoneyTransferRepository repository;

    @BeforeAll
    void init() {
        repository = new MoneyTransferRepositoryImpl();
    }

    @DisplayName("Returns correct Id when register Transaction")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    void shouldReturnCorrectIdWhileRegisterTransactions(int id) {
        int actualId = repository.registerTransaction(new Transaction());
        assertEquals(id, actualId);
    }

    @DisplayName("Returns correct Id when register Operation")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    void shouldReturnCorrectIdWhileRegisterOperations(int id) {
        int actualId = repository.registerOperation(new Operation());
        assertEquals(id, actualId);
    }
}