package cn.lethekk.chapter4.controller;

import cn.lethekk.chapter4.pojo.User;
import cn.lethekk.chapter4.service.ManyAspectService;
import cn.lethekk.chapter4.service.UserService;
import cn.lethekk.chapter4.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService = null;

    @Autowired
    private ManyAspectService manyAspectService = null;

    @RequestMapping("/print")
    @ResponseBody
    public User printUser(Long id,String userName,String note){
        User user = new User();
        user.setUserId(id);
        user.setUserName(userName);
        user.setNote(note);
        userService.printUser(user);
        return user;

    }


    @RequestMapping("/vp")
    @ResponseBody
    public User ValidateAndPoint(Long id,String userName,String note){
        User user = new User();
        user.setUserId(id);
        user.setUserName(userName);
        user.setNote(note);

        //强制转换
        UserValidator userValidator = (UserValidator) userService;
        if(userValidator.validate(user)){
            userService.printUser(user);
        }
        return user;
    }


    @RequestMapping("/manyAspects")
    public void manyAspects(){
        manyAspectService.manyAspects();
    }
}
