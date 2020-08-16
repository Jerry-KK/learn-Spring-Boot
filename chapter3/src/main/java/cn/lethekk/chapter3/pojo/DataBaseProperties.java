package cn.lethekk.chapter3.pojo;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@Getter
@ConfigurationProperties("database")            //@ConfigurationProperties中配置的字符串database，将与POJO的属性名称组成属性的全限定名去配置文件里面找，比@Value配置量有所减少
public class DataBaseProperties {

    //@Value("${database.driverName}")
    private String driverName = null;

    //@Value("${database.url}")
    private String url = null;

    private String username = null;

    private String password = null;

    public void setDriverName(String driverName){
        System.out.println(driverName);
        this.driverName = driverName;
    }

    public void setUrl(String url){
        System.out.println(url);
        this.url = url;
    }
    //@Value("${database.username}")
    public void setUsername(String username){
        System.out.println(username);
        this.username = username;
    }

    //@Value("${database.password}")
    public void setPassword(String password){
        System.out.println(password);
        this.password = password;
    }

}
