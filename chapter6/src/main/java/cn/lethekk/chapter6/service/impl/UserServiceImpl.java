package cn.lethekk.chapter6.service.impl;

import cn.lethekk.chapter6.dao.UserDao;
import cn.lethekk.chapter6.pojo.User;
import cn.lethekk.chapter6.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao = null;

    @Override
    @Transactional(isolation =  Isolation.READ_COMMITTED,timeout = 1)
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    @Override
    //@Transactional(isolation =  Isolation.SERIALIZABLE,timeout = 1,propagation = Propagation.REQUIRES_NEW)
    @Transactional(isolation =  Isolation.READ_COMMITTED,propagation = Propagation.NESTED)
    public int insertUser(User user) {
        return userDao.insertUser(user);
    }
}
