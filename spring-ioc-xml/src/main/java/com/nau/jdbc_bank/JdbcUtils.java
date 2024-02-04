package com.nau.jdbc_bank;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
    public static DataSource dataSource = null;
    static {
            Properties properties = new Properties();
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            try {
                properties.load(inputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        try {
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
    public static Connection getConnection() throws SQLException {
        Connection connection = tl.get();
        if(connection == null) {
            connection = dataSource.getConnection();
            tl.set(connection);
        }
        return connection;
    }
    public static void freeConnection() throws SQLException {
        Connection connection = tl.get();
        if(connection != null){
            tl.remove();
            connection.setAutoCommit(true);
            connection.close();

        }
    }
}
