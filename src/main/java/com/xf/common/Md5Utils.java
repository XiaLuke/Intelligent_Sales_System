package com.xf.common;

import org.apache.shiro.crypto.hash.SimpleHash;

public class Md5Utils {
    //设置一个盐值
    public static final String SALT = "itsource";
    //迭代次数
    private static final int HASHITERATIONS = 10;

    /**
     * 根据字母串创建一个密码
     */
    public static String createMd5Pwd(String pwd){
        SimpleHash hash = new SimpleHash("MD5",pwd,SALT,HASHITERATIONS);
        return hash.toHex();
    }

    public static void main(String[] args) {
        String aa = "admin";
        String md5Pwd = createMd5Pwd(aa);
        System.out.println(md5Pwd);
    }
}
