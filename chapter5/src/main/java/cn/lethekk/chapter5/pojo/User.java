package cn.lethekk.chapter5.pojo;

import cn.lethekk.chapter5.converter.SexConverter;
import cn.lethekk.chapter5.enumeration.SexEnum;
import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import javax.persistence.*;


@Data
@Entity(name = "user")
@Table(name = "t_user")
@Alias(value = "user")
public class User {
    //标明主键
    @Id
    //主键策略，递增
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    //定义属性和表的映射
    @Column(name = "user_name")
    private String userName = null;

    //定义转换器
    @Convert(converter = SexConverter.class)
    private SexEnum sex = null;

    private String note = null;


}
