package cn.lethekk.chapter4.aspect;

import cn.lethekk.chapter4.pojo.User;
import cn.lethekk.chapter4.validator.UserValidator;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {

    //引入新的接口
    @DeclareParents(value = "cn.lethekk.chapter4.service.impl.UserServiceImpl",
    defaultImpl = cn.lethekk.chapter4.validator.impl.UserValidatorImpl.class)
    public UserValidator userValidator;


    //定义切点
    @Pointcut("execution(* cn.lethekk.chapter4.service.impl.UserServiceImpl.printUser(..))")
    public void pointCut(){

    }

    @Before("pointCut() && args(user)")     // args(user) ：约定将连接点（目标方法对象）名称为user的参数传递进来
    public void before(JoinPoint point, User user){     //对于非环绕通知，可使用一个连接点（JoinPoint）类型的参数，通过它也可以获取参数
        Object[] args = point.getArgs();
        System.out.println("before .......");
    }

    @After("pointCut()")
    public void after(){
        System.out.println("after .......");
    }

    @AfterReturning("pointCut()")
    public void afterReturning(){
        System.out.println("afterReturning .......");
    }

    @AfterThrowing("pointCut()")
    public void afterThrowing(){
        System.out.println("afterThrowing .......");
    }

    @Around("pointCut()")
    public void around(ProceedingJoinPoint jp) throws Throwable{
        System.out.println("around before ......");
        jp.proceed();
        System.out.println("around after .......");
    }



}
