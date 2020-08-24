package cn.lethekk.chapter6.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias("user")
@Data
public class User {
    private Long id;
    private String userName;
    private String note;
}
