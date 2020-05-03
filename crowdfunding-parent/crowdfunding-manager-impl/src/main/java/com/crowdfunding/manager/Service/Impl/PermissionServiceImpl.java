package com.crowdfunding.manager.Service.Impl;

import com.crowdfunding.bean.Permission;
import com.crowdfunding.manager.Service.IPermissionService;
import com.crowdfunding.manager.dao.IPermissionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private IPermissionDao iPermissionDao;
    @Override
    public Permission getRootPermission() {
        return iPermissionDao.getRootPermission();
    }

    @Override
    public List<Permission> getChildrenPermissionBypid(Integer id) {
        return iPermissionDao.getChildrenPermissionBypid(id);
    }

    @Override
    public List<Permission> queryAll() {
        return iPermissionDao.queryAll();
    }

    @Override
    public boolean savaPermission(Permission permission) {
        return iPermissionDao.savaPermission(permission);
    }

    @Override
    public Permission getPermissionByID(Integer id) {
        return iPermissionDao.getPermissionByID(id);
    }

    @Override
    public boolean updatePermission(Permission permission) {
        return iPermissionDao.updatePermission(permission);
    }

    @Override
    public boolean deletePermission(Integer id) {
        return iPermissionDao.deletePermission(id);
    }

    @Override
    public List<Integer> queryRolePermissionedByRoleId(Integer roleid) {
        return iPermissionDao.queryRolePermissionedByRoleId(roleid);
    }
}
