package com.company.core.controllers;

import com.company.dao.AccountDao;
import com.company.dao.TransactionDao;
import com.company.core.exceptions.AccountNotFoundException;
import com.company.core.models.Transaction;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/accounts/{accountNumber}")
public class TransactionController {
    private final TransactionDao transactionDao;
    private final AccountDao accountDao;

    public TransactionController(TransactionDao transactionDao, AccountDao accountDao) {
        this.transactionDao = transactionDao;
        this.accountDao = accountDao;
    }

    @GetMapping("transactions")
    public Transaction[] getTransactions(@PathVariable int accountNumber) {
        var account = accountDao.getByNumber(accountNumber);
        if (account == null)
            throw new AccountNotFoundException();

        return transactionDao.getAllTransactions(accountNumber);
    }

    @PostMapping("transactions")
    public void createTransaction(@PathVariable int accountNumber,
                                  @RequestBody Transaction transaction) {
        var account = accountDao.getByNumber(accountNumber);
        if (account == null)
            throw new AccountNotFoundException();

        account.addTransaction(transaction);
        accountDao.updateAccount(account);
        transactionDao.createTransaction(accountNumber, transaction);
    }
}
