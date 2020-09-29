package cn.lethekk.chapter9.controller;

import cn.lethekk.chapter9.pojo.User;
import cn.lethekk.chapter9.service.UserService;
import cn.lethekk.chapter9.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    //注入用户服务类
    @Autowired
    private UserService userService = null;

    /**
     * 打开请求页面
     * @return 字符串，指向页面
     */
    @GetMapping("/add")
    public String add(){
        return "/user/add";
    }

    /**
     * 新增用户
     * @param user 通过@RequestBody注解得到JSON参数
     * @return  回填id后的用户信息
     */
    @PostMapping("insert")
    @ResponseBody
    public User insert(@RequestBody User user){        //参数标注为@RequestBody，可以接收前端提交的JSON请求体
        userService.insertUser(user);
        return user;
    }

    /**
     * 通过URL传递参数
     * http:localhost:8080/user/1
     * @param id
     * @return
     */
    //{...}代表占位符，还可以配置参数名称
    @GetMapping("/{id}")
    //响应为JSON数据集
    @ResponseBody
    //@PathVariable 通过名称获取参数
    public User get(@PathVariable("id") Long id){
        return userService.getUser(id);
    }

    /**
     * 调用控制器前先执行这个方法
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder){
        //绑定验证器
        binder.setValidator(new UserValidator());
        //定义日期参数格式，参数不在需注解@DateTimeFormat,boolean参数表示是否允许为空
        binder.registerCustomEditor(Date.class,new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),false));
    }

    /**
     *
     * @param user  --用户对象用StringToUserConverter
     * @param errors    --验证器返回的错误
     * @param date  --因为WebDataBinder已经绑定了格式，所以不在需要注解
     * @return  各类数据
     */
    public Map<String,Object> validator(@Valid User user, Errors errors,Date date){
        Map<String,Object> map = new HashMap<>();
        map.put("user",user);
        map.put("date",date);
        //判断是否存在错误
        if(errors.hasErrors()){
            //获取全部错误
            List<ObjectError> oes = errors.getAllErrors();
            for (ObjectError oe : oes) {
                //判断是否字段错误
                if(oe instanceof FieldError){       //java 中的instanceof 运算符是用来在运行时指出对象是否是特定类的一个实例。instanceof通过返回一个布尔值来指出，这个对象是否是这个特定类或者是它的子类的一个实例。
                    //字段错误
                    FieldError fe = (FieldError) oe;
                    map.put(fe.getField(),fe.getDefaultMessage());
                }else {
                    //对象错误
                    map.put(oe.getObjectName(),oe.getDefaultMessage());
                }
            }
        }
        return map;
    }

}
