package com.baeldung.comparator;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

public class EncryptDecryptTest {

    private static final String UNICODE_FORMAT = "UTF8";
    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";

    public String encrypt(String unencryptedString) {
        String encryptedString = null;
        try {
        	Cipher cipher = getCipher();
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey());
            byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
            byte[] encryptedText = cipher.doFinal(plainText);
            encryptedString = new String(Base64.getEncoder().encode(encryptedText));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedString;
    }
    
    
    private SecretKey getSecretKey() throws Exception {
    	 String encryptionSecretKey = "ThisWillBeTheSecretSharedWithDecrypter";
         byte[] arrayBytes = encryptionSecretKey.getBytes(UNICODE_FORMAT);
         KeySpec ks = new DESedeKeySpec(arrayBytes);
         SecretKeyFactory skf = SecretKeyFactory.getInstance(DESEDE_ENCRYPTION_SCHEME);
         return skf.generateSecret(ks);
    }

    private Cipher getCipher() throws Exception {
         return Cipher.getInstance(DESEDE_ENCRYPTION_SCHEME);
    }

    public String decrypt(String encryptedString) {
        String decryptedText=null;
        try {
        	Cipher cipher = getCipher();
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey());
            byte[] encryptedText = Base64.getDecoder().decode(encryptedString);
            byte[] plainText = cipher.doFinal(encryptedText);
            decryptedText= new String(plainText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedText;
    }


    public static void main(String args []) throws Exception
    {
        EncryptDecryptTest td= new EncryptDecryptTest();

        String target="010101-0101";
        String encrypted=td.encrypt(target);
        String decrypted=td.decrypt(encrypted);

        System.out.println("String To Encrypt: "+ target);
        System.out.println("Encrypted String:" + encrypted);
        System.out.println("Decrypted String:" + decrypted);

    }

}