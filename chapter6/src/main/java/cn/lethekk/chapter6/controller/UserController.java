package cn.lethekk.chapter6.controller;

import cn.lethekk.chapter6.pojo.User;
import cn.lethekk.chapter6.service.UserBatchService;
import cn.lethekk.chapter6.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService = null;

    @Autowired
    private UserBatchService userBatchService = null;

    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser(Long id){
        return userService.getUser(id);
    }

    @RequestMapping("/insertUser")
    @ResponseBody
    public Map<String,Object> insertUser(String userName,String note){
        User user = new User();
        user.setUserName(userName);
        user.setNote(note);
        int updater = userService.insertUser(user);
        HashMap<String, Object> result = new HashMap<>();
        result.put("success",updater == 1);
        result.put("user",user);
        return result;
    }


    @ResponseBody
    @RequestMapping("/insertUsers")
    public Map<String,Object> insertUsers(String userName1,String note1,String userName2,String note2){
        User user1 = new User();
        user1.setUserName(userName1);
        user1.setNote(note1);
        User user2 = new User();
        user2.setUserName(userName2);
        user2.setNote(note2);
        ArrayList<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        int insert = userBatchService.insertUser(userList);
        Map<String,Object> result = new HashMap<>();
        result.put("success",insert>0);
        result.put("userList",userList);
        return result;
    }
}
