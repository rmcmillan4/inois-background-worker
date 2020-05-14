package edu.gsu.ays.gpi.inoisbatch.services;

import com.macasaet.fernet.*;
import java.time.temporal.TemporalAmount;
import java.time.Duration;
import java.time.Instant;

import edu.gsu.ays.gpi.inoisbatch.exceptions.FileDecryptionError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DecryptionService {

    private static Logger log = LoggerFactory.getLogger(DecryptionService.class);

    public static String decryptFile(String fileContents) {
        log.info("Decrypting file...");

        try{
            final Key key = new Key(KeyService.getEncryptionKey());
            final Token token = Token.fromString(fileContents);

            final Validator < String > validator = new StringValidator() {
                public TemporalAmount getTimeToLive() {
                    return Duration.ofSeconds(Instant.MAX.getEpochSecond());
                }
            };

            final String payload = token.validateAndDecrypt(key, validator);
            log.info("Decryption successful.");

            return payload;
        }

        catch (Exception ex){
            log.error("File decryption failed.");
            log.error(ex.getMessage());
            throw new FileDecryptionError("Decryption failed due to invalid file contents.");
        }

    }
}
