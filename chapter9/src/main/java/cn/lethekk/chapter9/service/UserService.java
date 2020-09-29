package cn.lethekk.chapter9.service;

import cn.lethekk.chapter9.pojo.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public void insertUser(User user) {
    }

    public User getUser(Long id) {
        return new User();
    }
}
