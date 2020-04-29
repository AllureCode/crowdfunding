package com.crowdfunding.manager.Service;

import com.crowdfunding.bean.Permission;

import java.util.List;


public interface IPermissionService {
    Permission getRootPermission();

    List<Permission> getChildrenPermissionBypid(Integer id);

    List<Permission> queryAll();

    boolean savaPermission(Permission permission);
}
