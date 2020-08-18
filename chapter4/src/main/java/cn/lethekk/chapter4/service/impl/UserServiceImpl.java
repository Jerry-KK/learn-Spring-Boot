package cn.lethekk.chapter4.service.impl;

import cn.lethekk.chapter4.service.UserService;
import cn.lethekk.chapter4.pojo.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public void printUser(User user) {
        if(user == null){
            throw new RuntimeException("检查用户参数是否为空");
        }
        System.out.println("id = " + user.getUserId());
        System.out.println("name = " + user.getUserName());
        System.out.println("note = " + user.getNote());
    }
}
