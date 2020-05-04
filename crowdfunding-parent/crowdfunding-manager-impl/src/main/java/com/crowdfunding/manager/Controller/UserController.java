package com.crowdfunding.manager.Controller;

import com.crowdfunding.bean.Page;
import com.crowdfunding.bean.Role;
import com.crowdfunding.bean.User;
import com.crowdfunding.manager.Service.IUserService;
import com.crowdfunding.util.MD5Utils;
import com.crowdfunding.util.StringUtils;
import com.crowdfunding.util.responseWriteUtils;
import com.crowdfunding.util.strUuidUtils;
import jdk.nashorn.internal.runtime.OptimisticBuiltins;
import net.sf.json.JSONObject;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;


    /**
     * 用于跳转页面
     *
     * @return
     */
    @RequestMapping("/toAdd")
    public String add() {
        return "/authenticationPage_user/add";
    }

    @RequestMapping("/toUser")
    public String toMember() {

        return "/authenticationPage_user/user";
    }

    @RequestMapping("/toAssignRole")
    public String assignRole(@RequestParam(value = "id",required = false) String id,
                             Model model,
                             HttpServletResponse response) throws Exception {

        //查询所有的角色权限
        List<Role> roles = iUserService.queryAllRole();
        //根据当前id查询已分配权限用户
        List<Integer> roleid = iUserService.queryRoleById(Integer.parseInt(id));
        List<Role> leftRole = new ArrayList<>();//未分配
        List<Role> rightRole = new ArrayList<>();//已分配
        for (Role role : roles) {
            if (roleid.contains(role.getId())) {
                rightRole.add(role);
            } else {
                leftRole.add(role);
            }
        }
        model.addAttribute("leftRole",leftRole);
        model.addAttribute("rightRole",rightRole);
        return "/authenticationPage_user/assignRole";
    }

    @RequestMapping("/toEdit")
    public String edit(@RequestParam(value = "id") String id, Model model) {
        User user = iUserService.queryById(Integer.parseInt(id));
        model.addAttribute("user", user);
        return "/authenticationPage_user/edit";
    }

    /************************************************************************/

    // @ResponseBody  //异步请求所需的注解
    @RequestMapping("/searchUser")
    public Object searchMember(@RequestParam(value = "pageNo") String pageNo,
                               @RequestParam(value = "pageSize") String pageSize,
                               @RequestParam(required = false, value = "queryText") String queryText,
                               HttpServletResponse response) throws Exception {
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("pageNo", Integer.parseInt(pageNo));
        parameter.put("pageSize", Integer.parseInt(pageSize));
        if (StringUtils.isNotEmpty(queryText)) {
            parameter.put("queryText", queryText);
        }
        Page<User> userPage = iUserService.queryPage(parameter);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", Boolean.TRUE);
        jsonObject.put("userPage", userPage);
        responseWriteUtils.Write(response, jsonObject);
        System.out.println(jsonObject);
//        System.out.println(jsonObject);
//        AjaxResult result = new AjaxResult();
//        result.setSuccess(true);
//        result.setPage(memberPage);
//        System.out.println(result);
//        return  result;
        return null;
    }

    /**
     * 添加用户信息
     *
     * @return
     */
    @RequestMapping("/addUser")
    public String addMember(@RequestParam(value = "loginacct") String loginacct,
                            @RequestParam(value = "userpswd") String userpswd,
                            @RequestParam(value = "username") String username,
                            @RequestParam(value = "email") String email,
                            HttpServletResponse response) throws Exception {
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("loginacct", loginacct);
        parameter.put("userpswd", MD5Utils.MD5(userpswd, "admin"));
        parameter.put("username", username);
        parameter.put("email", email);
        boolean falg = iUserService.insertMemberByParameter(parameter);
        JSONObject result = new JSONObject();
        result.put("success", Boolean.valueOf(falg));
        responseWriteUtils.Write(response, result);
        return null;
    }

    /**
     * 修改用户信息
     */
    @RequestMapping("/alterUser")
    public String alterUser(User user, HttpServletResponse response) throws Exception {
        JSONObject result = new JSONObject();
        user.setUserpswd(MD5Utils.MD5(user.getUserpswd(),"admin"));
        Integer integer = iUserService.updateUser(user);
        if (integer > 0) {
            result.put("success", Boolean.TRUE);
        } else {
            result.put("success", Boolean.FALSE);
        }
        responseWriteUtils.Write(response, result);
        return null;
    }

    /**
     * 删除用户信息
     */
    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam(value = "id", required = false) String id,
                             @RequestParam(value = "ids", required = false) String ids,
                             HttpServletResponse response) throws Exception {
        Integer integer = 0;
        JSONObject result = new JSONObject();
        if ((Integer.parseInt(id)) == 0) {
            String[] idstr = ids.split(",");
            for (int i = 0; i < idstr.length; i++) {
                String trim = idstr[i].trim();
                integer = iUserService.deleteUser(Integer.parseInt(trim));
            }
        } else {
            integer = iUserService.deleteUser(Integer.parseInt(id));
        }
        if (integer > 0) {
            result.put("success", Boolean.TRUE);
        } else {
            result.put("success", Boolean.FALSE);
        }
        responseWriteUtils.Write(response, result);
        return null;
    }

    /**
     * 保存用户分配的权限
     * @param id
     * @param
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/saveRolePower")
    public String saveRolePower(@RequestParam(value = "id",required = false)Integer id,
                                @RequestParam(value = "idsArray[]",required = false)Integer[] idsArray,
                                HttpServletResponse response)throws Exception{
        boolean flag=false;
       flag=iUserService.saveRolePower(id, idsArray);
//        Integer idss=Integer.parseInt(id);
//        for (String s:split) {
//            System.out.println("**************");
//            System.out.println(s);
//        }
//
////        for(int i=0;i<split.length;i++) {
////          flag = iUserService.saveRolePower(Integer.parseInt(id),Integer.parseInt(split[i]));
////        }
//        System.out.println(id);
        JSONObject result= new JSONObject();
        result.put("success", Boolean.valueOf(flag));
        responseWriteUtils.Write(response,result);
        return  null;
    }

    @RequestMapping("/deleteRolePower")
    public String deleteRolePower(@RequestParam(value = "id",required = false)Integer id,
                                  @RequestParam(value = "idsArray[]",required = false)Integer[] idsArray,
                                  HttpServletResponse response)throws Exception{
        boolean flag=false;
        flag=iUserService.deleteRolePower(id, idsArray);
        JSONObject result= new JSONObject();
        result.put("success", Boolean.valueOf(flag));
        responseWriteUtils.Write(response,result);
        return null;
    }
}
