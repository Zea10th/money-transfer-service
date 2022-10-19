package com.example.moneytransferapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Transaction {
    @JsonProperty("cardFromNumber")
    private String cardFromNumber;
    @JsonProperty("cardFromValidTill")
    private String cardFromValidTill;
    @JsonProperty("cardFromCVV")
    private String cardFromCVV;
    @JsonProperty("cardToNumber")
    private String cardToNumber;
    @JsonProperty("amount")
    private TransactionAmount amount;

    public String getCardFromNumber() {
        return cardFromNumber;
    }

    public Transaction setCardFromNumber(String cardFromNumber) {
        this.cardFromNumber = cardFromNumber;
        return this;
    }

    public String getCardFromValidTill() {
        return cardFromValidTill;
    }

    public Transaction setCardFromValidTill(String cardFromValidTill) {
        this.cardFromValidTill = cardFromValidTill;
        return this;
    }

    public String getCardFromCVV() {
        return cardFromCVV;
    }

    public Transaction setCardFromCVV(String cardFromCVV) {
        this.cardFromCVV = cardFromCVV;
        return this;
    }

    public String getCardToNumber() {
        return cardToNumber;
    }

    public Transaction setCardToNumber(String cardToNumber) {
        this.cardToNumber = cardToNumber;
        return this;
    }

    public TransactionAmount getAmount() {
        return amount;
    }

    public void setAmount(TransactionAmount amount) {
        this.amount = amount;
    }

    @Autowired
    public Transaction getAmount(TransactionAmount amount) {
        this.amount = amount;
        return this;
    }

    public Transaction getEmpty() {
        String EMPTY = "empty";
        var amount = new TransactionAmount();
        amount.setValue(0);
        amount.setCurrency(EMPTY);

        this.setCardFromNumber(EMPTY)
                .setCardFromValidTill(EMPTY)
                .setCardFromCVV(EMPTY)
                .setCardToNumber(EMPTY)
                .setAmount(amount);
        return this;
    }

    public String toString() {
        float sum = (float) this.amount.getValue() / 100;
        return String.format("from card No %s (valid till %s CVV is %s) to card No %s amount is %.2f %s",
                getCardFromNumber(), getCardFromValidTill(), getCardFromCVV(), getCardToNumber(),
                sum, getAmount().getCurrency());
    }
}
