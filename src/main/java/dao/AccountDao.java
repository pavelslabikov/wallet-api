package dao;

import models.Account;

import java.util.Set;

public interface AccountDao {
    void createAccount(Account account);
    void deleteAccountByNumber(int number);
    Account getByNumber(int number);
    Set<Account> getAllAccounts();
}
