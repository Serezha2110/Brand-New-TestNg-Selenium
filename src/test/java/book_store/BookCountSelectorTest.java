package book_store;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BookStore;
import utils.DriverManager;
import utils.General;

public class BookCountSelectorTest {

    String bookStorePage = "https://demoqa.com/books";

    WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = DriverManager.initDriver();
        General.goToPage(bookStorePage);
    }


    @Test(testName = "Максимальное количество книг на странице = 5", groups = {"countOfBooksSelector", "positive"})
    public void maxCountOfBooks5() {
        new BookStore(driver)
                .setMaxBookCountSelectorByValue("10")
                .checkBookCount(8)
                .checkTotalPagesCount("1")
                .setMaxBookCountSelectorByValue("5")
                .checkBookCount(5)
                .checkRowsCount(5)
                .checkTotalPagesCount("2");
    }


    @Test(testName = "Максимальное количество книг на странице = 10", groups = {"countOfBooksSelector", "positive"})
    public void maxCountOfBooks10() {
        new BookStore(driver)
                .setMaxBookCountSelectorByValue("5")
                .checkBookCount(5)
                .checkRowsCount(5)
                .checkTotalPagesCount("2")
                .setMaxBookCountSelectorByValue("10")
                .checkBookCount(8)
                .checkRowsCount(10)
                .checkTotalPagesCount("1");
    }


    @Test(testName = "Максимальное количество книг на странице = 20", groups = {"countOfBooksSelector", "positive"})
    public void maxCountOfBooks20() {
        new BookStore(driver)
                .setMaxBookCountSelectorByValue("5")
                .checkBookCount(5)
                .checkRowsCount(5)
                .checkTotalPagesCount("2")
                .setMaxBookCountSelectorByValue("20")
                .checkBookCount(8)
                .checkRowsCount(20)
                .checkTotalPagesCount("1");
    }


    @Test(testName = "Максимальное количество книг на странице = 25", groups = {"countOfBooksSelector", "positive"})
    public void maxCountOfBooks25() {
        new BookStore(driver)
                .setMaxBookCountSelectorByValue("5")
                .checkBookCount(5)
                .checkRowsCount(5)
                .checkTotalPagesCount("2")
                .setMaxBookCountSelectorByValue("25")
                .checkBookCount(8)
                .checkRowsCount(25)
                .checkTotalPagesCount("1");
    }


    @Test(testName = "Максимальное количество книг на странице = 50", groups = {"countOfBooksSelector", "positive"})
    public void maxCountOfBooks50() {
        new BookStore(driver)
                .setMaxBookCountSelectorByValue("5")
                .checkBookCount(5)
                .checkRowsCount(5)
                .checkTotalPagesCount("2")
                .setMaxBookCountSelectorByValue("50")
                .checkBookCount(8)
                .checkRowsCount(50)
                .checkTotalPagesCount("1");
    }


    @Test(testName = "Максимальное количество книг на странице = 100", groups = {"countOfBooksSelector", "positive"})
    public void maxCountOfBooks100() {
        new BookStore(driver)
                .setMaxBookCountSelectorByValue("5")
                .checkBookCount(5)
                .checkRowsCount(5)
                .checkTotalPagesCount("2")
                .setMaxBookCountSelectorByValue("100")
                .checkBookCount(8)
                .checkRowsCount(100)
                .checkTotalPagesCount("1");
    }


    @AfterMethod(alwaysRun = true)
    public void tearDownMethod() {
        DriverManager.dropWebDriver();
    }


}
