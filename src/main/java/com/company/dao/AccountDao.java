package com.company.dao;

import com.company.core.models.Account;
import org.springframework.lang.Nullable;

public interface AccountDao {
    void createAccount(Account account);
    void deleteAccount(Account account);
    @Nullable Account getByNumber(int number);
    Account[] getAllAccounts();
    void updateAccount(Account account);
}
