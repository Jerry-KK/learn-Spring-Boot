package cn.lethekk.chapter3.config;

import cn.lethekk.chapter3.condition.DatabaseConditional;
import cn.lethekk.chapter3.pojo.User;
import cn.lethekk.chapter3.service.UserService;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration      //代表这是一个Java配置文件，Spring的容器会根据它来生成IoC容器去装配Bean
//@ComponentScan    //扫描装配Bean，只扫描当前包和其子包

//@ComponentScan("cn.lethekk.chapter3.*")
//@ComponentScan(basePackages = "cn.lethekk.chapter3.*")
//@ComponentScan(basePackageClasses = {User.class})
@ComponentScan(value = "cn.lethekk.chapter3",
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = {UserService.class})}
        /*,lazyInit = true*/)
/*
@ComponentScan(value = "cn.lethekk.chapter3",
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = {UserService.class})})
    value:指明扫描的包
    excludeFilters：指明哪些包不被扫描到，相应的includeFilters是指明只扫描哪些类
    type:指按哪种类型来进行过滤        FilterType为一个枚举类,总共有5个值,也就是说type总共有5个可选值
        public enum FilterType {
	        ANNOTATION,//按照注解方式
	        ASSIGNABLE_TYPE,//按照指定类型的方式
	        ASPECTJ,//使用ASPECTJ表达式的方式-------没用过,不演示
	        REGEX,//利用正则表达式进行指定-----------没用过,不演示
	        CUSTOM//自己实现TypeFilter接口进行自定义规则(如下面的代码)
        }
    classes:classes为一个数组，里面为具体过滤的条件实体
    参考博客：https://blog.csdn.net/nrsc272420199/article/details/88385574

    lazyInit:延迟加载，默认为false
 */
public class AppConfig {
    /*@Bean(name = "user")      //代表将initUser方法返回的POJO装配到IoC容器中，其属性name定义这个Bean的名称，如果没有配置name，则用方法名称“initUser”作为Bean的名称保存到Spring IoC 容器中
    public User initUser(){
        User user = new User();
        user.setId(1L);
        user.setUserName("user_name_1");
        user.setNote("note_1");
        return user;
    }*/

    /*@Bean(name = "dataSource")
    public DataSource getDataSource(){
        Properties properties = new Properties();
        properties.getProperty("driver","com.mysql.jdbc.Driver");
        properties.setProperty("url","jdbc:mysql://localhost:3306/chapter3");
        properties.setProperty("name","root");
        properties.setProperty("password","123456");
        DataSource dataSource = null;
        try{
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        }catch (Exception e){
            e.printStackTrace();
        }
        return dataSource;
    }*/

    /*@Bean(name = "dataSource")
    @Conditional(DatabaseConditional.class)
    public DataSource getDataSource(
            @Value("${database.driverName}") String driver,
            @Value("${database.url}") String url,
            @Value("${database.username}") String username,
            @Value("${database.password}") String password
    ){
        Properties properties = new Properties();
        properties.getProperty("driver",driver);
        properties.setProperty("url",url);
        properties.setProperty("name",username);
        properties.setProperty("password",password);
        DataSource dataSource = null;
        try{
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        }catch (Exception e){
            e.printStackTrace();
        }
        return dataSource;
    }*/

    @Bean(name = "dataSource")
    @Profile("dev")
    public DataSource getDataSource(){
        Properties properties = new Properties();
        properties.getProperty("driver","com.mysql.jdbc.Driver");
        properties.setProperty("url","jdbc:mysql://localhost:3306/chapter3");
        properties.setProperty("name","root");
        properties.setProperty("password","123456");
        DataSource dataSource = null;
        try{
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        }catch (Exception e){
            e.printStackTrace();
        }
        return dataSource;
    }

    /*@Bean(name = "dataSource")
    @Profile("test")
    public DataSource getDataSource(){
        Properties properties = new Properties();
        properties.getProperty("driver","com.mysql.jdbc.Driver");
        properties.setProperty("url","jdbc:mysql://localhost:3306/chapter3");
        properties.setProperty("name","root");
        properties.setProperty("password","123456");
        DataSource dataSource = null;
        try{
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        }catch (Exception e){
            e.printStackTrace();
        }
        return dataSource;
    }*/
}
