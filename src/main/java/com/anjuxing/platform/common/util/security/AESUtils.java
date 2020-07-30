package com.anjuxing.platform.common.util.security;

import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES加密工具类
 */
public class AESUtils {
	
	private static final String AES_KEY = "anjuxing";

    /**
     * 生成密钥
     * @param key
     * @return
     * @throws Exception
     */
    private static byte[] initKey(String key) throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128, new SecureRandom(key.getBytes()));  //192 256
        SecretKey secretKey = keyGen.generateKey();
        return secretKey.getEncoded();
    }

    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] cipherBytes = cipher.doFinal(data);
        return cipherBytes;
    }

    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] plainBytes = cipher.doFinal(data);
        return plainBytes;
    }

    /**
     * AES加密数据
     * @param data
     * @return
     */
    public static String encrypt(String data){
    	return encrypt(data, AES_KEY);
    }

    /**
     * AES自定义密钥加密数据
     * @param data
     * @param key
     * @return
     */
    public static String encrypt(String data, String key){
        String encStr = data;
        try {
            byte[] enc = encrypt(data.getBytes("UTF-8"), initKey(key));
            encStr =  Base64.getEncoder().encodeToString(enc);
        }catch (Exception e){
            e.printStackTrace();
        }
        return encStr;
    }

    /**
     * AES解密数据
     * @param data
     * @return
     */
    public static String decrypt(String data){
    	return decrypt(data, AES_KEY);
    }

    /**
     * AES自定义密钥解密数据
     * @param data
     * @param key
     * @return
     */
    public static String decrypt(String data, String key){
        String decStr = data;
        try {
            byte[] dataByte = Base64.getDecoder().decode(data);
            byte[] dec = decrypt(dataByte, initKey(key));
            decStr = new String(dec,"UTF-8");
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
