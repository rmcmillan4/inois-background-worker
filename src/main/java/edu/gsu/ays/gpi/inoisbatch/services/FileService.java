package edu.gsu.ays.gpi.inoisbatch.services;


import java.io.*;
import java.util.UUID;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;

import edu.gsu.ays.gpi.inoisbatch.tasks.ProcessEntityData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

    private static Logger log = LoggerFactory.getLogger(FileService.class);

    private static final String accountKey = System.getenv("APPSETTING_StorageAccountKey");
    private static final String accountName = System.getenv("APPSETTING_StorageAccountName");
    private static final String containerName = System.getenv("APPSETTING_BlobContainerIngest");

    public static void retrieveBlob(String file) throws IOException {

        final String connectionString = buildConnectionString();
        //final String fileName = UUID.randomUUID().toString();

        BlobClient blobClient = buildBlobClient(connectionString, file);
        //InputStream inputStream = new BufferedInputStream(file.getInputStream());
        //blobClient.upload(inputStream, file.getBytes().length);
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()){
            blobClient.download(outputStream);
            log.info("File downloaded: ");
            log.info(outputStream.toString());
        }


        //log.info("File downloaded: " + fileName);



    }

    private static BlobClient buildBlobClient(String connectionString, String fileName) {
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connectionString)
                .buildClient();
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
        BlobClient blobClient = containerClient.getBlobClient(fileName);
        return blobClient;
    }

    private static String buildConnectionString() {
        return "DefaultEndpointsProtocol=https;AccountName=" + accountName + ";AccountKey=" + accountKey
                + ";EndpointSuffix=core.windows.net";
    }
}
