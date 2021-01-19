package com.company.core.controllers;

import com.company.dao.AccountDao;
import com.company.core.exceptions.AccountAlreadyExistException;
import com.company.core.exceptions.AccountNotFoundException;
import com.company.core.models.Account;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/accounts")
public class AccountController {
    private final AccountDao accountDao;

    public AccountController(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @GetMapping("{number}")
    public Account getAccount(@PathVariable Integer number) {
        var foundAccount = accountDao.getByNumber(number);
        if (foundAccount == null)
            throw new AccountNotFoundException();

        return foundAccount;
    }

    @GetMapping("/")
    public Account[] getAllAccounts() {
        return accountDao.getAllAccounts();
    }

    @PostMapping("/")
    public void createAccount(@RequestBody Account account) {
        if (accountDao.getByNumber(account.getNumber()) != null)
            throw new AccountAlreadyExistException();

        accountDao.createAccount(account);
    }

    @DeleteMapping("{number}")
    public void deleteAccount(@PathVariable int number) {
        var foundAccount = accountDao.getByNumber(number);
        if (foundAccount == null)
            throw new AccountNotFoundException();

        accountDao.deleteAccount(foundAccount);
    }
}
