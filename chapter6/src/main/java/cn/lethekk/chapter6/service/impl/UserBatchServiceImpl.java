package cn.lethekk.chapter6.service.impl;

import cn.lethekk.chapter6.pojo.User;
import cn.lethekk.chapter6.service.UserBatchService;
import cn.lethekk.chapter6.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserBatchServiceImpl implements UserBatchService {

    @Autowired
    private UserService userService;


    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public int insertUser(List<User> userList) {
        int count = 0;
        for (User user: userList) {
            count+=userService.insertUser(user);
        }
        return count;
    }
}
