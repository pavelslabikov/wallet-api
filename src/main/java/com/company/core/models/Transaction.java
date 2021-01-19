package com.company.core.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Objects;


@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Transaction {
    private final TransactionType type;
    @JsonFormat(shape = JsonFormat.Shape.STRING,
                pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private final Date date;
    private final Float amount;
    private final String description;

    @JsonCreator
    public Transaction(@JsonProperty(value = "type", required = true) String type,
                       @JsonProperty(value = "date", required = true) Date date,
                       @JsonProperty(value = "amount", required = true) float amount,
                       @JsonProperty("description") String description) {
        this.type = TransactionType.valueOf(type.toUpperCase());  // TODO: Fix case
        this.date = date;
        this.amount = amount;
        this.description = Objects.requireNonNullElse(description, "");
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
