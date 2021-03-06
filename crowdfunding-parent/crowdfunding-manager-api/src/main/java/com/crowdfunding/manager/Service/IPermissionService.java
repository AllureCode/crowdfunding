package com.crowdfunding.manager.Service;

import com.crowdfunding.bean.Permission;

import java.util.List;


public interface IPermissionService {
    Permission getRootPermission();

    List<Permission> getChildrenPermissionBypid(Integer id);

    List<Permission> queryAll();

    boolean savaPermission(Permission permission);

    Permission getPermissionByID(Integer id);
    
    boolean updatePermission(Permission permission);

    boolean deletePermission(Integer id);

    List<Integer> queryRolePermissionedByRoleId(Integer roleid);
}
