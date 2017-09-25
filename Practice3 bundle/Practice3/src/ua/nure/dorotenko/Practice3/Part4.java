package ua.nure.dorotenko.Practice3;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Part4 {
    public static String hash(String input, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        try {
            digest.update(input.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            System.out.println(e);
        }
        byte[] hash = digest.digest();
        StringBuffer result = new StringBuffer();
        for(byte b : hash){
            StringBuilder binary = new StringBuilder(Integer.toBinaryString(b));
            if(binary.length() > 7){
                binary = new StringBuilder(binary.substring(binary.length() - 8, binary.length()));
            } else{
                binary.reverse();
                while(binary.length() != 8){
                    binary.append("0");
                }
                binary.reverse();
            }
            StringBuilder appendable = new StringBuilder(Integer.toHexString(Integer.parseInt(binary.toString(), 2)));
            if(appendable.length() != 2){
                appendable.reverse().append(0).reverse();
            }
            result.append(appendable);
        }
        return result.toString().toUpperCase();
    }

    public static void main(String[] args) {
        try{
            System.out.println(hash("password", "SHA-256"));
            System.out.println(hash("passwort", "SHA-256"));
        } catch (NoSuchAlgorithmException ex){
            System.out.println(ex);
        }

    }
}
