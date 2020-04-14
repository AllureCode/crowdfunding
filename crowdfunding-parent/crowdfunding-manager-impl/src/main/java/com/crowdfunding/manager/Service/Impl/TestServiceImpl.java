package com.crowdfunding.manager.Service.Impl;

import com.crowdfunding.manager.Service.TestService;
import com.crowdfunding.manager.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestDao testDao;
    @Override
    public void insert() {
        Map map = new HashMap();
        map.put("boyName", "zhang_san");
        testDao.insert(map);
    }
}
