package com.company.dao.sql;

import com.company.dao.AccountDao;
import com.company.core.models.Account;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class SqlAccountDao implements AccountDao {
    private final JdbcTemplate accountDb;

    public SqlAccountDao(@Qualifier("accountDbTemplate") JdbcTemplate template) {
        this.accountDb = template;
    }

    @Override
    public void createAccount(Account account) {
        accountDb.update("INSERT INTO accounts VALUES (?, ?)",
                account.getNumber(), account.getBalance());
    }

    @Override
    public void deleteAccount(Account account) {
        accountDb.update("DELETE FROM accounts WHERE \"AccountNumber\" = ?",
                account.getNumber());
    }

    @Override
    public Account getByNumber(int number) {
        Account account = null;
        try {
            account = accountDb.queryForObject("SELECT * FROM accounts WHERE \"AccountNumber\" = ?",
                    Mappers.accountRowMapper, number);
        } catch (DataAccessException ignored) {

        }

        return account;
    }

    @Override
    public Account[] getAllAccounts() {
        return (Account[]) accountDb.query("SELECT * FROM accounts",
                Mappers.accountRowMapper).toArray();
    }

    @Override
    public void updateAccount(Account account) {
        accountDb.update("UPDATE accounts SET \"Balance\"=? WHERE \"AccountNumber\" = ?",
                account.getBalance(), account.getNumber());
    }
}
