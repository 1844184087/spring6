package jdbc;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class jdbc {
    public static void main(String[] args) throws SQLException {

        DriverManager.registerDriver(new Driver());

        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/my_jdbc","root","110110");

        Statement statement = connection.createStatement();

        String sql = "select * from t_user";
        ResultSet  resultSet = statement.executeQuery(sql);

        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String account = resultSet.getString("account");
            String passwd = resultSet.getString("password");
            String nickname = resultSet.getString("nickname");
            System.out.println(id+account+passwd+nickname);
        }
    }
}
