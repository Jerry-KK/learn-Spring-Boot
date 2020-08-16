package cn.lethekk.chapter3;

import cn.lethekk.chapter3.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;

import javax.xml.ws.Service;

@SpringBootApplication
@ComponentScan(value = "cn.lethekk.chapter3",
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = {UserService.class})}
        /*,lazyInit = true*/)
@PropertySource(value = {"classpath:jdbc.properties"},ignoreResourceNotFound = true)  //配置配置文件地址，value可以配置多个，ignoreResourceNotFound表示忽略找不到文件的问题
public class Chapter3Application {

    public static void main(String[] args) {
        SpringApplication.run(Chapter3Application.class, args);
    }

}
