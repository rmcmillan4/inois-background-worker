package edu.gsu.ays.gpi.inoisbatch.utils;

public class FileProcessingStatus {
    public static final long INGEST_PENDING = 1L;
    public static final long PROCESSING_FILE = 2L;
    public static final long COMPLETED_SUCCESSFULLY = 3L;
    public static final long INVALID_FILE_FORMAT_ERROR = 4L;
    public static final long FILE_DECRYPTION_ERROR = 5L;
    public static final long HASHING_ERROR = 6L;
    public static final long DB_TRANSACTION_ERROR = 7L;

}
