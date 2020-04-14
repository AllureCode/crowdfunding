package com.crowdfunding.manager.Controller.testController;

import com.crowdfunding.bean.User;
import com.crowdfunding.manager.Service.IUserService;
import com.crowdfunding.manager.Service.TestService;
import com.crowdfunding.manager.dao.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TestCon {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private TestService testService;
    @RequestMapping("/test")
    public String test(){
        System.out.println("方法执行了");
        testService.insert();
        return "success";
    }
    @RequestMapping("/findListUser")
    public String testFindUser(){
        //List<User> allUser = iUserService.findAllUser();
        System.out.println("////////////////////////////////");
       // System.out.println(allUser);
        return "success";
    }
}
