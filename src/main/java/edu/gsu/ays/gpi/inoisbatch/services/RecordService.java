package edu.gsu.ays.gpi.inoisbatch.services;

import edu.gsu.ays.gpi.inoisbatch.entity.InoisEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class RecordService {

    private static Logger log = LoggerFactory.getLogger(RecordService.class);

    public static void processCsv(InoisEntity entity, String csv){
        readCsv(entity, csv);
        hashRecords(entity);
    }

    public static void readCsv(InoisEntity entity, String csv){
        log.info("Parsing CSV file...");

        try{
            entity.readBatch(csv);
        }
        catch (IOException ex){
            log.error("Failed to parse CSV file");
            log.error(ex.getMessage());
            throw new RuntimeException("Failed to parse CSV file");
        }
        log.info("Successfully parsed CSV file.");
    }

    public static void hashRecords(InoisEntity entity){
        log.info("Hashing all records...");

        try{
            String saltKey = KeyService.getCurrentInternalSaltKey();
            for (InoisEntity record: entity.retrieveBatch()){
                log.info("Hashing record: " + record.toString());
                record.hash(saltKey);
                log.info("Hashed record: " + record.toString());
            }
        }
        catch (Exception ex){
            log.error("Record Hashing Failed");
            log.error(ex.getMessage());
            throw new RuntimeException("Failed to hash parsed records.");
        }
        log.info("Successfully hashed all records.");
    }
}
