package com.crowdfunding.manager.dao;

import com.crowdfunding.bean.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IRoleDao {
    Integer queryCount(Map<String, Object> map);

    List<Role> queryRoles(Map<String, Object> map);
}
