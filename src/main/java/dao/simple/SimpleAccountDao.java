package dao.simple;

import dao.AccountDao;
import models.Account;
import java.util.Set;

public class SimpleAccountDao implements AccountDao {
    private final Set<Account> storage;

    public SimpleAccountDao(Set<Account> storage) {
        this.storage = storage;
    }

    public void createAccount(Account account) {
        storage.add(account);
    }

    public void deleteAccount(Account account) {
        storage.remove(account);
    }

    public Account getByNumber(int number) {
        for (var account : storage)
            if (account.getNumber() == number)
                return account;

        return null;
    }
}
