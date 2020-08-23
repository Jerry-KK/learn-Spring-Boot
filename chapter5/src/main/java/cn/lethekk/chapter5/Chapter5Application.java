package cn.lethekk.chapter5;

import cn.lethekk.chapter5.dao.MybatisUserDao;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@SpringBootApplication

//实际上，以下两个配置没有，只要依赖spring-boot-starter-data-jpa,Spring Boot 2.会对项目进行扫描，
//定义JPA接口扫描路径
@EnableJpaRepositories(basePackages = "cn.lethekk.chapter5.dao")
//定义实体bean扫描包路径
@EntityScan(basePackages = "cn.lethekk.chapter5.pojo")

@MapperScan(
        //指定扫描的包
        basePackages = "cn.lethekk.chapter5.*",
        //指定sqlSessionFactory,如果sqlSessionTemplate被指定，则作废
        sqlSessionFactoryRef = "sqlSessionFactory",
        //指定sqlSessionTemplate,将忽略sqlSessionFactory的配置
        sqlSessionTemplateRef = "sqlSessionTemplate",
        //限定扫描接口，不常用
        annotationClass = Repository.class
)
public class Chapter5Application {

    /*@Autowired
    SqlSessionFactory sqlSessionFactory = null;

    @Bean
    public MapperFactoryBean<MybatisUserDao> initMyBatisUserDao(){
        MapperFactoryBean<MybatisUserDao> bean = new MapperFactoryBean<>();
        bean.setMapperInterface(MybatisUserDao.class);
        bean.setSqlSessionFactory(sqlSessionFactory);
        return bean;
    }*/

    public static void main(String[] args) {
        SpringApplication.run(Chapter5Application.class, args);
    }





}
