package edu.gsu.ays.gpi.inoisbatch.services;

import com.azure.security.keyvault.secrets.models.KeyVaultSecret;
import edu.gsu.ays.gpi.inoisbatch.entity.InoisEntity;
import edu.gsu.ays.gpi.inoisbatch.exceptions.DBTransactionError;
import edu.gsu.ays.gpi.inoisbatch.exceptions.HashingError;
import edu.gsu.ays.gpi.inoisbatch.exceptions.InvalidFileFormatError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class RecordService {

    private static Logger log = LoggerFactory.getLogger(RecordService.class);

    public static void processCsv(InoisEntity entity, String csv){
        readCsv(entity, csv);
        hashRecords(entity);
        writeRecords(entity);
    }

    public static void readCsv(InoisEntity entity, String csv){
        log.info("Parsing CSV file...");

        try{
            entity.readBatch(csv);
        }
        catch (Exception ex){
            log.error("CSV parsing failed.");
            log.error(ex.getMessage());
            throw new InvalidFileFormatError("Failed to parse CSV due to invalid file contents.");
        }
        log.info("Successfully parsed CSV file.");
    }

    public static void hashRecords(InoisEntity entity){
        log.info(String.format("Hashing %s records...", entity.retrieveBatch().size()));

        try{
            String saltKey = KeyService.getCurrentInternalSaltKey();
            List<KeyVaultSecret> allInternalSaltVersions = KeyService.getAllInternalSaltVersions();
            for (InoisEntity record: entity.retrieveBatch()){
                record.hash(saltKey);
                record.generatePreviousHashes(allInternalSaltVersions);
                //log.info("Hashed record: " + record.toString());
            }
        }
        catch (Exception ex){
            log.error("Record Hashing Failed");
            log.error(ex.getMessage());
            throw new HashingError("Failed to hash parsed records.");
        }
        log.info("Successfully hashed all records.");
    }

    public static void writeRecords(InoisEntity entity) {
        log.info("Writings records...");

        try{
            entity.writeBatch();
            entity.writeHashHistory();
        }
        catch (Exception ex){
            log.error("Record write to the DB failed");
            log.error(ex.getMessage());
            throw new DBTransactionError("Failed to write processed records to the DB.");
        }
        log.info("Records successfully written.");
    }
}
