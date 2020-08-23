package cn.lethekk.chapter5.dao;


import cn.lethekk.chapter5.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface MybatisUserDao {
    public User getUser(Long id);
}
