package com.company;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.math.BigInteger;

class KeyPair implements java.io.Serializable {
    private static final long serialVersionUID = 4L;
    private BigInteger key;
    private BigInteger n;

    KeyPair(BigInteger key, BigInteger n) {
        this.setKey(key);
        this.setN(n);
    }

    KeyPair(KeyPair key, String fileName) {
        this(readKey(fileName).getKey(), readKey(fileName).getN());
        key = new KeyPair(this.key, this.n);
    }

    public BigInteger getN() { return this.n; }
    public void setN(BigInteger n) { this.n = n; }

    public BigInteger getKey() { return this.key; }
    public void setKey(BigInteger key) { this.key = key; }

    private static KeyPair readKey(String fileName) {
        KeyPair key = null;
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            key = (KeyPair) in.readObject();
            in.close();
            System.out.println("Read key from " + fileName);
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        }
        return key;
    }
}
