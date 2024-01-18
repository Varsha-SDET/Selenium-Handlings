package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/*
 *@className: WindowPopupTest
 *@objective- control window popup - auth popups
 */
public class WindowPopupTest {
    WebDriver driver;
    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void handleWindowPopup(){
        //https://the-internet.herokuapp.com -> actual url
        //pass in user passs@site form (1 time process as it requires to authenticate)
        driver.get("https://admin:admin@the-internet.herokuapp.com/");
        driver.findElement(By.linkText("Basic Auth")).click();


    }
    @AfterClass
    public void closeBrowser(){
        driver.close();
    }
}
