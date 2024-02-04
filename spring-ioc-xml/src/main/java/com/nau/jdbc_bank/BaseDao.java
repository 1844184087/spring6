package com.nau.jdbc_bank;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaseDao {
    //删改查

    public static int executeUpdate(String sql,Object ...parms) throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        for(int i=1;i<=parms.length;i++){
            ps.setObject(i,parms[i-1]);
        }
        int result = ps.executeUpdate();
        ps.close();
        if(connection.getAutoCommit()){
            JdbcUtils.freeConnection();
        }
        return result;
    }
    public <T> List<T> executeQuery(Class<T> clazz, String sql, Object ...parms) throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        for(int i=1;i<=parms.length;i++){
            ps.setObject(i,parms[i-1]);
        }
        List<T> list = new ArrayList<>();
        ResultSet resultSet = ps.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        while(resultSet.next()) {
            T t = clazz.newInstance();
            for(int i = 1;i<=columnCount;i++){
                Object value = resultSet.getObject(i);
                String columnName = metaData.getColumnLabel(i);
                Field field = clazz.getDeclaredField(columnName);
                field.setAccessible(true);
                field.set(t,value);
        }
            list.add(t);
        }
        ps.close();
        resultSet.close();
        if(connection.getAutoCommit()) {
            JdbcUtils.freeConnection();
        }
        return list;
    }
}
