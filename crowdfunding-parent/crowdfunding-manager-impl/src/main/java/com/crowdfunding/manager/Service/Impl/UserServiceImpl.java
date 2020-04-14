package com.crowdfunding.manager.Service.Impl;

import com.crowdfunding.bean.Page;
import com.crowdfunding.bean.Role;
import com.crowdfunding.bean.User;
import com.crowdfunding.manager.Service.IUserService;

import com.crowdfunding.manager.dao.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {
@Autowired
private IUserDao iUserDao;
    @Override
    public List<User> queryAllUser() {
        return iUserDao.queryAllUser();
    }

    @Override
    public List<User> queryUserByParameter(Map<String, Object> parameter) {
        return iUserDao.queryUserByParameter(parameter);
    }

    @Override
    public boolean insertUserByParameter(Map<String, Object> parameter) {
        return iUserDao.insertUserByParameter(parameter);
    }

    @Override
    public Integer queryCount() {
        return iUserDao.queryCount();
    }

//    @Override
//    public List<User> queryUser(Integer startIndex, Integer pageSize) {
//        return iUserDao.queryUser(startIndex,pageSize);
//    }

    @Override
    public Integer queryCount(Map<String, Object> parameter) {
        return iUserDao.queryCount(parameter);
    }

    @Override
    public List<User> queryUser(Map<String, Object> parameter) {
        return iUserDao.queryUser(parameter);
    }

    @Override
    public Page<User> queryPage(Map<String, Object> parameter) {
        Page<User> page = new Page<>((Integer)parameter.get("pageNo"),(Integer)parameter.get("pageSize"));

        //查询总记录数
        Integer total = iUserDao.queryCount(parameter);
        page.setTotalSize(total);
        //查询信息
        parameter.put("startIndex", page.getStartIndex());
        List<User> users = iUserDao.queryUser(parameter);
        page.setDate(users);

        return page;
    }

    @Override
    public boolean insertMemberByParameter(Map<String, Object> parameter) {
        return iUserDao.insertUserByParameter(parameter);
    }

    @Override
    public User queryById(Integer id) {
        return iUserDao.queryById(id);
    }

    @Override
    public Integer updateUser(User user) {
        return iUserDao.updateUser(user);
    }

    @Override
    public Integer deleteUser(Integer parseInt) {
        return iUserDao.deteteUser(parseInt);
    }

    @Override
    public List<Role> queryAllRole() {

        return iUserDao.queryAllRole();
    }

    @Override
    public List<Integer> queryRoleById(int parseInt) {
        return iUserDao.queryRoleById(parseInt);
    }

    @Override
    public boolean saveRolePower(Integer parseInt, Integer[] parseInt1) {
        return iUserDao.saveRolePower(parseInt,parseInt1);
    }

    @Override
    public boolean deleteRolePower(Integer id, Integer[] idsArray) {
        return iUserDao.deleteRolePower(id,idsArray);
    }
}
