package com.company.core.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.Assert;

import java.util.Objects;


public class Account {
    private final int number;
    private float balance;

    @JsonCreator
    public Account(@JsonProperty(value = "number", required = true) int number,
                   @JsonProperty(value = "balance", required = true) float balance) {
        if (number <= 0)
            throw new IllegalArgumentException("Number cannot be negative");

        this.number = number;
        this.balance = balance;
    }

    public void addTransaction(Transaction transaction) {
        Assert.notNull(transaction, "Transaction required");
        if (transaction.getType() == TransactionType.INCOME)
            balance += transaction.getAmount();
        else
            balance -= transaction.getAmount();
    }

    public int getNumber() {
        return number;
    }

    public float getBalance() {
        return balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Account account = (Account) o;
        return number == account.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
