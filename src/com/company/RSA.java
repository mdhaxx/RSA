package com.company;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

class RSA {
    private String userName;
    private String fileName;
    private String publicKey;
    private String privateKey;
    private KeyPair key;
    private Scanner in;
    private int selection;

    RSA() throws IOException {
        init();
        menu();
    }

    public String getUserName() { return userName; }

    private void init() {
    in = new Scanner(System.in);
    System.out.print("Enter user name: ");
    userName = in.next();
    System.out.println("\nWelcome " + userName + "!");
    }

    private void menu() throws IOException {
        System.out.println("\nMenu: Select an option and press enter to continue\n");
        System.out.println("1. Generate RSA Keys");
        System.out.println("2. Encrypt String Message");
        System.out.println("3. Decrypt String Message");
        System.out.println("4. Encrypt Text File");
        System.out.println("5. Decrypt Text File");
        System.out.println("6. Encrypt Binary File");
        System.out.println("7. Decrypt Binary File");
        System.out.println("8. Sign");
        System.out.println("9. Verify Signature");
        System.out.println("10. Exit");

        selection = in.nextInt();
        selection();
    }

    private void selection() throws IOException {
        publicKey = getUserName() + "_pub.key";
        privateKey = getUserName() + "_priv.key";

        if (selection != 1) {
            fileName = ((selection % 2) == 0) ? publicKey : privateKey;
            key = new KeyPair(key, fileName);
        }

        Scanner input = new Scanner(System.in);
        switch (selection) {

            case 1 -> {
                //Generate RSA keys
                System.out.print("Enter key size: ");
                int bitLength = in.nextInt();
                new KeyGenerator(getUserName(), bitLength);
                menu();
            }
            case 2 -> {
                //Encrypt String Message
                System.out.print("Enter message to encrypt: ");
                String message = input.nextLine();
                Encryption encryption = new Encryption(message, key);
                System.out.println("Encrypted message: " + encryption.getCipherText());
                menu();
            }
            case 3 -> {
                //Decrypt String Message
                System.out.println("Enter ciphertext to decrypt: ");
                String cipherText = input.nextLine();
                Decryption decryption = new Decryption(cipherText, key);
                System.out.println("Decrypted message: " + decryption.getPlainText());
                menu();
            }
            case 4 -> {
                //Encrypt Text File
                System.out.print("Enter the name of the text file you want to encrypt: ");
                Path filePath = Paths.get(input.nextLine());
                new Encryption(filePath, key, getUserName());
                menu();
            }
            case 5 -> {
                //Decrypt Text File
                System.out.print("Enter the name of the text file you want to decrypt: ");
                Path filePath = Paths.get(input.nextLine());
                new Decryption(filePath, key, getUserName());
                menu();
            }
            case 6 -> {
                //Encrypt Binary File
                menu();
            }
            case 7 -> {
                //Decrypt Binary File
                menu();
            }
            case 8 -> {
                //Sign
                menu();
            }
            case 9 -> {
                //Verify Signature
                menu();
            }
            case 10 -> {
                //Exit
            }
        }
    }

    public static void main(String[] args) throws IOException { new RSA(); }

}