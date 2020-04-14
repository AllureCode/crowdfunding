package com.crowdfunding.controller;

import com.crowdfunding.bean.Member;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String doLoginl(@RequestParam(value = "loginacct")String loginacct,
                           @RequestParam(value = "userpswd")String userpswd,
                           @RequestParam(value = "IDtype")String IDtype,
                           HttpServletResponse response,
                          HttpSession session
                            )throws Exception{
        Map<String ,Object> map = new HashMap<>();
        JSONObject jsonObject = new JSONObject();
        map.put("loginacct",loginacct);
        map.put("userpswd", MD5Utils.MD5(userpswd,"admin"));
        if(IDtype.equals("user")){
            //管理员登录
            List<User> users = iUserService.queryUserByParameter(map);
            session.setAttribute("user", users);
            if(users.isEmpty()){
                jsonObject.put("success", Boolean.FALSE);
                responseWriteUtils.Write(response,jsonObject);
            }else {
                jsonObject.put("success",Boolean.TRUE);
                responseWriteUtils.Write(response,jsonObject);
            }
        }
        if(IDtype.equals("member")){
            //会员登录
            List<Member> members = iMemberService.queryMemberByParameter(map);
            session.setAttribute("member", members);
            if (members.isEmpty()){
                jsonObject.put("success",Boolean.FALSE);
                responseWriteUtils.Write(response,jsonObject);
            }else {
                jsonObject.put("success",Boolean.TRUE);
                responseWriteUtils.Write(response,jsonObject);
            }
        }
        return null;
    }
}
