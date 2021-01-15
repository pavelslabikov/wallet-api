package core.controllers;


import core.dao.AccountDao;
import core.dao.BaseDaoFactory;
import core.dao.TransactionDao;
import core.exceptions.AccountNotFoundException;
import core.models.Transaction;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/accounts/{accountNumber}")
public class TransactionController {
    private final TransactionDao transactionDao;
    private final AccountDao accountDao;

    public TransactionController(@Qualifier("sqlDaoFactory") BaseDaoFactory factory) {
        this.transactionDao = factory.createTransactionDao();
        accountDao = factory.createAccountDao();
    }

    @GetMapping("transactions")
    public List<Transaction> getTransactions(@PathVariable int accountNumber) {
        return transactionDao.getAllTransactions(accountNumber);
    }

    @PostMapping("transactions")
    public void createTransaction(@PathVariable int accountNumber,
                                  @RequestBody Transaction transaction) {
        var account = accountDao.getByNumber(accountNumber);
        account.addTransaction(transaction);
        accountDao.updateAccount(account);
        transactionDao.createTransaction(accountNumber, transaction);
    }
}
