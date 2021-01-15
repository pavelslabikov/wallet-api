package core.dao.simple;

import core.dao.AccountDao;
import core.dao.BaseDaoFactory;
import core.dao.TransactionDao;
import core.models.Account;
import org.springframework.stereotype.Component;

import java.util.HashSet;


@Component
public class SimpleDaoFactory extends BaseDaoFactory {
    private final HashSet<Account> storage;

    public SimpleDaoFactory(HashSet<Account> storage) {
        this.storage = storage;
    }

    @Override
    public AccountDao createAccountDao() {
        return new SimpleAccountDao(storage);
    }

    @Override
    public TransactionDao createTransactionDao() {
        return null;
    }
}
