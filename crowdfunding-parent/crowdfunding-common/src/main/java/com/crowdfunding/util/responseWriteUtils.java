package com.crowdfunding.util;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 写入response的工具类
 */
public class responseWriteUtils {
    public static void Write(HttpServletResponse response,Object o)throws Exception{
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        writer.println(o.toString());
        writer.flush();
        writer.close();
    }
}
