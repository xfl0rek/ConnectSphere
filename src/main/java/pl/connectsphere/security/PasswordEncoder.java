package pl.connectsphere.security;

import pl.connectsphere.security.EncryptionAlgorithm.TripleDES;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class PasswordEncoder {
    TripleDES tripleDES;

    public PasswordEncoder() {
        tripleDES = new TripleDES();
    }

    public String encrypt(String password) {
        try {
            byte[] key1 = Arrays.copyOf("jfcd1M2qVBrgjgJPBBEe".getBytes(StandardCharsets.UTF_8), 8);
            byte[] key2 = Arrays.copyOf("hsQfeN7RYpjFE4B95iSC".getBytes(StandardCharsets.UTF_8), 8);
            byte[] key3 = Arrays.copyOf("OFKj3BdSVD65lFIQ6cBu".getBytes(StandardCharsets.UTF_8), 8);

            tripleDES.setKeys(key1, key2, key3);

            byte[] text = password.getBytes(StandardCharsets.UTF_8);
            byte[] encryptedPassword = tripleDES.encryptMessage(text);

            return bytesToHex(encryptedPassword);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
