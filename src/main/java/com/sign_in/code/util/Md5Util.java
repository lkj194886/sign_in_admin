package com.sign_in.code.util;

import org.apache.commons.codec.binary.Hex;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.util.Random;

/**
 * @Classname Md5Util
 * @Description MD5加密加盐
 * @Date 2020/10/20 1:13
 * @Created by wgg
 */
@Component
public class Md5Util {
    //生成随机加盐的密码
    public  String generate(String code) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append(random.nextInt(99999999)).append(random.nextInt(99999999));
        int len = sb.length();
        if (len < 16) {
            for (int i = 0; i < 16 - len; i++) {
                sb.append("0");
            }
        }
        String salt = sb.toString();
        code = md5hex(code + salt);
        char[] cs = new char[48];
        for (int i = 0; i < 48; i += 3) {
            cs[i] = code.charAt(i / 3 * 2);
            char c = salt.charAt(i / 3);
            cs[i + 1] = c;
            cs[i + 2] = code.charAt(i / 3 * 2 + 1);
        }
        return new String(cs);
    }

    /**
     * 获取十六进制字符串形式的MD5摘要
     */
    public String md5hex(String src) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] bs = messageDigest.digest(src.getBytes());
            return new String(new Hex().encode(bs));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //校验方法
    public   boolean verify(String code, String  md5){
        char[] cs1 = new char[32];
        char[] cs2 = new char[16];
        for (int i = 0; i < 48; i += 3) {
            cs1[i / 3 * 2] = md5.charAt(i);
            cs1[i / 3 * 2 + 1] = md5.charAt(i + 2);
            cs2[i / 3] = md5.charAt(i + 1);
        }
        String salt = new String(cs2);
        return md5hex(code + salt).equals(new String(cs1));
    }
}
