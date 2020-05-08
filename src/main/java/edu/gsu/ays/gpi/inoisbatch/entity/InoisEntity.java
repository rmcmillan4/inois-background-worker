package edu.gsu.ays.gpi.inoisbatch.entity;


import com.azure.security.keyvault.secrets.models.KeyVaultSecret;
import java.io.IOException;
import java.util.List;

public interface InoisEntity {

    public void hash(String saltKey);

    public void readBatch(String csv) throws IOException;

    public void writeBatch();

    public List<? extends InoisEntity> retrieveBatch();

    public void generatePreviousHashes(List<KeyVaultSecret> saltKeys);

    public void writeHashHistory();

}
