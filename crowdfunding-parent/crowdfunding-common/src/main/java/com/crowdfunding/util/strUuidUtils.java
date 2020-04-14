package com.crowdfunding.util;

import java.util.UUID;

/**
 * 使用uuid生成随意字符串作为加密MD5的盐值
 */
public class strUuidUtils {
    public static String strTo(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {
        System.out.println(strTo());
    }
}
