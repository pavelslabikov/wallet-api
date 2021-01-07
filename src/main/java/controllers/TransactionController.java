package controllers;


import dao.TransactionDao;
import models.Transaction;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {
    private final TransactionDao transactionDao;

    public TransactionController(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    @PostMapping("/v1/accounts/{accountNumber}")
    public void createTransaction(@PathVariable int accountNumber, @RequestBody Transaction transaction) {
        transactionDao.createTransaction(accountNumber, transaction);
    }
}
