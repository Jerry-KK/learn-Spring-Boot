package cn.lethekk.chapter5.service.impl;

import cn.lethekk.chapter5.enumeration.SexEnum;
import cn.lethekk.chapter5.pojo.User;
import cn.lethekk.chapter5.service.JdbcTmplUserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

@Service
public class JdbcTmplUserServiceImpl implements JdbcTmplUserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //获取映射关系
    private RowMapper<User> getUserMapper(){
        //使用Lambda表达式创建用户映射关系
        RowMapper<User> userRowMapper = (ResultSet rs , int rownum) -> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setUserName(rs.getString("user_name"));
            int sexId = rs.getInt("sex");
            user.setSex(SexEnum.getEnumById(sexId));
            user.setNote(rs.getString("note"));
            return user;
        };
        return userRowMapper;
    }

    //查询用户信息
    @Override
    public User getUser(Long id) {
        String sql = "select * from t_user where id = ? ";
        Object[] params = new Object[]{id};
        User user = jdbcTemplate.queryForObject(sql, params, getUserMapper());
        return user;
    }

    //查询用户列表
    @Override
    public List<User> findUsers(String userName, String note) {
        String sql = "select * from t_user where user_name like concat('%',?,'%') and note like concat('%',?,'%')";
        Object[] params = new Object[]{userName,note};
        List<User> users = jdbcTemplate.query(sql, params, getUserMapper());
        return users;
    }

    //插入数据库
    @Override
    public int insertUser(User user) {
        String sql = "insert into t_user (user_name,sex,note) values (?,?,?)";
        return jdbcTemplate.update(sql,user.getUserName(),user.getSex().getId(),user.getNote());
    }

    //更新数据库
    @Override
    public int updateUser(User user) {
        String sql = "update  t_user set user_name = ? , sex = ? , note = ? where id = ? ";
        return jdbcTemplate.update(sql,user.getUserName(),user.getSex().getId(),user.getNote(),user.getId());
    }

    //删除数据
    @Override
    public int deleteUser(Long id) {
        String sql = "delete from t_user where id = ?";
        return jdbcTemplate.update(sql,id);
    }

    public User getUser2(Long id){
        //通过Lambda表达式使用StatementCallback
        User result = this.jdbcTemplate.execute((Statement stmt) -> {
            String sql1 = "select count(*) total from t_user where id = " + id;
            ResultSet rs1 = stmt.executeQuery(sql1);
            while (rs1.next()){
                int total = rs1.getInt("total");
                System.out.println(total);
            }
            //执行SQL
            String sql2 = "select id, user_name , sex , note from t_user where id = " + id;
            ResultSet rs2 = stmt.executeQuery(sql2);
            User user = null;
            while (rs2.next()){
                int rowNum = rs2.getRow();
                user = getUserMapper().mapRow(rs2,rowNum);
            }
            return user;
        });
        return result;
    }

    public User getUser3(Long id){
        //通过Lambda表达式使用ConnectionCallback接口
        return this.jdbcTemplate.execute((Connection conn) -> {
            String sql1 = "select count(*) as total from t_user where id = ?";
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setLong(1,id);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()){
                System.out.println(rs1.getInt("total"));
            }
            //执行SQL
            String sql2 = "select id, user_name , sex , note from t_user where id = ?";
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setLong(1,id);
            ResultSet rs2 = ps2.executeQuery();
            User user = null;
            while (rs2.next()){
                int rowNum = rs2.getRow();
                user = getUserMapper().mapRow(rs2,rowNum);
            }
            return user;
        });
    }


}
