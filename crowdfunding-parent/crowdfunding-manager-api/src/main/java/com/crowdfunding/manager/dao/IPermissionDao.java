package com.crowdfunding.manager.dao;

import com.crowdfunding.bean.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPermissionDao {
    Permission getRootPermission();

    List<Permission> getChildrenPermissionBypid(Integer id);


    List<Permission> queryAll();

    boolean savaPermission(Permission permission);

    Permission getPermissionByID(Integer id);

    boolean updatePermission(Permission permission);

    boolean deletePermission(Integer id);

    List<Integer> queryRolePermissionedByRoleId(Integer roleid);
}
