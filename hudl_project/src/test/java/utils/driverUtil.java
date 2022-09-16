package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class driverUtil {

    //The class to initialize the webdriver

    public static WebDriver driver;

    public static WebDriver DriverSetup(){

        //statement to handle the singleton pattern
        if (driver!=null){
            return driver;
        }

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\onlym\\Desktop\\hudl_project\\bin\\chromedriver.exe");
        WebDriverManager.chromedriver().setup();
        WebDriver driver =  new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        return driver;
    }

    //teardown of the driver
    public static void closeDriver(){
        if(driver != null){
            driver.quit();
            driver=null;
        }
    }

}
