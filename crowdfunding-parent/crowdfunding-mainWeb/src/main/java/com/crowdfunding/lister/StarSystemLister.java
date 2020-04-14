package com.crowdfunding.lister;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 自定义监听器：StarSystemLister  简化获取上下文路径的jsp页面写法
 * 当项目启动时将获取到的上下文路径放入application域
 */
public class StarSystemLister implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext applicationContext = sce.getServletContext();
        String contextPath = applicationContext.getContextPath();
        applicationContext.setAttribute("APP_PATH", contextPath);
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
