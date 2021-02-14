package com.company;

import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;

class Decryption {
    private KeyPair key;
    private String cipherText;
    private String plainText;

    Decryption(String cipherText, KeyPair key) {
        this.cipherText = cipherText;
        this.key = key;

        decrypt(cipherText, key);
    }

    Decryption(Path filePath, KeyPair key, String userName) throws IOException {
        this(Files.readString(filePath), key);

        plainTextFile(userName);
    }

    public String getPlainText() { return plainText; }

    private String decrypt(String cipherText, KeyPair key) {
        return plainText = new String((new BigInteger(cipherText)).modPow(key.getKey(), key.getN()).toByteArray());
    }

    private void plainTextFile(String filePrefix) throws IOException {
        File plainTextFile = new File(filePrefix + "_plainText.txt");
        Files.writeString(plainTextFile.toPath(), plainText);
        System.out.println("Your message has been decrypted and saved as " + plainTextFile );
    }
}
