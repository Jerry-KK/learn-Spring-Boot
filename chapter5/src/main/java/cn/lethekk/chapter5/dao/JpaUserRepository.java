package cn.lethekk.chapter5.dao;

import cn.lethekk.chapter5.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<User,Long> {

}
