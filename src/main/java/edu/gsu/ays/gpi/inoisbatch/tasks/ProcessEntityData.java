package edu.gsu.ays.gpi.inoisbatch.tasks;


import com.azure.security.keyvault.secrets.models.KeyVaultSecret;
import edu.gsu.ays.gpi.inoisbatch.entity.BatchHeaderQueue;
import edu.gsu.ays.gpi.inoisbatch.entity.DFCS;
import edu.gsu.ays.gpi.inoisbatch.entity.InoisEntity;
import edu.gsu.ays.gpi.inoisbatch.services.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import edu.gsu.ays.gpi.inoisbatch.db.BatchHeaderQueueDao;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Configuration
public class ProcessEntityData implements Tasklet {

    Logger log = LoggerFactory.getLogger(ProcessEntityData.class);
    private JdbcTemplate jdbcTemplate;
    private JdbcTemplate entityJdbcTemplate;


    public ProcessEntityData(JdbcTemplate jdbcTemplate, JdbcTemplate entityJdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        this.entityJdbcTemplate = entityJdbcTemplate;
    }


    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception
    {
        log.info("ProcessEntityData start...");
        BatchHeaderQueueDao batchHeaderQueueDao = new BatchHeaderQueueDao(this.jdbcTemplate);
        BatchHeaderQueue recordToProcess = batchHeaderQueueDao.getRecordToProcess();
        String fileContents = FileService.retrieveBlob(recordToProcess.getBatchIdentifier());
        String decryptedFileContents = DecryptionService.decryptFile(fileContents);
        RecordService.processCsv(new DFCS(entityJdbcTemplate, recordToProcess), decryptedFileContents);
        log.info("ProcessEntityData done..");
        return RepeatStatus.FINISHED;
    }
}
