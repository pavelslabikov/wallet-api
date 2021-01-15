package core.dao;


public abstract class BaseDaoFactory {
    public abstract AccountDao createAccountDao();
    public abstract TransactionDao createTransactionDao();
}
