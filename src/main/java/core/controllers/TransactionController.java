package core.controllers;


import core.dao.AccountDao;
import core.dao.BaseDaoFactory;
import core.exceptions.AccountNotFoundException;
import core.models.Transaction;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/accounts/{accountNumber}")
public class TransactionController {
    private final AccountDao accountDao;

    public TransactionController(BaseDaoFactory factory) {
        this.accountDao = factory.createAccountDao();
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
