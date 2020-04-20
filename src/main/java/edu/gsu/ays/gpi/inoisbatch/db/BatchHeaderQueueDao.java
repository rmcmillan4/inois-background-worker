package edu.gsu.ays.gpi.inoisbatch.db;

import edu.gsu.ays.gpi.inoisbatch.entity.BatchHeaderQueue;
import edu.gsu.ays.gpi.inoisbatch.entity.mappers.BatchHeaderQueueMapper;
import org.springframework.jdbc.core.JdbcTemplate;


public class BatchHeaderQueueDao {

    private final JdbcTemplate jdbcTemplate;


    public BatchHeaderQueueDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getNumberOfRecords(){
        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM dbo.batch_header_queue", Integer.class);
    }

/*    public BatchHeaderQueue getRecordToProcess(){
        return jdbcTemplate.queryForObject("SELECT TOP 1 * FROM dbo.batch_header_queue WHERE status_id = 'Ingest Pending' ORDER BY created;", new BatchHeaderQueueMapper());
    }*/

    public BatchHeaderQueue getRecordToProcess(){
        return jdbcTemplate.queryForObject("SELECT TOP 1 * FROM dbo.batch_header_queue WHERE status_id = 1 ORDER BY created DESC;", new BatchHeaderQueueMapper());
    }

    public void updateRecordToProcess(Long id, Long status){
        jdbcTemplate.update("UPDATE dbo.batch_header_queue SET updated = CURRENT_TIMESTAMP, status_id = ? WHERE id = ?;", status, id);
    }

}
