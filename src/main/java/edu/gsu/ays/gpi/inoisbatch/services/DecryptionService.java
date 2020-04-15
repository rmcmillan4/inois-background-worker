package edu.gsu.ays.gpi.inoisbatch.services;

import com.macasaet.fernet.*;
import java.time.temporal.TemporalAmount;
import java.time.Duration;
import java.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DecryptionService {

    private static Logger log = LoggerFactory.getLogger(FileService.class);

    public static String decryptFile(String fileContents) {
        log.info("Decrypting file...");

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
}
