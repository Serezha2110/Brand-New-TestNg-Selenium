package authorisation;

import org.testng.annotations.*;
import pages.BasePage;
import pages.BookStore;
import pages.Login;
import pages.Profile;
import utils.BaseTest;

public class AuthorisationTest extends BaseTest {

    String bookStorePage = "https://demoqa.com/books";
    String profilePage = "https://demoqa.com/profile";

    @Parameters({"registeredUserName", "registeredUserPassword"})
    @Test(testName = "Корректная авторизация и переход в личный кабинет"
            ,groups = {"authorisation", "positive"}
    )
    public void correctAccessToProfile(String userName, String password) {
        driver.get(bookStorePage);
        new BookStore(driver).clickLoginButton();
        Login login = new Login(driver);

        login.setUserName(userName)
                .setPassword(password)
                .clickLogin();

        BasePage basePage = new BasePage(driver);
        basePage.waitAndCheckCurrentURL(bookStorePage);
        driver.get(profilePage);

        new Profile(driver).checkNotLogginLabelNotExists();
    }


    @Test(testName = "Переход в личный кабинет без авторизации",
            groups = {"authorisation", "negative", "testNow"}
    )
    public void openAccountPageWithoutAuthorisation() {
        driver.get(profilePage);
        new Profile(driver).checkNotLogginLabelExists();
    }


    @Parameters({"registeredUserName", "incorrectPassword"})
    @Test(testName = "Корректный логин и некорректный пароль",
            groups = {"authorisation", "negative"}
    )
    public void correctUsernameAndIncorrectPassword(String registeredUserName, String incorrectPassword) {
        driver.get(bookStorePage);
        new BookStore(driver).clickLoginButton();
        Login login = new Login(driver);

        login.setUserName(registeredUserName)
                .setPassword(incorrectPassword)
                .clickLogin();

        login.checkExistsAndAssertErrorText("Invalid username or password!");
    }


    @Parameters({"unregisteredUserName", "registeredUserPassword"})
    @Test(testName = "Незарегистрированое имя пользователя", groups = {"authorisation", "negative"})
    public void unregisteredUsername(String unregisteredUserName, String registeredUserPassword) {
        driver.get(bookStorePage);
        new BookStore(driver).clickLoginButton();
        Login login = new Login(driver);

        login.setUserName(unregisteredUserName)
                .setPassword(registeredUserPassword)
                .clickLogin();

        login.checkExistsAndAssertErrorText("Invalid username or password!");
    }


    @Parameters({"registeredUserPassword"})
    @Test(testName = "Пустое поле имени пользователя", groups = {"authorisation", "negative"})
    public void emptyUsernameField(String registeredUserPassword) {
        driver.get(bookStorePage);
        new BookStore(driver).clickLoginButton();

        Login login = new Login(driver);

        login.setPassword(registeredUserPassword)
                .clickLogin();
        login.checkInputFieldHasInvalidStatus("UserName");
    }


    @Parameters({"registeredUserName"})
    @Test(testName = "Пустое поле пароль", groups = {"authorisation", "negative"})
    public void emptyPasswordField(String registeredUserName) {
        driver.get(bookStorePage);
        new BookStore(driver).clickLoginButton();
        Login login = new Login(driver);

        login.setUserName(registeredUserName)
                .clickLogin();
        login.checkInputFieldHasInvalidStatus("Password");
    }


}
