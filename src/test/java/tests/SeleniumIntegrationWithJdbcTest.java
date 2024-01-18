package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.sql.*;
/*
 *@className: SeleniumIntegrationWithJdbcTest
 *@objective- to inject data from database into selenium script with help of connection
 */

public class SeleniumIntegrationWithJdbcTest {
    @Test
    public void jdbcConnection() throws SQLException {
        String host ="localhost";
        String port = "3306";
        //connect to database
        Connection con = DriverManager.getConnection("jdbc:mysql://"+ host + ":" + port +"/sys","root","Varsha@123");
        Statement statement = con.createStatement();
        ResultSet resultset = statement.executeQuery("SELECT * FROM credentials WHERE scenario ='rewardsCard'");
        //to move to index from start
        while (resultset.next()) {
            WebDriverManager.chromedriver().setup();
            WebDriver driver = new ChromeDriver();
            driver.get("https://login.salesforce.com");
            //info to pass in usename and passsword from database
            driver.findElement(By.xpath(".//*[@id='username']")).sendKeys(resultset.getString("username"));
            driver.findElement(By.xpath(".//*[@id='password']")).sendKeys(resultset.getString("password"));
            driver.close();
        }

    }
}
