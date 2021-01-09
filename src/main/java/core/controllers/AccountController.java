package core.controllers;

import core.dao.AccountDao;
import core.dao.BaseDaoFactory;
import core.exceptions.AccountAlreadyExistException;
import core.exceptions.AccountNotFoundException;
import core.models.Account;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/v1/accounts")
public class AccountController {
    private final AccountDao accountDao;

    public AccountController(BaseDaoFactory factory) {
        this.accountDao = factory.createAccountDao();
    }

    @GetMapping("{number}")
    public Account getAccount(@PathVariable Integer number) {
        var foundAccount = accountDao.getByNumber(number);
        if (foundAccount == null)
            throw new AccountNotFoundException();

        return foundAccount;
    }

    @GetMapping("/")
    public Set<Account> getAllAccounts() {
        return accountDao.getAllAccounts();
    }

    @PostMapping("/")
    public void createAccount(@RequestBody Account account) {
        if (accountDao.hasAccount(account))
            throw new AccountAlreadyExistException();

        accountDao.createAccount(account);
    }

    @DeleteMapping("{number}")
    public void deleteAccount(@PathVariable int number) {
        accountDao.deleteAccountByNumber(number);
    }
}
