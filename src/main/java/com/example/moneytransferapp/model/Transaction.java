package com.example.moneytransferapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    public void setCardFromNumber(String cardFromNumber) {
        this.cardFromNumber = cardFromNumber;
    }

    public String getCardFromValidTill() {
        return cardFromValidTill;
    }

    public void setCardFromValidTill(String cardFromValidTill) {
        this.cardFromValidTill = cardFromValidTill;
    }

    public String getCardFromCVV() {
        return cardFromCVV;
    }

    public void setCardFromCVV(String cardFromCVV) {
        this.cardFromCVV = cardFromCVV;
    }

    public String getCardToNumber() {
        return cardToNumber;
    }

    public void setCardToNumber(String cardToNumber) {
        this.cardToNumber = cardToNumber;
    }

    public TransactionAmount getAmount() {
        return amount;
    }

    public void setAmount(TransactionAmount amount) {
        this.amount = amount;
    }

    public Transaction amount(TransactionAmount amount) {
        this.amount = amount;
        return this;
    }

    public String toString() {
        return String.format("from card No %s (valid till %s CVV is %s) to card No %s amount is %.2f %s",
                getCardFromNumber(), getCardFromValidTill(), getCardFromCVV(), getCardToNumber(),
                (float)(getAmount().getValue()/100), getAmount().getCurrency());
    }
}
