package com.crowdfunding.controller;

import com.crowdfunding.bean.Permission;
import com.crowdfunding.bean.User;
import com.crowdfunding.manager.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class DispatcherController {

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
        return "/authenticationPage_user/main";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); //销毁session对象
        return "redirect:/index_1.html";
    }
}
