package tests;

import com.mysql.cj.protocol.Resultset;
import org.testng.annotations.Test;

import java.sql.*;
/*
 *@className: jdbcConnectionTest
 *@objective- to inject data from database into java code.
 */
public class jdbcConnectionTest {
    @Test
    public void databaseConnection() throws SQLException {
        String host ="localhost";
        String port = "3306";
        //connect to database
        Connection con =DriverManager.getConnection("jdbc:mysql://"+ host + ":" + port +"/sys","root","Varsha@123");
        Statement statement = con.createStatement();
        ResultSet resultset = statement.executeQuery("SELECT * FROM credentials WHERE scenario ='zeroBalanceCard'");
       //to move to index from start
        while (resultset.next()) {
            System.out.println(resultset.getString("username"));
            System.out.println(resultset.getString("password"));
        }

    }
}
