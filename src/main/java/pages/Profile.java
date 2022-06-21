package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.General;

public class Profile {

    WebDriver driver;

    By notLogginLabel = By.cssSelector("#notLoggin-label");

    public Profile(WebDriver driver) {
        this.driver = driver;
    }

    public void checkNotLogginLabelNotExists() {
        Assert.assertFalse(General.isElementPresent(notLogginLabel));
    }

    public void checkNotLogginLabelExists() {
        Assert.assertTrue(General.isElementPresent(notLogginLabel));
    }

}
