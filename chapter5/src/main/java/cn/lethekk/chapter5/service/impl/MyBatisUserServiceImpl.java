package cn.lethekk.chapter5.service.impl;

import cn.lethekk.chapter5.dao.MybatisUserDao;
import cn.lethekk.chapter5.pojo.User;
import cn.lethekk.chapter5.service.MyBatisUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyBatisUserServiceImpl implements MyBatisUserService {

    @Autowired
    private MybatisUserDao mybatisUserDao = null;

    @Override
    public User getUser(Long id) {
        return mybatisUserDao.getUser(id);
    }
}
