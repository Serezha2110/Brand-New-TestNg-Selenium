package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class General {

    static WebDriver driver;


    public static WebElement fluentWaitElement(final By locator) {
        driver = DriverManager.getWebDriver();
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(10L))
                .pollingEvery(Duration.ofSeconds(1L))
                .ignoring(NoSuchElementException.class);

        return wait.until(driver1 -> driver1.findElement(locator));
    }


    public static boolean isElementPresent(By locatorKey) {
        driver = DriverManager.getWebDriver();
        try {
            driver.findElement(locatorKey);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public static void waitAndCheckCurrentURL(String expectedURL) {
        driver = DriverManager.getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10L));
        wait.until(driver1 -> driver1.getCurrentUrl().equals(expectedURL));
    }

    public static void goToPage(String page) {
        driver = DriverManager.getWebDriver();
        driver.get(page);
    }

}