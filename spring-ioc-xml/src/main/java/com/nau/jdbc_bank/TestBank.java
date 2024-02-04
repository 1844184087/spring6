package com.nau.jdbc_bank;

import org.junit.jupiter.api.Test;
import java.sql.Connection;

import static java.lang.Thread.sleep;

public class TestBank {
    @Test
    public void test() throws Exception {
        bankDao bankDao = new bankDao();
     Connection conn = JdbcUtils.getConnection();
     if(conn == null){
         System.out.println("shibai");
         sleep(5000);
     }

    try{
        conn.setAutoCommit(false);
        bankDao.add("经理",5000);
        bankDao.sub("管理员",5000);
        conn.commit();
    }catch(Exception e){
        conn.rollback();
        throw e;
    }finally {
        JdbcUtils.freeConnection();
    }

    }
}
