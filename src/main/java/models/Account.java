package models;

import com.fasterxml.jackson.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class Account {
    private final List<Transaction> transactions;

    private final int number;
    private float balance;

    @JsonCreator
    public Account(@JsonProperty(value = "number", required = true) int number,
                   @JsonProperty(value = "balance", required = true) float balance,
                   @JsonProperty(value = "transactions") List<Transaction> transactions) {
        this.transactions = Objects.requireNonNullElseGet(transactions, ArrayList::new);
        this.number = number;
        this.balance = balance;
    }

    public void addTransaction(Transaction transaction) {
        if (transaction == null)
            throw new IllegalArgumentException("Transaction cannot be null");
        transactions.add(transaction);

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

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
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
