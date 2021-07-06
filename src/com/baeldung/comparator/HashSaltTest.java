package com.baeldung.comparator;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


public class HashSaltTest {

    public static String encode(String password, String saltKey) throws NoSuchAlgorithmException, IOException {
      
    	byte[] salt =  Base64.getDecoder().decode(saltKey);

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.reset();
        digest.update(salt);

        byte[] btPass = digest.digest(password.getBytes("UTF-8"));
        btPass = digest.digest(btPass);

        String hashedSSN = new String(Base64.getEncoder().encode(btPass));
        return hashedSSN;
    }


    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        String ssn = "010101-0101";
        String saltKey = "fdt4tfdgvrbt3t34t34t3cw";

        // Assume done by Scheduler
        String hash1 = HashSaltTest.encode(ssn, saltKey);
        System.out.println(hash1);

        // Assume done by workflow automator
        String hash2 = HashSaltTest.encode(ssn, saltKey);
        System.out.println(hash2);

        if(hash1.equalsIgnoreCase(hash2))
            System.out.println("Both hash Matches..");
        else
            System.out.println("Hash matches fails..");
    }
}