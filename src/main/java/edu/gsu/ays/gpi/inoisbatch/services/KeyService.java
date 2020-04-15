package edu.gsu.ays.gpi.inoisbatch.services;

import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.security.keyvault.secrets.models.KeyVaultSecret;
import com.azure.security.keyvault.secrets.models.SecretProperties;

import java.util.ArrayList;
import java.util.List;

public class KeyService {

    private static final String clientId = System.getenv("APPSETTING_KvAccessClientId");
    private static final String clientSecret = System.getenv("APPSETTING_KvAccessClientSecret");
    private static final String keyVaultUrl = System.getenv("APPSETTING_KvUrl");
    private static final String tenantId = System.getenv("APPSETTING_TenantId");
    private static final String encryptionKeyName = "B64FileIngestEncryptionKey";
    //private static String saltKeyName = "B64ClientSalt";
    private static String internalSaltKeyName = "B64InternalSalt";


    public static SecretClient buildSecretClient() {

        final ClientSecretCredential clientSecretCredential = new ClientSecretCredentialBuilder().clientId(clientId)
                .clientSecret(clientSecret).tenantId(tenantId).build();

        return new SecretClientBuilder().vaultUrl(keyVaultUrl).credential(clientSecretCredential).buildClient();
    }

    public static String getEncryptionKey() {
        SecretClient client = buildSecretClient();
        KeyVaultSecret key = client.getSecret(encryptionKeyName);
        return key.getValue();
    }

    public static String getCurrentInternalSaltKey() {
        SecretClient client = buildSecretClient();
        KeyVaultSecret key = client.getSecret(internalSaltKeyName);
        return key.getValue();
    }

    public static List<KeyVaultSecret> getPreviousInternalSaltKeys() {
        SecretClient client = buildSecretClient();
        List<KeyVaultSecret> allSaltVersions = new ArrayList<>();
        for (SecretProperties secretProperties : client.listPropertiesOfSecretVersions(internalSaltKeyName)) {
            KeyVaultSecret secretWithValue  = client.getSecret(secretProperties.getName(), secretProperties.getVersion());
            //System.out.printf("Received secret with name %s and value %s \n", secretWithValue.getName(), secretWithValue.getValue());
            allSaltVersions.add(secretWithValue);
        }
        return allSaltVersions;
    }
}
