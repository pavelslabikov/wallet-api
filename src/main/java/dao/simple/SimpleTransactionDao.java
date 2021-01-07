package dao.simple;

import dao.TransactionDao;
import models.Account;
import models.Transaction;

import java.util.List;
import java.util.Set;

public class SimpleTransactionDao implements TransactionDao {
    private final Set<Account> storage;

    public SimpleTransactionDao(Set<Account> storage) {
        this.storage = storage;
    }

    @Override
    public void createTransaction(int accountNumber, Transaction transaction) {
        Account chosenAccount = null;
        for (var account : storage)
            if (account.getNumber() == accountNumber)
                chosenAccount = account;

        if (chosenAccount != null)
            chosenAccount.addTransaction(transaction);
    }

    @Override
    public List<Transaction> getAllTransactions(int accountNumber) {
        Account chosenAccount = null;
        for (var account : storage)
            if (account.getNumber() == accountNumber)
                chosenAccount = account;

        return chosenAccount == null
                ? null
                : chosenAccount.getTransactions();
    }
}
