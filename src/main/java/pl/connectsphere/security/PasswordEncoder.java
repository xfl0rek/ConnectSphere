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

    public String decrypt(String encryptedPassword) {
        try {
            byte[] key1 = Arrays.copyOf("jfcd1M2qVBrgjgJPBBEe".getBytes(StandardCharsets.UTF_8), 8);
            byte[] key2 = Arrays.copyOf("hsQfeN7RYpjFE4B95iSC".getBytes(StandardCharsets.UTF_8), 8);
            byte[] key3 = Arrays.copyOf("OFKj3BdSVD65lFIQ6cBu".getBytes(StandardCharsets.UTF_8), 8);

            tripleDES.setKeys(key1, key2, key3);

            byte[] encryptedText = hexToBytes(encryptedPassword);
            byte[] decryptedPassword = tripleDES.decryptMessage(encryptedText);

            return new String(decryptedPassword, StandardCharsets.UTF_8);
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

    private byte[] hexToBytes(String hex) {
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }
        return data;
    }
}
