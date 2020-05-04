package com.crowdfunding.controller;

import com.crowdfunding.bean.Permission;
import com.crowdfunding.bean.User;
import com.crowdfunding.manager.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DispatcherController {
    @Autowired
    private IUserService userService;

    @RequestMapping("/index_1")
    public String index() {
        return "index_1";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/reg")
    public String reg() {
        return "reg";
    }

    @RequestMapping("/main")
    public String main(HttpSession session) {
        List<User> user = (List<User>) session.getAttribute("user");
        User user1 = user.get(0);
        List<Permission> myPermission = userService.queryPermissionByUserId(user1.getId());
        Permission permissionRoot = null;
        for (Permission permission : myPermission) {
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
        session.setAttribute("permissionRoot", permissionRoot);
        System.out.println(permissionRoot);
        return "/authenticationPage_user/main";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); //销毁session对象
        return "redirect:/index_1.html";
    }
}
