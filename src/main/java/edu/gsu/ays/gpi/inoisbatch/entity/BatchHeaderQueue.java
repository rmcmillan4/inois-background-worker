package edu.gsu.ays.gpi.inoisbatch.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class BatchHeaderQueue {

    private Long id;

    private String batchIdentifier;

    private String filePath;

    private String clientIp;

    private int entityUser;

    private int registeredEntity;

    private String effectiveStartDate;

    private String effectiveEndDate;

    private String statusId;

    private LocalDateTime created;

    private String createdBy;

    private LocalDateTime updated;

    private String updatedBy;


    public BatchHeaderQueue() {}


    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getFilePath() { return filePath; }

    public void setFilePath(String filePath) { this.filePath = filePath; }

    public String getEffectiveStartDate() { return effectiveStartDate; }

    public void setEffectiveStartDate(String effectiveStartDate) { this.effectiveStartDate = effectiveStartDate; }

    public String getEffectiveEndDate() { return effectiveEndDate; }

    public void setEffectiveEndDate(String effectiveEndDate) { this.effectiveEndDate = effectiveEndDate; }

    public String getClientIp() {return clientIp;}

    public void setClientIp(String clientIp) { this.clientIp = clientIp; }

    public String getBatchIdentifier() {return batchIdentifier;}

    public void setBatchIdentifier(String batchIdentifier) { this.batchIdentifier = batchIdentifier; }

    public int getRegisteredEntity() {return registeredEntity;}

    public void setRegisteredEntity(int registeredEntity) { this.registeredEntity = registeredEntity; }

    public int getEntityUser() {return entityUser;}

    public void setEntityUser(int entityUser) { this.entityUser = entityUser; }

    public String getStatusId() { return statusId; }

    public void setStatusId(String statusId) { this.statusId = statusId; }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) { this.created = created; }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) { this.updated = updated; }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BatchHeaderQueue that = (BatchHeaderQueue) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(clientIp, that.clientIp) &&
                Objects.equals(filePath, that.filePath) &&
                Objects.equals(batchIdentifier, that.batchIdentifier) &&
                Objects.equals(registeredEntity, that.registeredEntity) &&
                Objects.equals(effectiveStartDate, that.effectiveStartDate) &&
                Objects.equals(effectiveEndDate, that.effectiveEndDate) &&
                Objects.equals(entityUser, that.entityUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, registeredEntity, entityUser, clientIp, filePath, batchIdentifier, effectiveStartDate, effectiveEndDate);
    }


    @Override
    public String toString() {
        return "BatchHeaderQueue{" +
                "batchId='" + id + '\'' +
                "batchIdentifier='" + batchIdentifier + '\'' +
                "registeredEntity='" + registeredEntity + '\'' +
                "entityUser='" + entityUser + '\'' +
                '}';
    }
}
