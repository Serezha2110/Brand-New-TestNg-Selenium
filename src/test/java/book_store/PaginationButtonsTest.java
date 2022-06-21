package book_store;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BookStore;
import utils.DriverManager;
import utils.General;

public class PaginationButtonsTest {

    String bookStorePage = "https://demoqa.com/books";

    WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = DriverManager.initDriver();
        General.goToPage(bookStorePage);
    }


    @Test(testName = "Перемещение между страницами используя кнопки Previous и Next", groups = {"PaginationButtonTest", "positive"})
    public void movingBetweenPagesUsingPreviousAndNextButtons() {
        BookStore bookStore = new BookStore(driver);

        bookStore.setMaxBookCountSelectorByValue("5")
                .checkTotalPagesCount("2")
                .checkCurrentPageIs("1")
                .checkPaginationButtonIsEnabled("previous", false)
                .checkPaginationButtonIsEnabled("next", true)
                .clickNext()
                .checkCurrentPageIs("2")
                .checkPaginationButtonIsEnabled("previous", true)
                .checkPaginationButtonIsEnabled("next", false)
                .clickPrevious()
                .checkCurrentPageIs("1")
                .checkPaginationButtonIsEnabled("previous", false)
                .checkPaginationButtonIsEnabled("next", true);
    }


    @Test(testName = "Проверка что кнопки пагинации неактивны если общее количество страниц = 1", groups = {"PaginationButtonTest", "positive"})
    public void checkBothButtonsDisabled() {
        BookStore bookStore = new BookStore(driver);

        bookStore.setMaxBookCountSelectorByValue("100")
                .checkTotalPagesCount("1")
                .checkPaginationButtonIsEnabled("previous", false)
                .checkPaginationButtonIsEnabled("next", false);
    }


    @Test(testName = "Проверка отсутствия поведения при клике на неактивные кнопки пагинации", groups = {"PaginationButtonTest", "positive"})
    public void checkAbsenceOfBehaviorWhenClickOnDisabledButtons() {
        BookStore bookStore = new BookStore(driver);

        bookStore.setMaxBookCountSelectorByValue("100")
                .checkTotalPagesCount("1")
                .checkCurrentPageIs("1")
                .checkPaginationButtonIsEnabled("previous", false)
                .checkPaginationButtonIsEnabled("next", false)
                .clickNext()
                .checkCurrentPageIs("1")
                .clickPrevious()
                .checkCurrentPageIs("1");
    }


    @AfterMethod(alwaysRun = true)
    public void tearDownMethod() {
        DriverManager.dropWebDriver();
    }

}
