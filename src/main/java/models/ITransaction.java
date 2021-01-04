package models;

import java.util.Date;

public interface ITransaction {
    int getId();
    float getAmount();
    TransactionType getType();
    Date getDate();
    String getDescription();
    int getTarget();
}
