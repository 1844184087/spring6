package com.nau.jdbc_bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class bankDao {
    public void add(String account, int money) throws SQLException {
        Connection conn = JdbcUtils.getConnection();
        String sql = "update t_bank set money = money + ? where account = ?;";
    PreparedStatement stmt = conn.prepareStatement(sql);
    stmt.setObject(1,money);
    stmt.setObject(2,account);
    stmt.executeUpdate();
        System.out.println("+成功");
    stmt.close();
    }
    public void sub(String account,int money) throws SQLException {
        Connection conn = JdbcUtils.getConnection();
        String sql = "update t_bank set money = money - ? where account = ?;";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setObject(1,money);
        stmt.setObject(2,account);
        stmt.executeUpdate();
        System.out.println("-成功");
        stmt.close();
    }
}
