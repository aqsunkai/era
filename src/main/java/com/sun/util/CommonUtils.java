package com.sun.util;

import org.apache.shiro.crypto.hash.Sha384Hash;

/**
 * Created by Administrator on 2017/3/3.
 */
public class CommonUtils {

    //将传进来密码加密方法
    public static String encrypt(String data) {
        String sha384Hex = new Sha384Hash(data).toBase64();
        System.out.println(data + ":" + sha384Hex);
        return sha384Hex;
    }

    public static void main(String[] args) {
        String xx = CommonUtils.encrypt("zhuyuanqq1");
        System.out.println(xx);
    }
}
