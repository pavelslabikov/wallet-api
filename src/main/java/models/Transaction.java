package models;

import java.util.Date;
import java.util.Objects;

public class Transaction {
    private final TransactionType type;
    private final Date date;
    private final float amount;
    private String description = "";

    public Transaction(String type, Date date, float amount, String description) {
        this.type = TransactionType.valueOf(type);
        this.date = date;
        this.amount = amount;
        this.description = description;
    }

    public Transaction(String type, Date date, float amount) {
        this.type = TransactionType.valueOf(type);
        this.date = date;
        this.amount = amount;
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Transaction that = (Transaction) o;
        return Float.compare(that.amount, amount) == 0 &&
                type == that.type &&
                date.equals(that.date) &&
                description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, date, amount, description);
    }
}
