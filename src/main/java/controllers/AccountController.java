package controllers;

import dao.AccountDao;
import models.Account;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/v1/accounts")
public class AccountController {
    private final AccountDao accountDao;

    public AccountController(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @GetMapping("{number}")
    public Account getAccount(@PathVariable Integer number) {
        return accountDao.getByNumber(number);
    }

    @GetMapping("/")
    public Set<Account> getAllAccounts() {
        return accountDao.getAllAccounts();
    }

    @PostMapping("/")
    public void createAccount(@RequestBody Account account) {
        accountDao.createAccount(account);
    }

    @DeleteMapping("{number}")
    public void deleteAccount(@PathVariable int number) {
        accountDao.deleteAccountByNumber(number);
    }
}
