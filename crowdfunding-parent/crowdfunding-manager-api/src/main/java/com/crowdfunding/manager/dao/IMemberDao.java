package com.crowdfunding.manager.dao;

import com.crowdfunding.bean.Member;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface IMemberDao {
//    /**
//     * 查询所有的会员信息
//     * @return
//     */
//    List<Member> queryAllMember();
//    /**
//     *根据传入的参数查询用户
//     * @return
//     */
//    List<Member> queryMemberByParameter(Map<String,Object> parameter);

    boolean insertMemberByParameter(Map<String,Object> parameter);
//
//    Integer queryCount();

//
//    /**
//     * 当查询条件为两个简单类型时需要加@Param注解
//     * @param startIndex
//     * @param pageSize
//     * @return
//     */
//    List<Member>  queryMember(@Param(value = "startIndex") Integer startIndex,
//                              @Param(value = "pageSize") Integer pageSize);
//
//    /**
//     * 根据条件查询总数
//     * @param parameter
//     * @return
//     */
//    Integer queryCount(Map<String, Object> parameter);
//
    /**
     * 根据条件查询会员用户
     * @param parameter
     * @return
     */
    List<Member> queryMember(Map<String, Object> parameter);
}
