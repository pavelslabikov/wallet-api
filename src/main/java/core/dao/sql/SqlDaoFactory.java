package core.dao.sql;

import core.dao.AccountDao;
import core.dao.BaseDaoFactory;
import core.dao.TransactionDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class SqlDaoFactory extends BaseDaoFactory {
    private final JdbcTemplate template;

    public SqlDaoFactory(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public AccountDao createAccountDao() {
        return new SqlAccountDao(template);
    }

    @Override
    public TransactionDao createTransactionDao() {
        return new SqlTransactionDao(template);
    }
}
