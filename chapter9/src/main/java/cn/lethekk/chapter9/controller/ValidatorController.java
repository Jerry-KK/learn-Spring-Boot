package cn.lethekk.chapter9.controller;

import cn.lethekk.chapter9.pojo.ValidatorPojo;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/valid")
@Controller
public class ValidatorController {

    @RequestMapping(value = "/valid/validate")
    @ResponseBody
    public Map<String,Object> validate(@Valid @RequestBody ValidatorPojo vp, Errors errors){
        Map<String,Object> errMap = new HashMap<>();
        //获取错误列表
        List<ObjectError> oes = errors.getAllErrors();
        for (ObjectError oe: oes) {
            String key = null;
            String msg = null;
            //字符错误
            if(oe instanceof FieldError){
                FieldError fe = (FieldError)oe;
                key = fe.getField();    //获取错误验证字段名
            }else {
                //非字段错误
                key = oe.getObjectName(); //获取验证对象名称
            }
            //错误信息
            msg = oe.getDefaultMessage();
            errMap.put(key,msg);
        }
        return errMap;
    }
}
