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

/*    @Bean(name="datawh")
    @ConfigurationProperties(prefix = "spring.datasource.datawh")
    public DriverManagerDataSource datawhDataSource() {
        return new DriverManagerDataSource();
    }

    @Bean(name = "datawhJdbcTemplate")
    @Autowired
    public JdbcTemplate datawhJdbcTemplate(@Qualifier("datawh") DriverManagerDataSource datawh) {
        return new JdbcTemplate(datawh);
    }*/


    @Bean(name="core")
    @ConfigurationProperties(prefix = "spring.datasource.core")
    public DriverManagerDataSource coreDataSource() {
        return new DriverManagerDataSource();
    }

    @Bean(name = "coreJdbcTemplate")
    @Autowired
    public JdbcTemplate coreJdbcTemplate(@Qualifier("core") DriverManagerDataSource core) {
        return new JdbcTemplate(core);
    }
}
