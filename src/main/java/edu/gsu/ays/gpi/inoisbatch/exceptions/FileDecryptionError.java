package edu.gsu.ays.gpi.inoisbatch.exceptions;

public class FileDecryptionError extends RuntimeException {
    public FileDecryptionError(String message) {
        super(message);
    }
}
