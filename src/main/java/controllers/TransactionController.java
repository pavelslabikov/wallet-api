package controllers;


import dao.AccountDao;
import exceptions.AccountNotFoundException;
import models.Transaction;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/accounts/{accountNumber}")
public class TransactionController {
    private final AccountDao accountDao;

    public TransactionController(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @GetMapping("transactions")
    public List<Transaction> getTransactions(@PathVariable int accountNumber) {
        var foundAccount = accountDao.getByNumber(accountNumber);
        if (foundAccount == null)
            throw new AccountNotFoundException();

        return foundAccount.getTransactions();
    }

    @PostMapping("transactions")
    public void createTransaction(@PathVariable int accountNumber,
                                  @RequestBody Transaction transaction) {
        var foundAccount = accountDao.getByNumber(accountNumber);
        if (foundAccount == null)
            throw new AccountNotFoundException();

        foundAccount.addTransaction(transaction);
    }
}
