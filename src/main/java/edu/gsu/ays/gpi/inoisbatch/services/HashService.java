package edu.gsu.ays.gpi.inoisbatch.services;

import com.azure.security.keyvault.secrets.models.KeyVaultSecret;
import edu.gsu.ays.gpi.inoisbatch.exceptions.HashingError;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;

import java.util.Arrays;
import java.util.List;

public class HashService {

    public static String hashValue(String valueToHash, String saltKey) {
        String saltedValue = valueToHash + saltKey;
        SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512();

        byte[] digest = digestSHA3.digest(saltedValue.getBytes());

        return Hex.toHexString(digest);
    }

    public static String generatePreviousInternalHashes(String previousClientHashes, List<KeyVaultSecret> allInternalSaltVersions){
        previousClientHashes = previousClientHashes.trim();
        String previousInternalHashes = "";
        List<KeyVaultSecret> previousSalts = allInternalSaltVersions.subList(1, allInternalSaltVersions.size());
        String[] previousHashArray = previousClientHashes.split(" ");
        List<String> previousHashList = Arrays.asList(previousHashArray);
        if (!(previousHashList.size() == previousSalts.size())) throw new HashingError(("The number of previous (client) hash values is not equal to the number of previous internal salt versions."));
        for (int i = 0; i < previousHashList.size(); i++){
            previousInternalHashes += hashValue(previousHashList.get(i), previousSalts.get(i).getValue());
            previousInternalHashes += " ";
        }
        return previousInternalHashes;
    }
}
