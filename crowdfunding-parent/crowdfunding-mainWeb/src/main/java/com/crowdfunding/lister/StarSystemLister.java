package com.crowdfunding.lister;

import com.crowdfunding.bean.Permission;
import com.crowdfunding.manager.Service.IPermissionService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

        //为了减少每次访问路径拦截时对数据库的请求次数 在容器已启动就加载数据库然后将数据存入application域中
        ApplicationContext ioc = WebApplicationContextUtils.getWebApplicationContext(applicationContext);
        IPermissionService permissionService = ioc.getBean(IPermissionService.class);
        //获取到数据库中许可的所有路径
        List<Permission> permissions = permissionService.queryAll();
        //存取所有的访问路径
        Set<String> allUrl = new HashSet<String>();
        for (Permission permission:permissions){
            allUrl.add("/"+permission.getUrl());
        }
        applicationContext.setAttribute("allUrl",allUrl);
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
