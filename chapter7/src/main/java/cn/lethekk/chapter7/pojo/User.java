package cn.lethekk.chapter7.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("user")
@Data
public class User implements Serializable{

    private static final long serialVersionUID = 4890645954891701033L;

    private Long id;
    private String userName;
    private String note;

}
