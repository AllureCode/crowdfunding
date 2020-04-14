package com.crowdfunding.manager.dao;
import com.crowdfunding.bean.Role;
import com.crowdfunding.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IUserDao {
    /**
     * 查询所有的会员信息
     * @return
     */
    List<User> queryAllUser();
    /**
     *根据传入的参数查询用户
     * @return
     */
    List<User> queryUserByParameter(Map<String,Object> parameter);

    boolean insertUserByParameter(Map<String,Object> parameter);

    Integer queryCount();


//    /**
//     * 当查询条件为两个简单类型时需要加@Param注解
//     * @param startIndex
//     * @param pageSize
//     * @return
//     */
//    List<User>  queryUser(@Param(value = "startIndex") Integer startIndex,
//                              @Param(value = "pageSize") Integer pageSize);

    /**
     * 根据条件查询总数
     * @param parameter
     * @return
     */
    Integer queryCount(Map<String, Object> parameter);

    /**
     * 根据条件查询会员用户
     * @param parameter
     * @return
     */
    List<User> queryUser(Map<String, Object> parameter);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User queryById(Integer id);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    Integer updateUser(User user);

    Integer deteteUser(Integer parseInt);
    /**
     * 查询角色
     */
    List<Role> queryAllRole();

    List<Integer> queryRoleById(int parseInt);

    /**
     * 保存用户权限
     * @param parseInt
     * @param parseInt1
     * @return
     */
    boolean saveRolePower(@Param("parseInt") Integer parseInt, @Param("parseInt1") Integer[] parseInt1);



    boolean deleteRolePower(@Param("parseInt") Integer parseInt, @Param("parseInt1") Integer[] parseInt1);
}
