package controllers;


import models.Transaction;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {
    @PostMapping("/v1/accounts/{accountNumber}")
    public void createTransaction(@PathVariable int accountNumber, @RequestBody Transaction transaction) {

    }
}
