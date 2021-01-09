package core.config;

import core.dao.AccountDao;
import core.dao.simple.SimpleAccountDao;
import core.models.Account;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;

@Configuration
public class WebConfig {
    @Bean
    public AccountDao accountDao() {
        return new SimpleAccountDao(accounts());
    }

    @Bean
    public HashSet<Account> accounts() {
        return new HashSet<>();
    }
}
