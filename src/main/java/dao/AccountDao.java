package dao;

import models.Account;

public interface AccountDao {
    void createAccount(Account account);
    void deleteAccount(Account account);
    Account getByNumber(int number);
}
