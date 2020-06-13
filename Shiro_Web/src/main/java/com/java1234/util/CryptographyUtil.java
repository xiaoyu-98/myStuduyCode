package com.java1234.util;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.Md5Hash;
import sun.security.util.Password;

/**
 * 类描述:
 *
 * @author xiaoyu
 * on 2020/6/13
 */
public class CryptographyUtil {

    /**
     * 加密
     *
     * @param str
     * @return
     */
    public static String encBase64(String str) {
        return Base64.encodeToString(str.getBytes());
    }

    /**
     * 解密
     *
     * @param str
     * @return
     */
    public static String decBase64(String str) {
        return Base64.decodeToString(str);
    }


    public static String md5(String str, String salt) {
        Md5Hash md5Hash = new Md5Hash(str, salt);
       return md5Hash.toString();
    }

    public static void main(String[] args) {
        System.out.println("加密后："+ encBase64("12345"));
        System.out.println("解密后："+ decBase64(encBase64("12345")));
        System.out.println(md5("12345","java"));
    }
}
