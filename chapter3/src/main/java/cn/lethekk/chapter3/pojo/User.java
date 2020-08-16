package cn.lethekk.chapter3.pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component("user")  //标明哪个类被扫描进入Spring IoC容器
public class User {
    @Value("2")
    private Long id;
    @Value("user_name_2")
    private String userName;
    @Value("note_2")
    private String note;
}
