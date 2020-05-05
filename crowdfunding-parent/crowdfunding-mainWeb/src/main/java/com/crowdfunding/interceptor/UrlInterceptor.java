package com.crowdfunding.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * 用户登录后的路径拦截器
 * @author wang_sir
 */
public class UrlInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       //获取到数据库中许可的所有路径
//        List<Permission> permissions = permissionService.queryAll();
////        //存取所有的访问路径
////        Set<String> allUrl = new HashSet<String>();
////        for (Permission permission:permissions){
////            allUrl.add("/"+permission.getUrl());
////        }
        Set<String> allUrl = (Set<String>) request.getSession().getServletContext().getAttribute("allUrl");
        //判断请求路径是否在所有的访问路径中 如果在进行拦截处理 不再直接放行
        String servletPath = request.getServletPath();
        if (allUrl.contains(servletPath)){
            //获取到当前登录用户所拥有的权限路径
            Set<String> myUrl = (Set<String>) request.getSession().getAttribute("myUrl");
            if (myUrl.contains(servletPath)){
                return true;
            }else {
                response.sendRedirect(request.getContextPath()+"/error.jsp");
                return false;
            }
        }else {
            return  true;
        }
    }
}
