package cn.lethekk.chapter6.dao;

import cn.lethekk.chapter6.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    User getUser(Long id);

    int insertUser(User user);
}
