package core.dao;

import core.models.Account;
import org.springframework.lang.Nullable;

import java.util.List;

public interface AccountDao {
    void createAccount(Account account);
    void deleteAccountByNumber(int number);
    @Nullable Account getByNumber(int number);
    List<Account> getAllAccounts();
    void updateAccount(Account account);
}
