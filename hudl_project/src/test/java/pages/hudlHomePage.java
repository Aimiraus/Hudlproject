package pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class hudlHomePage {

    WebDriver driver;

    public hudlHomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//a[@title='Home']")
    public WebElement homePageTitle;

    @FindBy(xpath = "//a[text()='Log in']")
    public WebElement homeLoginButton;


}
