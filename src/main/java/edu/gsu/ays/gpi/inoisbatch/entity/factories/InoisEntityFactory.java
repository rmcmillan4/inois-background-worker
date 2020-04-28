package edu.gsu.ays.gpi.inoisbatch.entity.factories;

import edu.gsu.ays.gpi.inoisbatch.entity.BatchHeaderQueue;
import edu.gsu.ays.gpi.inoisbatch.entity.DFCS;
import edu.gsu.ays.gpi.inoisbatch.entity.InoisEntity;
import edu.gsu.ays.gpi.inoisbatch.tasks.ProcessEntityData;
import edu.gsu.ays.gpi.inoisbatch.utils.RegisteredEntityIds;
import org.springframework.jdbc.core.JdbcTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InoisEntityFactory {
    private static Logger log = LoggerFactory.getLogger(ProcessEntityData.class);

    public static InoisEntity getInoisEntity(JdbcTemplate jdbcTemplate, BatchHeaderQueue recordToProcess){
        if (recordToProcess.getRegisteredEntity() == RegisteredEntityIds.DFCS){
            log.info("Record to process belongs to registered entity 'DFCS'");
            return new DFCS(jdbcTemplate, recordToProcess);
        }
/*        else if (recordToProcess.getRegisteredEntity() == RegisteredEntityConstants.SomeNewEntity){
            return new SomeNewEntity(jdbcTemplate, recordToProcess);
        }*/
        else {
            throw new RuntimeException(String.format("No matching INOIS entity found for registered entity id = '%s'  ", recordToProcess.getRegisteredEntity()));
        }
    }
}
