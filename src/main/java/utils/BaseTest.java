package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        driver = WebDriverManager.chromedriver().create();
        configureDriver();

    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        driver.quit();
    }


    protected void configureDriver() {
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

}
