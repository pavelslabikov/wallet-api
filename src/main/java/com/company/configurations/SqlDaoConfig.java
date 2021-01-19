package com.company.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class SqlDaoConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.postgres")
    public DataSource postgresDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public JdbcTemplate accountDbTemplate() {
        return new JdbcTemplate(postgresDataSource());
    }

    @Bean
    public JdbcTemplate transactionDbTemplate() {
        return new JdbcTemplate(postgresDataSource());
    }
}
