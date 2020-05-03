package com.crowdfunding.manager.dao;

import com.crowdfunding.bean.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Repository
public interface IRoleDao {
    Integer queryCount(Map<String, Object> map);

    List<Role> queryRoles(Map<String, Object> map);

    boolean deleteRolePermisison(@Param("roleid") Integer roleid);

    boolean saveRolePermission(@Param("roleid") Integer roleid,@Param("idsArray") Integer[] idsArray);
}
