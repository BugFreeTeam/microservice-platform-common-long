package com.anjuxing.platform.common.util.security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * DES数据加密工具类
 */
public class DESUtils {

    private static final String DES_KEY = "anjuxing";

    /*
     * 生成密钥
     */
    private static byte[] initKey(String key) throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("DESede");
        keyGen.init(new SecureRandom(key.getBytes()));  //112 168
        SecretKey secretKey = keyGen.generateKey();
        return secretKey.getEncoded();
    }

    /*
     * 3DES 加密
     */
    public static byte[] encrypt(byte[] data, String key) throws Exception {
        byte[] desKey = initKey(key);
        SecretKey secretKey = new SecretKeySpec(desKey, "DESede");
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] cipherBytes = cipher.doFinal(data);
        return cipherBytes;
    }

    /*
     * 3DES 解密
     */
    public static byte[] decrypt(byte[] data, String key) throws Exception {
        byte[] desKey = initKey(key);
        SecretKey secretKey = new SecretKeySpec(desKey, "DESede");
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] plainBytes = cipher.doFinal(data);
        return plainBytes;
    }

    public static String encrypt(String data){
        return encrypt(data, DES_KEY);
    }

    public static String encrypt(String data, String key){
        String encStr = "";
        try {
            byte[] enc = encrypt(data.getBytes("UTF-8"), key);
            encStr = Base64.getEncoder().encodeToString(enc);
        }catch (Exception e){
            e.printStackTrace();
        }
        return encStr;
    }

    public static String decrypt(String data){
        return decrypt(data, DES_KEY);
    }

    public static String decrypt(String data, String key){
        String decStr = data;
        try {
            byte[] dataByte = Base64.getDecoder().decode(data);
            byte[] dec = decrypt(dataByte, key);
            decStr = new String(dec, "UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        return decStr;
    }

    public static void main(String[] args) throws Exception {
        String str = "深圳市安居星信息科技有限公司";
        System.out.println(Base64.getEncoder().encodeToString(str.getBytes("UTF-8")));
        //默认密钥
        String enc = encrypt(str);
        System.out.println(enc);
        String dec = decrypt(enc);
        System.out.println(dec);
        //自定义密钥
        String key = "123";
        enc = encrypt(str, key);
        System.out.println(enc);
        dec = decrypt(enc, key);
        System.out.println(dec);
    }

}
