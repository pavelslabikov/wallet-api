package dao;

import models.Transaction;

public interface TransactionDao {
    void createTransaction(int accountNumber, Transaction transaction);
}
