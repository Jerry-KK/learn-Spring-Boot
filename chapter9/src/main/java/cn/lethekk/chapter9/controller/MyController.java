package cn.lethekk.chapter9.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/my")
public class MyController {

    /**
     * 在无注解下获取参数，要求参数名称和HTTP请求参数名称一致
     * http：//localhost:8080/my/no/annotation?intVal=10&longVal=200
     * 参数在默认规则下可以为空，所以没有字符串参数str不会报错
     * @param intVal
     * @param longVal
     * @param str
     * @return
     */
    @GetMapping("/no/annotation")
    @ResponseBody       //  控制器返回的结果会转化为JSON数据集
    public Map<String,Object> noAnnotation(Integer intVal,Long longVal,String str){
        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("intVal",intVal);
        paramsMap.put("longVal",longVal);
        paramsMap.put("str",str);
        return paramsMap;
    }

    /**
     * 通过注解@RequestParam获取参数
     * http：//localhost:8080/my/no/annotation?int_val=10&long_val=200&str_val=str
     * @RequestParam标注的参数默认是不能为空的，为了能够为空，需配置属性required=false
     * @param intVal
     * @param longVal
     * @param strVal
     * @return
     */
    @GetMapping("/annotation")
    @ResponseBody
    public Map<String,Object> requestParam(
            @RequestParam("int_val") Integer intVal,
            @RequestParam("long_val")Long longVal,
            @RequestParam(value = "str_val",required = false) String strVal
    ){
        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("intVal",intVal);
        paramsMap.put("longVal",longVal);
        paramsMap.put("strVal",strVal);
        return paramsMap;
    }

    /**
     * Spring MVC 能够支持用逗号分隔的数组参数
     * http://localhost:8080/my/requestArray?intArr=1,2,3&longArr=4,5,6&strArr=str1,str2,str3
     * @param intArr
     * @param longArr
     * @param strArr
     * @return
     */
    @GetMapping("/requestArray")
    @ResponseBody
    public Map<String,Object> requestArray(int[] intArr,Long[] longArr,String[] strArr){
        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("intArr",intArr);
        paramsMap.put("longArr",longArr);
        paramsMap.put("strArr",strArr);
        return paramsMap;
    }





















}
