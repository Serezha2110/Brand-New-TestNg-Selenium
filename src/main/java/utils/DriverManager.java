package utils;

import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Objects;

public class DriverManager {

    private static WebDriver driver;

    public static WebDriver initDriver() {
        Dotenv env = Dotenv.load();

        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", Objects.requireNonNull(env.get("WEB_DRIVER_PATH")));
            driver = new ChromeDriver();
            configureDriver();
        }
        return driver;
    }

    public static WebDriver getWebDriver() {
        return driver;
    }

    public static void dropWebDriver() {
        driver.quit();
        driver = null;
    }

    private static void configureDriver() {
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }
}
