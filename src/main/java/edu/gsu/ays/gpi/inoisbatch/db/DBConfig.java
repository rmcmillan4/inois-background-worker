package edu.gsu.ays.gpi.inoisbatch.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DBConfig {

    @Autowired
    public DBConfig(Environment environment) {
    }

    @Bean(name="entity")
    @ConfigurationProperties(prefix = "spring.datasource.entity")
    public DriverManagerDataSource entityDataSource() {
        return new DriverManagerDataSource();
    }

    @Bean(name = "entityJdbcTemplate")
    @Autowired
    public JdbcTemplate entityJdbcTemplate(@Qualifier("entity") DriverManagerDataSource entity) {
        return new JdbcTemplate(entity);
    }

    @Primary
    @Bean(name="core")
    @ConfigurationProperties(prefix = "spring.datasource.core")
    public DriverManagerDataSource coreDataSource() {
        return new DriverManagerDataSource();
    }

    @Primary
    @Bean(name = "coreJdbcTemplate")
    @Autowired
    public JdbcTemplate coreJdbcTemplate(@Qualifier("core") DriverManagerDataSource core) {
        return new JdbcTemplate(core);
    }
}
