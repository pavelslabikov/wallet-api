package core.controllers;

import core.dao.AccountDao;
import core.dao.TransactionDao;
import core.exceptions.AccountNotFoundException;
import core.models.Transaction;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
