package core.dao.sql;

import core.dao.TransactionDao;
import core.models.Transaction;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

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
                accountNumber, transaction.getType().getType(), transaction.getDate(), transaction.getAmount(),
                transaction.getDescription());
    }

    @Override
    public Transaction[] getAllTransactions(int accountNumber) {
        return (Transaction[]) transactionDb.query("SELECT * FROM transactions WHERE \"AccountNumber\" = ?",
                Mappers.transactionRowMapper, accountNumber).toArray();
    }
}
