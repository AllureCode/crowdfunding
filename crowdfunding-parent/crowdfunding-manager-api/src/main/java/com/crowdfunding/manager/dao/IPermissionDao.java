package com.crowdfunding.manager.dao;

import com.crowdfunding.bean.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPermissionDao {
    Permission getRootPermission();

    List<Permission> getChildrenPermissionBypid(Integer id);


    List<Permission> queryAll();
}
