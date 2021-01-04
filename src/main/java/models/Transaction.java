package models;

import java.util.Date;

public class Transaction {
    private final int id;
    private final TransactionType type;
    private final Date date;
    private final float amount;
    private String description = "";

    public Transaction(int id, TransactionType type, Date date, float amount, String description) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.amount = amount;
        this.description = description;
    }

    public Transaction(int id, TransactionType type, Date date, float amount) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public TransactionType getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }

    public float getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }
}
