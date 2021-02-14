package com.company;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

class Encryption {
    private String cipherText;
    private String message;
    private KeyPair key;

    Encryption(String message, KeyPair key) {
        this.message = message;
        this.key = key;

        encrypt(message, key);
    }

    Encryption(Path filePath, KeyPair key, String userName) throws IOException {
        this(Files.readString(filePath), key);

        cipherTextFile(userName);
    }

    public String getCipherText() {
        return cipherText;
    }

    private String encrypt(String message, KeyPair key) {
        return cipherText = (new BigInteger(message.getBytes(StandardCharsets.UTF_8))).modPow(key.getKey(), key.getN()).toString();
    }

    private void cipherTextFile(String userName) throws IOException {
        File cipherFile = new File(userName + "_cipher.txt");
        Files.writeString(cipherFile.toPath(), cipherText);
        System.out.println("Your message has been encrypted and saved as " + cipherFile);
    }
}