package edu.gsu.ays.gpi.inoisbatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import edu.gsu.ays.gpi.inoisbatch.tasks.ProcessEntityData;
import edu.gsu.ays.gpi.inoisbatch.tasks.ProcessHashData;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Bean
    public Step stepOne(){
        return steps.get("ProcessEntityData")
                .tasklet(new ProcessEntityData())
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
