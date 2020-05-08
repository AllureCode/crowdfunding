package com.crowdfunding.controller;

import com.crowdfunding.bean.Member;
import com.crowdfunding.bean.Permission;
import com.crowdfunding.bean.User;
import com.crowdfunding.manager.Service.IMemberService;
import com.crowdfunding.manager.Service.IUserService;
import com.crowdfunding.util.MD5Utils;
import com.crowdfunding.util.responseWriteUtils;
import net.sf.json.JSONObject;
import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/dologin")
public class doLoginController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IMemberService iMemberService;

    /**
     * 对登录方法的控制
     */
    @RequestMapping("/login")
    public String doLoginl(@RequestParam(value = "loginacct") String loginacct,
                           @RequestParam(value = "userpswd") String userpswd,
                           @RequestParam(value = "IDtype") String IDtype,
                           HttpServletResponse response,
                           HttpSession session
    ) throws Exception {
        Map<String, Object> map = new HashMap<>();
        JSONObject jsonObject = new JSONObject();
        map.put("loginacct", loginacct);
        map.put("userpswd", MD5Utils.MD5(userpswd, "admin"));
        if (IDtype.equals("user")) {
            //管理员登录
            User users = iUserService.queryUserByParameter(map);
            session.setAttribute("user", users);
            //-对拦截器控制的代码(获取到当前用户的所有路径)----------
            List<Permission> myPermission = iUserService.queryPermissionByUserId(users.getId());
            Set<String> set = new HashSet<String>();
            Permission permissionRoot = null;
            for (Permission permission : myPermission) {
                //获取到用户的所有路径权限
                set.add("/" + permission.getUrl());
                //判断谁的pid为null 找到顶级菜单
                if (permission.getPid() == null) {
                    //放入根节点
                    permissionRoot = permission;
                } else {
                    //仍为父节点 继续遍历
                    for (Permission innerChilder : myPermission) {
                        if (permission.getPid().equals(innerChilder.getId())) {
                            innerChilder.getChildren().add(permission);
                            break;
                        }
                    }
                }
            }
            session.setAttribute("myUrl", set);
            session.setAttribute("permissionRoot", permissionRoot);
            //---对拦截器控制的结束代码---------
            if (users == null) {
                jsonObject.put("success", Boolean.FALSE);
                responseWriteUtils.Write(response, jsonObject);
            } else {
                jsonObject.put("success", Boolean.TRUE);
                responseWriteUtils.Write(response, jsonObject);
            }
        }
        if (IDtype.equals("member")) {
            //会员登录
            List<Member> members = iMemberService.queryMemberByParameter(map);
            session.setAttribute("member", members);
            if (members.isEmpty()) {
                jsonObject.put("success", Boolean.FALSE);
                responseWriteUtils.Write(response, jsonObject);
            } else {
                jsonObject.put("success", Boolean.TRUE);
                responseWriteUtils.Write(response, jsonObject);
            }
        }
        return null;
    }
}
