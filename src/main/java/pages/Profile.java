package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Profile extends BasePage{

    By notLogginLabel = By.cssSelector("#notLoggin-label");

    public Profile(WebDriver driver) {
        super(driver);
    }

    public void checkNotLogginLabelNotExists() {
        Assert.assertFalse(isElementPresent(notLogginLabel));
    }

    public void checkNotLogginLabelExists() {
        Assert.assertTrue(isElementPresent(notLogginLabel));
    }

}
