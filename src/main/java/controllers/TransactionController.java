package controllers;


import dao.TransactionDao;
import models.Transaction;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/accounts/{accountNumber}")
public class TransactionController {
    private final TransactionDao transactionDao;

    public TransactionController(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    @GetMapping("transactions")
    public List<Transaction> getTransactions(@PathVariable int accountNumber) {
        return transactionDao.getAllTransactions(accountNumber);
    }

    @PostMapping("transactions")
    public void createTransaction(@PathVariable int accountNumber,
                                  @RequestBody Transaction transaction) {
        transactionDao.createTransaction(accountNumber, transaction);
    }
}
