package com.ywsoftware.oa.common.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AesHelper {

    private static final SecretKeySpec AESKEY;

    static {
        SecretKeySpec keySpec = null;
        String password = "wqd!21ewd4{}32";

        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(password.getBytes());
            kgen.init(128, random);

            SecretKey secretKey = kgen.generateKey();
            keySpec = new SecretKeySpec(secretKey.getEncoded(), "AES");
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }

        AESKEY = keySpec;
    }

    /**
     * 加密
     *
     * @param plain 明文
     * @return 密文
     */
    public static String encrypt(String plain) throws Exception {
        byte[] buffer = plain.getBytes("UTF-8");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, AESKEY);

        String cipherText = new String(Base64.getEncoder().encode(cipher.doFinal(buffer)), "UTF-8");

        return cipherText.replaceAll("[\r\n]", "");
    }

    /**
     * 解密
     *
     * @param cipherText 密文
     * @return 明文
     */
    public static String decrypt(String cipherText) throws Exception {
        byte[] buffer = Base64.getDecoder().decode(cipherText.getBytes("UTF-8"));

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, AESKEY);

        return new String(cipher.doFinal(buffer), "UTF-8");
    }
}
