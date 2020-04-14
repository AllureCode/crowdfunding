package com.crowdfunding.util;


import org.apache.shiro.crypto.hash.Md5Hash;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * MD5言之加密工具类
 */
public class MD5Utils {
    public static String MD5(String source,Object salt){

        return new Md5Hash(source,salt).toString();
    }
    /**
     * 测试盐值加密
     * @param args
     */
    public static void main(String[] args) {

        System.out.println(MD5Utils.MD5("000000", strUuidUtils.strTo()));
        SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(simpleFormatter.format(new Date()));
    }
}
