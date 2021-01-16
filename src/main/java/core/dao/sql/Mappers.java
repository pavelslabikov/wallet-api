package core.dao.sql;

import core.models.Account;
import core.models.Transaction;
import org.springframework.jdbc.core.RowMapper;


public class Mappers {
    public static RowMapper<Account> accountRowMapper = (resultSet, i) -> {
        var number = resultSet.getInt("AccountNumber");
        var balance = resultSet.getFloat("Balance");
        return new Account(number, balance);
    };

    public static RowMapper<Transaction> transactionRowMapper = (resultSet, i) -> {
        var type = resultSet.getString("Type");
        var date = resultSet.getDate("Date");
        var amount = resultSet.getFloat("Amount");
        var description = resultSet.getString("Description");
        return new Transaction(type, date, amount, description);
    };
}
