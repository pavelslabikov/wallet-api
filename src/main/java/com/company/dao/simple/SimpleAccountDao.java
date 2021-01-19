package com.company.dao.simple;

import com.company.dao.AccountDao;
import com.company.core.models.Account;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class SimpleAccountDao implements AccountDao {
    private final Set<Account> accountStorage;

    public SimpleAccountDao(Set<Account> storage) {
        this.accountStorage = storage;
    }

    @Override
    public void createAccount(Account account) {
        accountStorage.add(account);
    }

    @Override
    public void deleteAccount(Account account) {
        accountStorage.remove(account);
    }

    @Override
    public Account getByNumber(int number) {
        for (var account : accountStorage)
            if (account.getNumber() == number)
                return account;

        return null;
    }

    @Override
    public Account[] getAllAccounts() {
        return (Account[]) accountStorage.toArray();
    }

    @Override
    public void updateAccount(Account account) { }
}
