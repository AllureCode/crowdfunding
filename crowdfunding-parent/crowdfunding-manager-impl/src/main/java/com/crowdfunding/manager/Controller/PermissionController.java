package com.crowdfunding.manager.Controller;

import com.crowdfunding.bean.Permission;
import com.crowdfunding.manager.Service.IPermissionService;
import com.crowdfunding.util.responseWriteUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private IPermissionService iPermissionService;

    @RequestMapping("/toPermissionPage")
    public String toPermissionPage() {
        return "/authenticationPage_permission/permission";
    }

    @RequestMapping("/toAdd")
    public String toAddPermissionPage(){
        return "/authenticationPage_permission/addPermission";
    }
    @RequestMapping("/toEdit")
    public String toEditPermissionPage(@RequestParam("id") Integer id, Model model){
        //回显当前用户信息
       Permission permission =  iPermissionService.getPermissionByID(id);
       model.addAttribute("permission", permission);
        return "/authenticationPage_permission/editPermission";
    }

    @RequestMapping("/loadData")
//    方法一
//    public String loadPermissionData(HttpServletResponse response)throws Exception{
//        List<Permission> root = new ArrayList<Permission>();
//        //父
//        Permission permission = iPermissionService.getRootPermission();
//        root.add(permission);
//        //子
//        List<Permission> children = iPermissionService.getChildrenPermissionBypid(permission.getId());
//        //设置父子关系
//        permission.setChildren(children);
//        for(Permission child:children){
//            List<Permission> innerChildren = iPermissionService.getChildrenPermissionBypid(child.getId());
//            child.setChildren(innerChildren);
//        }
//        JSONObject result = new JSONObject();
//        result.put("success", Boolean.TRUE);
//        result.put("root", root);
//        responseWriteUtils.Write(response, result);
//        return null;
//    }
    //方法二 采用递归（会多次请求数据库 容易给数据库造成超负荷）
//    public String loadPermissionData(HttpServletResponse response)throws Exception{
//        List<Permission> root = new ArrayList<Permission>();
//        //父
//        Permission permission = iPermissionService.getRootPermission();
//        root.add(permission);
//        queryPermissionData(permission);
//        JSONObject result = new JSONObject();
//        result.put("success", Boolean.TRUE);
//        result.put("root", root);
//        responseWriteUtils.Write(response, result);
//        return null;
//    }
//    private void queryPermissionData(Permission permission){
//        List<Permission> children = iPermissionService.getChildrenPermissionBypid(permission.getId());
//        //组合父子关系
//        permission.setChildren(children);
//
//        for (Permission child:children){
//            child.setOpen(true);
//            queryPermissionData(child);
//        }
//    }
    //方法三 采用一次性查询数据库 然后遍历集合
    public String loadPermissionData(HttpServletResponse response) throws Exception {
        List<Permission> root = new ArrayList<Permission>(); //创建父集合
        //查询到所有的permission
        List<Permission> permissions = iPermissionService.queryAll();
        for (Permission permission : permissions) {
            //判断谁的pid为null 找到顶级菜单
            if (permission.getPid() == null) {
                root.add(permission);//放入根节点
            } else {
                //仍为父节点 继续遍历
                for (Permission innerChilder : permissions) {
                    if (permission.getPid() == innerChilder.getId()) {
                        innerChilder.getChildren().add(permission);
                        break;
                    }
                }
            }
        }
        JSONObject result = new JSONObject();
        result.put("success", Boolean.TRUE);
        result.put("root", root);
        responseWriteUtils.Write(response, result);
        return null;
    }
    //对方法三进行优化 利用map
//        public String loadPermissionData(HttpServletResponse response) throws Exception {
//        List<Permission> root = new ArrayList<Permission>(); //创建父集合
//        //查询到所有的permission
//        List<Permission> permissions = iPermissionService.queryAll();
//        Map<Integer,Permission> map = new HashMap<Integer, Permission>();
//        for (Permission innerChild:permissions){
//            map.put(innerChild.getId(),innerChild);
//        }
//        for (Permission permission : permissions) {
//            //判断谁的pid为null 找到顶级菜单
//            Permission child = permission;
//            if (child.getPid() == 0) {
//                root.add(permission);//放入根节点
//            } else {
//                Permission parents = map.get(child.getPid());
//
//                parents.getChildren().add(permission);
//            }
//        }
//        JSONObject result = new JSONObject();
//        result.put("success", Boolean.TRUE);
//        result.put("root", root);
//        responseWriteUtils.Write(response, result);
//        return null;
//    }

    @RequestMapping("/addPermission")
    public String addPermission(/*@RequestParam("name")String name,
                                @RequestParam("icon")String icon,
                                @RequestParam("url") String url,
                                @RequestParam("pid")String pid*/
                                Permission permission,
                                HttpServletResponse response) throws Exception {
        JSONObject jsonObject = new JSONObject();
        boolean flag = iPermissionService.savaPermission(permission);
        if(flag){
            jsonObject.put("success",Boolean.TRUE);
        }else {
            jsonObject.put("success", Boolean.FALSE);
        }
        responseWriteUtils.Write(response, jsonObject);
        return null;
    }
    @RequestMapping("/UpdatePermission")
    public String UpdatePermission(/*@RequestParam("name")String name,
                                @RequestParam("icon")String icon,
                                @RequestParam("url") String url,
                                @RequestParam("pid")String pid*/
            Permission permission,
            HttpServletResponse response) throws Exception {
        JSONObject jsonObject = new JSONObject();
        boolean flag = iPermissionService.updatePermission(permission);
        if(flag){
            jsonObject.put("success",Boolean.TRUE);
        }else {
            jsonObject.put("success", Boolean.FALSE);
        }
        responseWriteUtils.Write(response, jsonObject);
        return null;
    }

}
