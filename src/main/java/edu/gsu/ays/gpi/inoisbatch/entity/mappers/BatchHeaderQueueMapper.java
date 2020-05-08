package edu.gsu.ays.gpi.inoisbatch.entity.mappers;

import edu.gsu.ays.gpi.inoisbatch.entity.BatchHeaderQueue;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class BatchHeaderQueueMapper implements RowMapper<BatchHeaderQueue> {
    @Override
    public BatchHeaderQueue mapRow(ResultSet rs, int rowNum) throws SQLException {
        BatchHeaderQueue batchHeaderQueue = new BatchHeaderQueue();

        batchHeaderQueue.setId(rs.getLong("id"));
        batchHeaderQueue.setBatchIdentifier(rs.getString("batch_identifier"));
        batchHeaderQueue.setFilePath(rs.getString("file_path"));
        batchHeaderQueue.setClientIp(rs.getString("client_ip"));
        batchHeaderQueue.setEntityUser(rs.getInt("entity_user_id"));
        batchHeaderQueue.setRegisteredEntity(rs.getInt("registered_entity_id"));
        batchHeaderQueue.setEffectiveStartDate(rs.getString("effective_start_date"));
        batchHeaderQueue.setEffectiveEndDate(rs.getString("effective_end_date"));
        batchHeaderQueue.setStatusId(rs.getString("status_id"));
        batchHeaderQueue.setCreated(rs.getObject("created", LocalDateTime.class));
        batchHeaderQueue.setCreatedBy(rs.getString("created_by"));
        batchHeaderQueue.setUpdated(rs.getObject("updated", LocalDateTime.class));
        batchHeaderQueue.setUpdatedBy(rs.getString("updated_by"));

        return batchHeaderQueue;
    }
}
