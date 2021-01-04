package models;

import java.util.Collections;
import java.util.List;

public class Account {
    private final List<Transaction> transactions;
    private final int number;

    public Account(int number, List<Transaction> transactions) {
        this.transactions = Collections.unmodifiableList(transactions);
        this.number = number;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public int getNumber() {
        return number;
    }
}
