package com.crowdfunding.manager.Controller;

import com.crowdfunding.bean.Page;
import com.crowdfunding.bean.Permission;
import com.crowdfunding.bean.Role;
import com.crowdfunding.manager.Service.IPermissionService;
import com.crowdfunding.manager.Service.IRoleService;
import com.crowdfunding.util.StringUtils;
import com.crowdfunding.util.responseWriteUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService iRoleService;
    @Autowired
    private IPermissionService iPermissionService;

    @RequestMapping("/toRole")
    public String toRole(){
        return "/authenticationPage_role/role";
    }
    @RequestMapping("/toAssignPermission")
    public String toAssignPermission(){
        return "/authenticationPage_role/assignPermission";
    }

    /***
     * 查询所有的角色
     * @param pageNo
     * @param pageSize
     * @param queryText
     * @param response
     * @return
     * @throws Exception
     */
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

    /**
     * 采用异步加载方式 展示许可树
     * @return
     */
    @ResponseBody
    @RequestMapping("/loadDataAsyn")
    public Object loadPermissionData(@RequestParam("roleid") Integer roleid)throws Exception{
        List<Permission> root  = new ArrayList<>();
        List<Permission> permissions = iPermissionService.queryAll();
        //根据roleid查询对应的角色已经分配的许可
        List<Integer> rolePermissionedByRoleId = iPermissionService.queryRolePermissionedByRoleId(roleid);
        for (Permission permission:permissions){
            //如果角色id在permission中出现 则将check设置为true
            if (rolePermissionedByRoleId.contains(permission.getId())){
                permission.setChecked(true);
            }
            if (permission.getPid()==null){
                root.add(permission);
            }else {
                for (Permission innerChilder : permissions) {
                    if (permission.getPid().equals(innerChilder.getId())) {
                        innerChilder.getChildren().add(permission);
                        break;
                    }
                }
            }
        }
        System.out.println(root);
        return root;
    }

    /**
     * 分配权限（添加数据到数据库）
     * @param roleid
     * @param idsArray
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/insertPermission")
    public String insertPermission(@RequestParam(value = "roleid",required = false)Integer roleid,
                                   @RequestParam(value = "ids[]",required = false)Integer[] ids,
                                   HttpServletResponse response)throws Exception{
        System.out.println("**********-----------------");
        System.out.println(Arrays.toString(ids));
       boolean flag =  iRoleService.saveRolePermission(roleid,ids);
       JSONObject jsonObject  = new JSONObject();
       jsonObject.put("success", Boolean.valueOf(flag));
       responseWriteUtils.Write(response, jsonObject);
        return  null;
    }
}
