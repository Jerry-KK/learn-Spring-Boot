package cn.lethekk.chapter5.controller;

import cn.lethekk.chapter5.dao.JpaUserRepository;
import cn.lethekk.chapter5.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/jpa")
public class JapController {

    @Autowired
    private JpaUserRepository jpaUserRepository = null;

    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser(Long id){
        //使用JPA接口查询对象
        User user = jpaUserRepository.findById(id).get();
        return user;
    }


}
