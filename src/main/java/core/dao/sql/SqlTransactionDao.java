package core.dao.sql;

import core.dao.AccountDao;
import core.dao.TransactionDao;
import core.models.Account;
import core.models.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class SqlTransactionDao implements TransactionDao {
    private final JdbcTemplate transactionDb;

    private final RowMapper<Transaction> transactionRowMapper = (resultSet, i) -> {
        var type = resultSet.getString("Type");
        var date = resultSet.getDate("Date");
        var amount = resultSet.getFloat("Amount");
        var description = resultSet.getString("Description");
        return new Transaction(type, date, amount, description);
    };

    public SqlTransactionDao(JdbcTemplate transactionDb) {
        this.transactionDb = transactionDb;
    }

    @Override
    public void createTransaction(int accountNumber, Transaction transaction) {
        transactionDb.update("INSERT INTO transactions VALUES (?, ?::transaction_type, ?, ?, ?)",
                accountNumber, transaction.getType().getType(), transaction.getDate(), transaction.getAmount(),
                transaction.getDescription());
    }

    @Override
    public List<Transaction> getAllTransactions(int accountNumber) {
        return transactionDb.query("SELECT * FROM transactions WHERE \"AccountNumber\" = ?",
                transactionRowMapper, accountNumber);
    }
}
