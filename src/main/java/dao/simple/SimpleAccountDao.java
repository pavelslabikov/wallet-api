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

    @Override
    public void deleteAccountByNumber(int number) {
        var accountToDelete = getByNumber(number);
        if (accountToDelete != null)
            storage.remove(accountToDelete);
    }


    public Account getByNumber(int number) {
        for (var account : storage)
            if (account.getNumber() == number)
                return account;

        return null;
    }

    @Override
    public Set<Account> getAllAccounts() {
        return storage;
    }
}
