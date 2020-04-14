package com.crowdfunding.manager.Service.Impl;

import com.crowdfunding.bean.Member;
import com.crowdfunding.bean.Page;
import com.crowdfunding.manager.Service.IMemberService;
import com.crowdfunding.manager.dao.IMemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MemberServiceImpl implements IMemberService {

    @Autowired
    private IMemberDao iMemberDao;

    @Override
    public List<Member> queryMemberByParameter(Map<String, Object> parameter) {
        return iMemberDao. queryMember(parameter);
    }

    @Override
    public boolean insertMemberByParameter(Map<String, Object> parameter) {
        return iMemberDao.insertMemberByParameter(parameter);
    }

//    @Override
//    public Page<Member> queryPage(Map<String, Object> parameter) {
//        Page page = new Page((Integer)parameter.get("pageNo"),
//                (Integer)parameter.get("pageSize"));
//        //根据条件查询总数
//        Integer count = iMemberDao.queryCount(parameter);
//        //查询分页的会员用户
//        parameter.put("startIndex",page.getStartIndex());
//        List<Member> members = iMemberDao.queryMember(parameter);
//        page.setDate(members);
//        page.setTotalSize(count);
//        return page;
//    }

}
