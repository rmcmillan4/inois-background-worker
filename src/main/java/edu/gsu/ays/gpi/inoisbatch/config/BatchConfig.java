package edu.gsu.ays.gpi.inoisbatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import edu.gsu.ays.gpi.inoisbatch.tasks.ProcessEntityData;
import org.springframework.jdbc.core.JdbcTemplate;


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

    @Bean
    public Step stepOne(){
        return steps.get("ProcessEntityData")
                .tasklet(new ProcessEntityData(this.jdbcTemplate, this.entityJdbcTemplate))
                .build();
    }

    @Bean
    public Job demoJob(){
        return jobs.get("inois-batch")
                .incrementer(new RunIdIncrementer())
                .start(stepOne())
                .build();
    }
}
