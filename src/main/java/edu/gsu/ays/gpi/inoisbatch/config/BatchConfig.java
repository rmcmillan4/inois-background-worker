package edu.gsu.ays.gpi.inoisbatch.config;

import edu.gsu.ays.gpi.inoisbatch.db.DBConfig;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import edu.gsu.ays.gpi.inoisbatch.tasks.ProcessEntityData;
import edu.gsu.ays.gpi.inoisbatch.tasks.ProcessHashData;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfig extends DefaultBatchConfigurer {

    private JdbcTemplate jdbcTemplate;

    private JdbcTemplate entityJdbcTemplate;

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    public BatchConfig(@Qualifier("coreJdbcTemplate") JdbcTemplate jdbcTemplate,
                       @Qualifier("entityJdbcTemplate") JdbcTemplate entityJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.entityJdbcTemplate = entityJdbcTemplate;
    }



/*    @Override
    @Autowired
    public void setDataSource(DataSource dataSource) {
        //DriverManagerDataSource dataSource = new DriverManagerDataSource();
        DriverManagerDataSource dataSource = dbConfig.coreDataSource();

        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/springjdbc");
        dataSource.setUsername("guest_user");
        dataSource.setPassword("guest_password");

        return dataSource;
    }*/

    @Bean
    public Step stepOne(){
        return steps.get("ProcessEntityData")
                .tasklet(new ProcessEntityData(this.jdbcTemplate, this.entityJdbcTemplate))
                .build();
    }

    @Bean
    public Step stepTwo(){
        return steps.get("ProcessHashData")
                .tasklet(new ProcessHashData())
                .build();
    }

    @Bean
    public Job demoJob(){
        return jobs.get("inois-batch")
                .incrementer(new RunIdIncrementer())
                .start(stepOne())
                .next(stepTwo())
                .build();
    }
}
