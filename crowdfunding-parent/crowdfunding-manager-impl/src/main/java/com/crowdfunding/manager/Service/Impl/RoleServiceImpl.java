package com.crowdfunding.manager.Service.Impl;

import com.crowdfunding.bean.Page;
import com.crowdfunding.bean.Role;
import com.crowdfunding.manager.Service.IRoleService;
import com.crowdfunding.manager.dao.IRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleDao iRoleDao;
    @Override
    public Page<Role> queryRolePage(Map<String, Object> map) {
        Page<Role> page = new Page<>((Integer)map.get("pageNo"), (Integer)map.get("pageSize"));
        //查询总条数
        Integer countRoles = iRoleDao.queryCount(map);
        page.setTotalSize(countRoles);
        //查询所有角色信息
        map.put("startIndex", page.getStartIndex());
        List<Role> list = iRoleDao.queryRoles(map);
        page.setDate(list);
        return page;
    }
}
