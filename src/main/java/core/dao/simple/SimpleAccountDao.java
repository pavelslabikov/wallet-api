package core.dao.simple;

import core.dao.AccountDao;
import core.models.Account;

import java.util.*;

public class SimpleAccountDao implements AccountDao {
    private final Set<Account> storage;

    public SimpleAccountDao(Set<Account> storage) {
        this.storage = storage;
    }

    @Override
    public void createAccount(Account account) {
        storage.add(account);
    }

    @Override
    public void deleteAccountByNumber(int number) {
        var accountToDelete = getByNumber(number);
        if (accountToDelete != null)
            storage.remove(accountToDelete);
    }

    @Override
    public Account getByNumber(int number) {
        for (var account : storage)
            if (account.getNumber() == number)
                return account;

        return null;
    }

    @Override
    public List<Account> getAllAccounts() {
        return new ArrayList<>(storage);
    }

    @Override
    public void updateAccount(Account account) { }
}
