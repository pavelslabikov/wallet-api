package core.config;

import core.dao.AccountDao;
import core.dao.TransactionDao;
import core.dao.simple.SimpleAccountDao;
import core.dao.sql.SqlAccountDao;
import core.dao.sql.SqlTransactionDao;
import core.models.Account;
import org.postgresql.ds.PGConnectionPoolDataSource;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.ConnectionPoolDataSource;
import javax.sql.DataSource;

import java.util.HashSet;

@Configuration
public class WebConfig {
    @Bean
    public AccountDao accountDao() {
        return new SqlAccountDao(template());
    }

    @Bean
    public TransactionDao transactionDao() {
        return new SqlTransactionDao(template());
    }

    @Bean
    public HashSet<Account> accounts() {
        return new HashSet<>();
    }

    @Bean
    public JdbcTemplate template() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.type(PGSimpleDataSource.class);
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://localhost:5432/accounts");
        dataSourceBuilder.username("postgres");
        dataSourceBuilder.password("123");
        return new JdbcTemplate(dataSourceBuilder.build());
    }
}
