package edu.gsu.ays.gpi.inoisbatch.tasks;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import edu.gsu.ays.gpi.inoisbatch.db.BatchHeaderQueueDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Configuration
public class ProcessEntityData implements Tasklet {

    Logger log = LoggerFactory.getLogger(ProcessEntityData.class);
    private JdbcTemplate jdbcTemplate;


    public ProcessEntityData(JdbcTemplate jdbcTemplate){ this.jdbcTemplate = jdbcTemplate; }


    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception
    {
        log.info("ProcessEntityData start...");
        BatchHeaderQueueDao batchHeaderQueueDao = new BatchHeaderQueueDao(this.jdbcTemplate);
        int records = batchHeaderQueueDao.getNumberOfRecords();
        log.info("records in queue: " + records);


        log.info("ProcessEntityData done..");
        return RepeatStatus.FINISHED;
    }
}
