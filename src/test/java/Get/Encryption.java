package Get;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Encryption {

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        // Generate or obtain a secret key
        SecretKey secretKey = generateSecretKey();

        // Convert the secret key to a string representation
        String secretKeyString = keyToString(secretKey);
        System.out.println("KeyString: " + secretKeyString);

        // Your sensitive data to be encrypted
        String originalData = "Bearer 6442e439f9a859bb160324bafeb5ad9963f063684860e0ac280b561d3bcdf7ba";

        // Encrypt the data using the secret key string
        String encryptedData = encrypt(originalData, secretKeyString);
        System.out.println("Encrypted: " + encryptedData);

        // Use a separate decryptor with the secret key string to decrypt the data
        String decryptedData = decrypt(encryptedData, secretKeyString);
        System.out.println("Decrypted: " + decryptedData);
    }

    private static SecretKey generateSecretKey() throws NoSuchAlgorithmException {
        // Use a key generation algorithm (e.g., AES)
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128); // 128-bit key size
        return keyGenerator.generateKey();
    }

    private static String keyToString(SecretKey secretKey) {
        // Convert the SecretKey to a string representation (Base64 encoding)
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    private static SecretKey stringToKey(String secretKeyString) {
        // Convert the string representation back to a SecretKey
        byte[] decodedKey = Base64.getDecoder().decode(secretKeyString);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }

    private static String encrypt(String data, String secretKeyString) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKey secretKey = stringToKey(secretKeyString);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    private static String decrypt(String encryptedData, String secretKeyString) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKey secretKey = stringToKey(secretKeyString);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }
}
