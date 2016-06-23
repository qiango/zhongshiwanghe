package com.hongzhi.zswh.util.encryption;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

/**   Twitter : @taylorwang789 
 * Creat time : May 17, 2016    11:35:21 AM
 */
public class RSAEncryption {
    
    private static  String privateKey = "/Users/taylor/doc/temp/zswhssl/pkcs8_key";
    private static String publicKey = "/Users/taylor/doc/temp/zswhssl/rsa_public_key.pem";


    public static PrivateKey getPrivateKey(String filename)throws Exception {
        File f = new File(filename);
        FileInputStream fis = new FileInputStream(f);
        DataInputStream dis = new DataInputStream(fis);
        byte[] keyBytes = new byte[(int) f.length()];
        dis.readFully(keyBytes);
        dis.close();
        PKCS8EncodedKeySpec spec =new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
      }
    
      public static PublicKey getPublicKey(String filename) throws Exception {
          File f = new File(filename);
          FileInputStream fis = new FileInputStream(f);
          DataInputStream dis = new DataInputStream(fis);
          byte[] keyBytes = new byte[(int) f.length()];
          dis.readFully(keyBytes);
          dis.close();
          X509EncodedKeySpec spec =new X509EncodedKeySpec(keyBytes);
          KeyFactory kf = KeyFactory.getInstance("RSA");
          return kf.generatePublic(spec);
      }

    
    public static void main(String[] args) throws Exception, InvalidKeySpecException, IOException {
          PrivateKey privKey =  getPrivateKey("/Users/taylor/doc/temp/zswhssl/pkcs8_key");
//          PublicKey  publKey = getPublicKey("/Users/taylor/doc/temp/zswhssl/rsa_public_key.pem");
          
          String input = "service=\"mobile.securitypay.pay\"partner=\"2088101568338364\"_input_charset=\"utf-8\"notify_url=\"http://localhost:";
          Cipher cipher = Cipher.getInstance("RSA");        
//          RSAPublicKey pubKey = (RSAPublicKey) PublicKeyReader.get("d:/publicKeyFile");
          cipher.init(Cipher.ENCRYPT_MODE, privKey);
          byte[] cipherText = cipher.doFinal(input.getBytes());
          //加密后的东西
          System.out.println("cipher: " + new String(cipherText));        
//          //开始解密
//          cipher.init(Cipher.DECRYPT_MODE, publKey); 
//          byte[] plainText = cipher.doFinal(cipherText);
//          System.out.println("plain : " + new String(plainText));
    }
    
    public static String  encodeWithPrivateKey(String input_string ) {
        try {
            PrivateKey privKey = getPrivateKey(privateKey);
            Cipher  cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privKey);
            byte[] cipherText = cipher.doFinal(input_string.getBytes());
            return  new String(cipherText) ;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }        
    }
    
}
