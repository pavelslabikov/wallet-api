package com.company.configurations;

import com.company.core.models.Account;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;

@Configuration
public class SimpleDaoConfig {
    @Bean
    public HashSet<Account> accounts() {
        return new HashSet<>();
    }

}
