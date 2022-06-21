package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.General;
import utils.DriverManager;

public class Login {

    WebDriver driver;

    By userNameField = By.cssSelector("#userName");
    By passwordField = By.cssSelector("#password");
    By loginButton = By.cssSelector("#login");
    By errorText = By.cssSelector("#name");

    public Login(WebDriver driver) {
        this.driver = driver;
    }

    public Login setUserName(String userName) {
        driver.findElement(userNameField).sendKeys(userName);
        return this;
    }

    public Login setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public Login checkExistsAndAssertErrorText(String expectedText) {
        General.fluentWaitElement(errorText);
        String actualText = driver.findElement(errorText).getText();
        Assert.assertEquals(actualText, expectedText, "Unexpected error text");
        return this;
    }

    public Login checkInputFieldHasInvalidStatus(String fieldName) {
        String elementClass = "";
        switch (fieldName) {
            case "UserName":
                General.fluentWaitElement(userNameField);
                elementClass = driver.findElement(userNameField).getAttribute("class");
                break;
            case "Password":
                General.fluentWaitElement(passwordField);
                elementClass = driver.findElement(passwordField).getAttribute("class");
                break;
        }
        Assert.assertEquals(elementClass, "mr-sm-2 is-invalid form-control", "Unexpected element status");
        return this;
    }

}
