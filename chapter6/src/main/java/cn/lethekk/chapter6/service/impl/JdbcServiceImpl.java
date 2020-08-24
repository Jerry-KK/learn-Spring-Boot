package cn.lethekk.chapter6.service.impl;

import cn.lethekk.chapter6.service.JdbcService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcServiceImpl implements JdbcService {

    @Autowired
    private DataSource dataSource;

    @Override
    public int inserUser(String userName, String note) {
        Connection connection = null;
        int result = 0;
        try{
            //获取连接
            connection = dataSource.getConnection();
            //开启事务
            connection.setAutoCommit(false);
            //设置隔离级别
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            //执行SQL
            PreparedStatement ps = connection.prepareStatement("insert into t_user(user_name,note) value (?,?)");
            ps.setString(1,userName);
            ps.setString(2,note);
            //提交事务
            connection.commit();
        } catch (SQLException e) {
            //回滚事务
            if(connection != null){
                try{
                    connection.rollback();
                }catch (SQLException e1){
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        }finally {
            //关闭数据库连接
            try{
                if(connection != null && !connection.isClosed()){
                    connection.close();
                }
            }catch (SQLException e2){
                e2.printStackTrace();
            }
        }
        return 0;
    }
}
