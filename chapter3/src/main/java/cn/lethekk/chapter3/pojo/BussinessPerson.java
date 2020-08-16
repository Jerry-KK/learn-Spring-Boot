package cn.lethekk.chapter3.pojo;

import cn.lethekk.chapter3.pojo.definition.Animal;
import cn.lethekk.chapter3.pojo.definition.Person;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class BussinessPerson implements Person, BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean {

    /*@Autowired  首先它会根据类型找到对应的Bean,如果对应类型的Bean不是唯一的，那么它会根据其属性名称和Bean的名称进行匹配。
    * 注意：@Autowired  是一个默认必须找到对应Bean的注解，如果不能确定其标注属性一定会存在并且允许这个标注的属性为null,可以配置属性 @Autowired(required = false)*/
    //@Autowired
    //@Qualifier("dog")       // @Qualifier和 @Autowired组合在一起，可以通过类型和名称一起找到Bean，Bean的名称在SpringIoC容器是唯一的标识，通过 @Qualifier可以消除歧义
    private Animal animal = null;


    /*public BussinessPerson(@Autowired @Qualifier("dog") Animal animal){  //可对构造方法的参数进行注入
        this.animal = animal;
    }*/

    @Override
    public void service() {
        this.animal.use();
    }

    @Override
    @Autowired   //也可标注在方法上
    @Qualifier("dog")
    public void setAnimal(Animal animal) {
        System.out.println("延迟依赖注入");
        this.animal = animal;
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("【"+this.getClass().getSimpleName()+"】调用BeanNameAware的setBeanName");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("【"+this.getClass().getSimpleName()+"】调用BeanFactoryAware的setBeanFactory");

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("【"+this.getClass().getSimpleName()+"】调用ApplicationContextAware的setApplicationContext");
    }



    @PostConstruct
    public void init(){
        System.out.println("【"+this.getClass().getSimpleName()+"】注解@PostConstruct定义的自定义初始化方法");

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("【"+this.getClass().getSimpleName()+"】调用InitializingBean的afterPropertiesSet");

    }

    @PreDestroy
    public void destroy1(){
        System.out.println("【"+this.getClass().getSimpleName()+"】注解@PreDestroy定义的自定义销毁方法");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("【"+this.getClass().getSimpleName()+"】DisposableBean方法");
    }




}
