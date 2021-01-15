package core.dao.sql;

import core.dao.AccountDao;
import core.models.Account;
import core.models.Transaction;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class SqlAccountDao implements AccountDao {
    private final JdbcTemplate accountDb;

    private final RowMapper<Account> accountRowMapper = (resultSet, i) -> {
        var number = resultSet.getInt("AccountNumber");
        var balance = resultSet.getFloat("Balance");
        return new Account(number, balance);
    };

    public SqlAccountDao(JdbcTemplate template) {
        this.accountDb = template;
    }

    @Override
    public void createAccount(Account account) {
        accountDb.update("INSERT INTO accounts VALUES (?, ?)",
                account.getNumber(), account.getBalance());
    }

    @Override
    public void deleteAccountByNumber(int number) {
        accountDb.update("DELETE FROM accounts WHERE \"AccountNumber\" = ?", number);
    }

    @Override
    public Account getByNumber(int number) {
        Account account = null;
        try {
            account = accountDb.queryForObject("SELECT * FROM accounts WHERE \"AccountNumber\" = ?",
                    accountRowMapper, number);
        } catch (DataAccessException ignored) {

        }

        return account;
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountDb.query("SELECT * FROM accounts", accountRowMapper);
    }

    @Override
    public void updateAccount(Account account) {
        accountDb.update("UPDATE accounts SET \"Balance\"=? WHERE \"AccountNumber\" = ?",
                account.getBalance(), account.getNumber());
    }
}
