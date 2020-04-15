package edu.gsu.ays.gpi.inoisbatch.services;

import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;

public class HashService {

    public static String hashValue(String valueToHash, String saltKey) {
        String saltedValue = valueToHash + saltKey;
        SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512();

        byte[] digest = digestSHA3.digest(saltedValue.getBytes());

        return Hex.toHexString(digest);
    }
}
