package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Account {
    private final List<Transaction> transactions;
    private final int number;
    private float balance;

    public Account(int number, float balance) {
        this.transactions = new ArrayList<>();
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
