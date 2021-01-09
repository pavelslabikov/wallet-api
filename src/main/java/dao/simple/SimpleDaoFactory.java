package dao.simple;

import dao.AccountDao;
import dao.BaseDaoFactory;
import models.Account;

public class SimpleDaoFactory extends BaseDaoFactory {

    @Override
    public AccountDao createAccountDao() {
        return null;
    }

}
