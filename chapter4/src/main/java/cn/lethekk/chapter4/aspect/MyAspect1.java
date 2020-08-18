package cn.lethekk.chapter4.aspect;

import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(3)
public class MyAspect1 {

    @Pointcut("execution(* cn.lethekk.chapter4.service.ManyAspectService.manyAspects(..))")
    public void manyAspects(){
    }

    @Before("manyAspects()")
    public void before(){
        System.out.println("MyAspect1 before ...");
    }

    @After("manyAspects()")
    public void after(){
        System.out.println("MyAspect1 after ...");
    }

    @AfterReturning("manyAspects()")
    public void afterReturning(){
        System.out.println("MyAspect1 afterReturning ...");
    }

}
