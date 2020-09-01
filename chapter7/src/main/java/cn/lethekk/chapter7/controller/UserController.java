package cn.lethekk.chapter7.controller;

import cn.lethekk.chapter7.pojo.User;
import cn.lethekk.chapter7.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService = null;

    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser(Long id){
        return userService.getUser(id);
    }

    @ResponseBody
    @RequestMapping("/insertUser")
    public User insertUser(String userName,String note){
        User user = new User();
        user.setUserName(userName);
        user.setNote(note);
        return userService.insertUser(user);
    }

    @RequestMapping("/findUsers")
    @ResponseBody
    public List<User> findUsers(String userName,String note){
        return userService.findUsers(userName,note);
    }

    @RequestMapping("/updateUserName")
    @ResponseBody
    public Map<String,Object> updateUserName(Long id,String userName){
        User user = userService.updateUser(id, userName);
        boolean flag = user != null;
        String message = flag?"更新成功":"更新失败";
        return resultMap(flag,message);
    }

    @RequestMapping("/deleteUser")
    @ResponseBody
    public Map<String,Object> deleteUser(Long id){
        int result = userService.deleteUser(id);
        boolean flag = result == 1;
        String message = flag?"删除成功":"删除失败";
        return resultMap(flag,message);
    }

    private Map<String,Object> resultMap(Boolean success,String message){
        Map<String,Object> result = new HashMap<>();
        result.put("success",success);
        result.put("message",message);
        return result;
    }
}
