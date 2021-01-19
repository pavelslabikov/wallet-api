package com.company.dao.sql;

import com.company.dao.TransactionDao;
import com.company.core.models.Transaction;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class SqlTransactionDao implements TransactionDao {
    private final JdbcTemplate transactionDb;


    public SqlTransactionDao(@Qualifier("transactionDbTemplate") JdbcTemplate transactionDb) {
        this.transactionDb = transactionDb;
    }

    @Override
    public void createTransaction(int accountNumber, Transaction transaction) {
        transactionDb.update("INSERT INTO transactions VALUES (?, ?::transaction_type, ?, ?, ?)",
                accountNumber, transaction.getType().getName(), transaction.getDate(), transaction.getAmount(),
                transaction.getDescription());
    }

    @Override
    public Transaction[] getAllTransactions(int accountNumber) {
        return transactionDb.query("SELECT * FROM transactions WHERE \"AccountNumber\" = ?",
                Mappers.transactionRowMapper, accountNumber).toArray(new Transaction[0]);
    }
}
