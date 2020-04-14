package com.crowdfunding.manager.Controller;

import com.crowdfunding.bean.Page;
import com.crowdfunding.bean.Role;
import com.crowdfunding.manager.Service.IRoleService;
import com.crowdfunding.manager.Service.IUserService;
import com.crowdfunding.util.StringUtils;
import com.crowdfunding.util.responseWriteUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService iRoleService;

    @RequestMapping("/toRole")
    public String toRole(){
        return "/authenticationPage_role/role";
    }


    @RequestMapping("/queryAllRoles")
    public String queryAllRoles(@RequestParam("pageNo") String pageNo,
                                @RequestParam("pageSize") String pageSize,
                                @RequestParam(value = "queryText",required = false)String queryText,
                                HttpServletResponse response )throws Exception{

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("pageNo", Integer.parseInt(pageNo));
        map.put("pageSize", Integer.parseInt(pageSize));
        if(StringUtils.isNotEmpty(queryText)){
            map.put("queryText", queryText);
        }
        Page<Role> rolePage =iRoleService.queryRolePage(map);
        System.out.println(rolePage);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", Boolean.TRUE);
        jsonObject.put("rolePage",rolePage);
        responseWriteUtils.Write(response, jsonObject);
        System.out.println(jsonObject);
        return null;
    }
}
