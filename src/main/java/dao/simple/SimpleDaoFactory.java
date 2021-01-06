package dao.simple;

import dao.AccountDao;
import dao.BaseDaoFactory;
import dao.TransactionDao;
import models.Account;

public class SimpleDaoFactory extends BaseDaoFactory {

    @Override
    public AccountDao createAccountDao() {
        return null;
    }

    @Override
    public TransactionDao createTransactionDao() {
        return null;
    }
}
