package authorisation;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.BookStore;
import pages.Login;
import pages.Profile;
import utils.DriverManager;
import utils.General;

public class AuthorisationTest {

    WebDriver driver;
    String bookStorePage = "https://demoqa.com/books";
    String profilePage = "https://demoqa.com/profile";

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = DriverManager.initDriver();
        General.goToPage(bookStorePage);
    }

    @Parameters({"registeredUserName", "registeredUserPassword"})
    @Test(testName = "Корректная авторизация и переход в личный кабинет", groups = {"authorisation", "positive"})
    public void correctAccessToProfile(String userName, String password) {
        new BookStore(driver).clickLoginButton();
        Login login = new Login(driver);

        login.setUserName(userName)
                .setPassword(password)
                .clickLogin();
        General.waitAndCheckCurrentURL(bookStorePage);

        General.goToPage(profilePage);

        new Profile(driver).checkNotLogginLabelNotExists();
    }


    @Test(testName = "Переход в личный кабинет без авторизации", groups = {"authorisation", "negative", "testNow"})
    public void authorisationWithCorrectCredentials() {
        General.goToPage(profilePage);
        System.out.println(1231231212);
        new Profile(driver).checkNotLogginLabelExists();
    }


    @Parameters({"registeredUserName", "incorrectPassword"})
    @Test(testName = "Корректный логин и некорректный пароль", groups = {"authorisation", "negative"})
    public void correctUsernameAndIncorrectPassword(String registeredUserName, String incorrectPassword) {
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
        new BookStore(driver).clickLoginButton();

        Login login = new Login(driver);

        login.setPassword(registeredUserPassword)
                .clickLogin();
        login.checkInputFieldHasInvalidStatus("UserName");
    }


    @Parameters({"registeredUserName"})
    @Test(testName = "Пустое поле пароль", groups = {"authorisation", "negative"})
    public void emptyPasswordField(String registeredUserName) {
        new BookStore(driver).clickLoginButton();

        Login login = new Login(driver);

        login.setUserName(registeredUserName)
                .clickLogin();
        login.checkInputFieldHasInvalidStatus("Password");
    }


    @AfterMethod(alwaysRun = true)
    public void tearDownMethod() {
        DriverManager.dropWebDriver();
    }


}
