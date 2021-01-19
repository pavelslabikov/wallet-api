package core.dao;

import core.models.Transaction;

import java.util.List;

public interface TransactionDao {
    void createTransaction(int accountNumber, Transaction transaction);
    Transaction[] getAllTransactions(int accountNumber);
}