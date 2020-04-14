package edu.gsu.ays.gpi.inoisbatch.db;

import edu.gsu.ays.gpi.inoisbatch.entity.BatchHeaderQueue;
import edu.gsu.ays.gpi.inoisbatch.entity.mappers.BatchHeaderQueueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


public class BatchHeaderQueueDao {

    private final JdbcTemplate jdbcTemplate;


    public BatchHeaderQueueDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getNumberOfRecords(){
        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM dbo.batch_header_queue", Integer.class);
    }

    public BatchHeaderQueue getRecordToProcess(){
        return jdbcTemplate.queryForObject("SELECT TOP 1 * FROM dbo.batch_header_queue WHERE status_id = 'Ingest Pending' ORDER BY created;", new BatchHeaderQueueMapper());
    }

}
