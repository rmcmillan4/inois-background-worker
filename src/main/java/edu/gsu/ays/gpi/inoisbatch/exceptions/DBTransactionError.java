package edu.gsu.ays.gpi.inoisbatch.exceptions;

public class DBTransactionError extends RuntimeException {
    public DBTransactionError(String message) {
        super(message);
    }
}
