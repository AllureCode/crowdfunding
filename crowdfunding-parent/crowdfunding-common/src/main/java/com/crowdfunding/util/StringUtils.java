package com.crowdfunding.util;

/**
 *
 * 判断字符串是否非空工具类
 */
public class StringUtils {
    public static boolean isEmpty(String str){
            return str==null ||"".equals(str);
    }
    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }
}
