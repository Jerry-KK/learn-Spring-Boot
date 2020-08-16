package cn.lethekk.chapter3.service;

import cn.lethekk.chapter3.pojo.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public void printUser(User user){
        System.out.println(user.getId());
        System.out.println(user.getUserName());
        System.out.println(user.getNote());
    }
}
