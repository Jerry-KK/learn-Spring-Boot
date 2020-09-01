package cn.lethekk.chapter7.service;

import cn.lethekk.chapter7.dao.UserDao;
import cn.lethekk.chapter7.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

   User getUser(Long id);

   User insertUser(User user);

   User updateUser(Long id,String userName);

   List<User> findUsers(String userName,String note);

   int deleteUser(Long id);
}
