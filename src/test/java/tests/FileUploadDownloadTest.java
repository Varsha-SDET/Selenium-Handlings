package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

/*
 *@className: FileUploadDownloadTest
 *@objective- control window popup- file uploads and download using AutoIT
 */
public class FileUploadDownloadTest {
    WebDriver driver;

        @Test
        public void uploadFileDownload() throws InterruptedException, IOException {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://altoconvertpdftojpg.com/");
            //Thread.sleep(3000);
            driver.findElement(By.cssSelector(".shadow-sm.inline-flex")).click();
            Thread.sleep(3000);
            //execute the file through java
            //C:\Users\varsh\OneDrive\Desktop
            // -> file uploading
            Runtime.getRuntime().exec("C:\\Users\\varsh\\OneDrive\\Desktop\\Documents\\fileUpload.exe");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
            //convert  button visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button#submit_btn")));
            driver.findElement(By.cssSelector("button#submit_btn")).click();
            //download button visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Download')]")));
            driver.findElement(By.xpath("//a[contains(text(),'Download')]")).click();
            Thread.sleep(6000);
            //to check downloaded file stored in local system
            //C:\Users\varsh\Downloads\Varsha_Resume.jpg
            File file = new File("C:\\Users\\varsh\\Downloads\\Varsha_Resume.jpg");
            if (file.exists()){
                System.out.println("file found");
            }else {
                System.out.println("file not found");
            }

        }

     //to setup on jenkins can use this type
    //step 1: reroute file checking to our own project
    //step 2: to tell chrome browser download into our own project for dynamic path
    @Test
    public void fileUploadDownload2() throws InterruptedException, IOException {
        WebDriverManager.chromedriver().setup();
        //system default directory-> project path
        String downloadPath = System.getProperty("user.dir");   //path of project ->dyamic path
        HashMap<String,Object> chromePrefs = new HashMap<String,Object>();
        //
        chromePrefs.put("profile.default_content_settings.popups",0);
        //on downloading anything download in default project path
        chromePrefs.put("download.default_directory",downloadPath);
        ChromeOptions chromeOptions = new ChromeOptions();
        //to download in project on downloading anything
        chromeOptions.setExperimentalOption("prefs",chromePrefs);
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://altoconvertpdftojpg.com/");
        //Thread.sleep(3000);
        driver.findElement(By.cssSelector(".shadow-sm.inline-flex")).click();
        Thread.sleep(3000);
        //execute the file through java
        //C:\Users\varsh\OneDrive\Desktop
        // -> file uploading
        Runtime.getRuntime().exec("C:\\Users\\varsh\\OneDrive\\Desktop\\Documents\\fileUpload.exe");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        //convert  button visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button#submit_btn")));
        driver.findElement(By.cssSelector("button#submit_btn")).click();
        //download button visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Download')]")));
        driver.findElement(By.xpath("//a[contains(text(),'Download')]")).click();
        //to download file time given is staticlly given
        Thread.sleep(7000);
        //to get dynamic path downloaded file , its stored in physical machine
        //eg, ->C:\Users\varsh\Downloads\Varsha_Resume.jpg
        File file = new File(downloadPath+"/Varsha_Resume.jpg");
        if (file.exists()){
           System.out.println("file found");
            Assert.assertTrue(file.exists());
           // to delete after downloading from project after verifying its downloaded else everyting multiple files will be created
            if(file.delete()){
                System.out.println("file deleted");
            }
        }else {
            System.out.println("file not found");
        }



    }
//
//        @AfterClass
//        public void closeBrowser(){
//            driver.close();
//        }
}
