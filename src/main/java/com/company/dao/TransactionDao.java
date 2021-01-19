package com.company.dao;

import com.company.core.models.Transaction;

public interface TransactionDao {
    void createTransaction(int accountNumber, Transaction transaction);
    Transaction[] getAllTransactions(int accountNumber);
}