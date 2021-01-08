package dao;

import models.Account;
import org.springframework.lang.Nullable;

import java.util.Set;

public interface AccountDao {
    void createAccount(Account account);
    void deleteAccountByNumber(int number);
    @Nullable Account getByNumber(int number);
    Set<Account> getAllAccounts();
    boolean hasAccount(Account account);
}
