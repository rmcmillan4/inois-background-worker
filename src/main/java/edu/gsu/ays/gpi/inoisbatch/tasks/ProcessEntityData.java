package edu.gsu.ays.gpi.inoisbatch.tasks;


import edu.gsu.ays.gpi.inoisbatch.entity.BatchHeaderQueue;
import edu.gsu.ays.gpi.inoisbatch.entity.InoisEntity;
import edu.gsu.ays.gpi.inoisbatch.entity.factories.InoisEntityFactory;
import edu.gsu.ays.gpi.inoisbatch.exceptions.*;
import edu.gsu.ays.gpi.inoisbatch.utils.FileProcessingStatus;
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
        log.info("ProcessEntityData task start...");
        BatchHeaderQueueDao batchHeaderQueueDao = new BatchHeaderQueueDao(this.jdbcTemplate);
        List<BatchHeaderQueue> recordsToProcess = batchHeaderQueueDao.getRecordToProcess();
        if (!recordsToProcess.isEmpty()) {
            try{
                log.info(recordsToProcess.get(0).toString());
                batchHeaderQueueDao.updateRecordToProcess(recordsToProcess.get(0).getId(), FileProcessingStatus.PROCESSING_FILE);
                String fileContents = FileService.retrieveBlob(recordsToProcess.get(0).getBatchIdentifier());
                String decryptedFileContents = DecryptionService.decryptFile(fileContents);
                InoisEntity matchingEntity = InoisEntityFactory.getInoisEntity(entityJdbcTemplate, recordsToProcess.get(0));
                RecordService.processCsv(matchingEntity, decryptedFileContents);
                batchHeaderQueueDao.updateRecordToProcess(recordsToProcess.get(0).getId(), FileProcessingStatus.COMPLETED_SUCCESSFULLY);
                log.info("ProcessEntityData done..");
            }
            catch (DBTransactionError ex){
                log.error(ex.getMessage());
                batchHeaderQueueDao.updateRecordToProcess(recordsToProcess.get(0).getId(), FileProcessingStatus.DB_TRANSACTION_ERROR);
            }
            catch (FileDecryptionError ex){
                log.error(ex.getMessage());
                batchHeaderQueueDao.updateRecordToProcess(recordsToProcess.get(0).getId(), FileProcessingStatus.FILE_DECRYPTION_ERROR);
            }
            catch (HashingError ex){
                log.error(ex.getMessage());
                batchHeaderQueueDao.updateRecordToProcess(recordsToProcess.get(0).getId(), FileProcessingStatus.HASHING_ERROR);
            }
            catch (InvalidFileFormatError ex){
                log.error(ex.getMessage());
                batchHeaderQueueDao.updateRecordToProcess(recordsToProcess.get(0).getId(), FileProcessingStatus.INVALID_FILE_FORMAT_ERROR);
            }
            catch (OutOfMemoryError ex){
                log.error(ex.getMessage());
                batchHeaderQueueDao.updateRecordToProcess(recordsToProcess.get(0).getId(), FileProcessingStatus.OUT_OF_MEMORY_ERROR);
            }
        }
        else {
            log.info("No new records to process.");
        }

        return RepeatStatus.FINISHED;
    }
}
