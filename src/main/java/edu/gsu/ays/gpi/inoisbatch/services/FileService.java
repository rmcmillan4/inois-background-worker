package edu.gsu.ays.gpi.inoisbatch.services;


import java.io.*;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileService {

    private static Logger log = LoggerFactory.getLogger(FileService.class);

    private static final String accountKey = System.getenv("APPSETTING_StorageAccountKey");
    private static final String accountName = System.getenv("APPSETTING_StorageAccountName");
    private static final String containerName = System.getenv("APPSETTING_BlobContainerIngest");



    public static String retrieveBlob(String file) throws IOException {

        log.info(String.format("Downloading file '%s' from Azure Blob Storage...", file));

        final String connectionString = buildConnectionString();
        String blobContents;

        BlobClient blobClient = buildBlobClient(connectionString, file);

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()){
            blobClient.download(outputStream);
            blobContents = outputStream.toString();
        }
        catch (Exception ex){
            log.error("File download failed.");
            log.error(ex.getMessage());
            throw ex;
        }

        log.info("File download successful.");

        return blobContents;
    }

    private static BlobClient buildBlobClient(String connectionString, String fileName) {
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connectionString).buildClient();
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
        return containerClient.getBlobClient(fileName);
    }

    private static String buildConnectionString() {
        return "DefaultEndpointsProtocol=https;AccountName=" + accountName + ";AccountKey=" + accountKey
                + ";EndpointSuffix=core.windows.net";
    }
}
