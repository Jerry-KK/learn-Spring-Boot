package cn.lethekk.chapter3.config;

import cn.lethekk.chapter3.pojo.BussinessPerson;
import cn.lethekk.chapter3.pojo.ScopeBean;
import cn.lethekk.chapter3.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class IoCTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        /*User user = ctx.getBean(User.class);
        log.info(user.toString());*/

       /* BussinessPerson person = ctx.getBean(BussinessPerson.class);
        person.service();
        ctx.close();*/

        ScopeBean s1 = ctx.getBean(ScopeBean.class);
        ScopeBean s2 = ctx.getBean(ScopeBean.class);
        System.out.println(s1 == s2);
    }
}
