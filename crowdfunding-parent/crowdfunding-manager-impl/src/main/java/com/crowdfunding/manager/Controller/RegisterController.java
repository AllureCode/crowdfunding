package com.crowdfunding.manager.Controller;

import com.crowdfunding.manager.Service.IMemberService;
import com.crowdfunding.manager.dao.IMemberDao;
import com.crowdfunding.util.MD5Utils;
import com.crowdfunding.util.responseWriteUtils;
import jdk.nashorn.internal.runtime.UnwarrantedOptimismException;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 注册功能的控制
 */

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private IMemberService iMemberService;
    @RequestMapping("/reg")
    public String register(@RequestParam(value = "loginacct")String loginacct,
                           @RequestParam(value = "userpswd")String userpswd,
                           @RequestParam(value = "email")String email,
                           @RequestParam(value = "usertype")String usertype ,
                           @RequestParam(value = "username")String username ,
                           HttpServletResponse response)throws Exception{

        /**
         *对usertype进行处理 0个人 1企业
         */
        if(usertype=="个人"){
            usertype="0";
        }else {
            usertype="1";
        }
        Map<String,Object> map = new HashMap<>();
        JSONObject jsonObject = new JSONObject();
        map.put("loginacct",loginacct);
        map.put("userpswd", MD5Utils.MD5(userpswd, "admin"));
        map.put("email",email);
        map.put("usertype",usertype);
        map.put("username", username);
        boolean flag = iMemberService.insertMemberByParameter(map);
        if(flag){
            jsonObject.put("success", Boolean.TRUE);
        }else {
            jsonObject.put("success",Boolean.FALSE);
        }
        responseWriteUtils.Write(response, jsonObject);
        return  null;
    }
}
