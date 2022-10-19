package com.example.moneytransferapp.controller;

import com.example.moneytransferapp.MoneyTransferApplication;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = MoneyTransferApplication.class)
@AutoConfigureMockMvc
class MoneyTransferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("Returns correct response while posting on /transfer.")
    @Test
    public void shouldReturnCorrectTransferResponse() throws Exception {
        String actual = """
                {
                  "cardFromNumber": "2222 3333 4444 5555",
                  "cardFromValidTill": "06/66",
                  "cardFromCVV": "7777",
                  "cardToNumber": "8888 9999 0000 1111",
                  "amount": {
                    "value": 6000,
                    "currency": "rur"
                  }
                }
                """;
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/transfer")
                        .content(actual)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.operationId").value("1"));
    }

    @DisplayName("Returns correct response while posting on /confirmOperation.")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @Test
    public void shouldReturnCorrectConfirmOperationResponse() throws Exception {
        String actual = """
                        {
                          "operationId": "1",
                          "code": "0000"
                        }
                        """;
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/confirmOperation")
                        .content(actual)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.operationId").value("1"));
    }

    @DisplayName("Returns correct logs")
    @Test
    void shouldReturnCorrectLogs() throws Exception {

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime localDate = LocalDateTime.now();


        String transaction = """
                {
                  "cardFromNumber": "1111 2222 3333 4444",
                  "cardFromValidTill": "05/55",
                  "cardFromCVV": "666",
                  "cardToNumber": "7777 8888 9999 0000",
                  "amount": {
                    "value": 5555,
                    "currency": "usd"
                  }
                }
                """;

        String confirm = """
                {
                  "operationId": "1",
                  "code": "0000"
                }""";

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/transfer")
                        .content(transaction)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operationId").exists());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/confirmOperation")
                        .content(confirm)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operationId").exists());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/getLogs")
                        .accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("text/plain;charset=UTF-8")))
                .andExpect(content().string(
                        "Progress report of Money Transfer Service program execution for the date " +
                                dateFormatter.format(localDate) + "\n" +
                                "\t1. Transaction from card No 1111 2222 3333 4444 (valid till 05/55 CVV is 666) " +
                                "to card No 7777 8888 9999 0000 amount is 55.55 usd " +
                                "using operating ID 1 with code 0000 " +
                                "was completed with result: Transaction ID 1 is successful.\n"));
    }
}