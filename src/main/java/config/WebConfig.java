package config;

import dao.AccountDao;
import dao.simple.SimpleAccountDao;

import models.Account;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.HashSet;

@Configuration
@EnableWebMvc
@ComponentScan("controllers")
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
