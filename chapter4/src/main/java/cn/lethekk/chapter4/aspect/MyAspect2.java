package cn.lethekk.chapter4.aspect;

import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class MyAspect2 {

    @Pointcut("execution(* cn.lethekk.chapter4.service.ManyAspectService.manyAspects(..))")
    public void manyAspects(){
    }

    @Before("manyAspects()")
    public void before(){
        System.out.println("MyAspect2 before ...");
    }

    @After("manyAspects()")
    public void after(){
        System.out.println("MyAspect2 after ...");
    }

    @AfterReturning("manyAspects()")
    public void afterReturning(){
        System.out.println("MyAspect2 afterReturning ...");
    }

}
