package com.crowdfunding.interceptor;

import com.crowdfunding.bean.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 对登录方法的拦截器
 * @author wang_sir
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //设置拦截的白名单
        Set<String> set = new HashSet<String>();
        set.add("/login.html");
        set.add("/reg.html");
        set.add("/dologin/login.do");
        set.add("/register/reg.do");
        set.add("/logout.do.do");
        set.add("/index_1.html");
        String servletPath = request.getServletPath();
        if (set.contains(servletPath)) {
            return true;
        }
        //判断用户是否存在
        HttpSession session = request.getSession();
        List<User> users = (List<User>) session.getAttribute("user");
        if (users!=null){
            return true;
        }else {
            response.sendRedirect(request.getContextPath()+"/index_1.html");
            return false;
        }
    }
}
