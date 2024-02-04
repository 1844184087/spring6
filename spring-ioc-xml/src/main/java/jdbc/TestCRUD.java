package jdbc;

import com.alibaba.druid.sql.ast.statement.SQLDescribeStatement;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.parsing.ParseState;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import  java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestCRUD {
    @Test
    public void testInsert() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.
                getConnection("jdbc:mysql://localhost:3306/my_jdbc?rewriteBatchedStatements=true", "root","110110");
        String sql = "insert into t_user(account,password,nickname) values(?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        long start = System.currentTimeMillis();
        for(int i=0;i<10000;i++){
            statement.setObject(1, "zhangsand"+i);
            statement.setObject(2, "123456d"+i);
            statement.setObject(3, "张三d"+i);
            statement.addBatch();
        }
        statement.executeBatch();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        connection.close();
        statement.close();
    }
    @Test
    public void testUpdate() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/my_jdbc", "root","110110");
        String sql = "update t_user set nickname =? where id =?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setObject(1, "三狗子");
        statement.setObject(2, 3);
        statement.executeUpdate();
        statement.close();
        connection.close();
    }
    @Test
    public void testDelete() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/my_jdbc","root","110110");
        String sql = "delete from t_user where id = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setObject(1, 3);
        statement.executeUpdate();
        statement.close();
        connection.close();
    }
    @Test
    public void testSelect() throws ClassNotFoundException,SQLException {
        List<Map> list = new ArrayList<Map>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/my_jdbc","root","110110");
        String sql = "select id,account as ac from t_user;";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        ResultSetMetaData  resultSetMetaData =  statement.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();
        while(resultSet.next()) {
            Map map = new HashMap();
            for(int i=1;i<=columnCount;i++) {//获取这一行中每一列的数据
                map.put(resultSetMetaData.getColumnLabel(i),resultSet.getObject(i));//key,value
            }
            list.add(map);
        }
        System.out.println(list);
    }
}
