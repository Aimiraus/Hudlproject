package testCases;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.hudlHomePage;
import pages.loginPage;
import pages.mainPage;
import utils.credsReader;
import utils.driverUtil;
import utils.encryptPassword;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class hudlLoginTest {

    WebDriver driver;
    WebDriverWait wait;
    //setting up the driver
    @BeforeClass
    void setup(){
        driver=driverUtil.DriverSetup();
    }
    // health check
    @BeforeMethod
    void healthCheck() throws IOException {

        hudlHomePage hudlHomePage = new hudlHomePage(driver);

        // launching gmail.com on the browser
        driver.get(credsReader.fileReader("url"));
        // maximized the browser window
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        // explicit wait for the element of the hudl page to load
        wait.until(ExpectedConditions.visibilityOf(hudlHomePage.homePageTitle));
        // asserting the attribute value
        Assert.assertEquals(hudlHomePage.homePageTitle.getAttribute("title"),"Home");

    }
    // login steps
    @Test(priority = 1)
    void testLogin() throws Exception {

        hudlHomePage hudlHomePage = new hudlHomePage(driver);
        loginPage loginPage = new loginPage(driver);
        mainPage mainPage = new mainPage(driver);
        encryptPassword encryptedPwd = new encryptPassword();

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128); // block size is 128bits
        SecretKey secretKey = keyGenerator.generateKey();

        //navigating to login page
        hudlHomePage.homeLoginButton.click();
        wait.until(ExpectedConditions.visibilityOf(loginPage.loginButton));

        // entering username
        loginPage.emailTextBox.sendKeys(credsReader.fileReader("username"));
        // entering password
        loginPage.passwordTextBox.sendKeys(encryptedPwd.pwd());
        // clicking log in button
        loginPage.loginButton.click();
        // explicit wait - to wait for the home page to load
        wait.until(ExpectedConditions.visibilityOf(mainPage.mainPageProfile));

        Assert.assertEquals("Aimira U",mainPage.mainPageProfile.getText());
        }

        //teardown of the driver
        @AfterTest
    void teardown() throws IOException {

       long timeStamp = System.currentTimeMillis();
       File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
       FileUtils.copyFile(screenshot,new File("src\\test\\java\\resources\\"+timeStamp+".jpg."));

       driver.close();
    }
}