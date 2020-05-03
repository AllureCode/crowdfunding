package com.crowdfunding.manager.Service;

import com.crowdfunding.bean.Page;
import com.crowdfunding.bean.Role;

import java.util.Map;

public interface IRoleService {
    Page<Role> queryRolePage(Map<String, Object> map);

    boolean saveRolePermission(Integer roleid, Integer[] idsArray);
}
