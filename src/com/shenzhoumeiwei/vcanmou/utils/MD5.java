package com.shenzhoumeiwei.vcanmou.utils;

/**
 * 为String生成MD5码
 */
import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5 {

    public static String encodeMD5String(String str) {
        return encode(str, "MD5");
    }

    private static String encode(String str, String method) {
        MessageDigest md = null;
        String dstr = null;
        try {
            md = MessageDigest.getInstance(method);
            md.update(str.getBytes());
            dstr = new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dstr;
    }

}
