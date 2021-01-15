package core.dao;

import core.models.Account;

import java.util.List;
import java.util.Set;

public interface AccountDao {
    void createAccount(Account account);
    void deleteAccountByNumber(int number);
    Account getByNumber(int number);
    List<Account> getAllAccounts();
    void updateAccount(Account account);
}
