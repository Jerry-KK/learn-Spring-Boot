package cn.lethekk.chapter3.pojo;

import cn.lethekk.chapter3.pojo.definition.Animal;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary        //当发现多个同样类型的Bean时，优先使用@Primary标注的进行注入
public class Cat implements Animal {
    @Override
    public void use() {
        System.out.println("猫【" + Cat.class.getSimpleName()+"】是抓老鼠。");
    }
}
