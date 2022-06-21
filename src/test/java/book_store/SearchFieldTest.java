package book_store;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BookStore;
import utils.DriverManager;
import utils.General;

public class SearchFieldTest {

    String bookStorePage = "https://demoqa.com/books";

    WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = DriverManager.initDriver();
        General.goToPage(bookStorePage);
    }


    @Test(testName = "Поиск по названию книги", groups = {"search", "positive"})
    public void searchByFullTitle() {
        new BookStore(driver)
                .checkBookCount(8)
                .setSearchRequest("Speaking JavaScript")
                .checkBookCount(1)
                .checkAllBooksOnPageContainsPhraseInTitle("Speaking JavaScript");
    }


    @Test(testName = "Поиск по фразе из названия книги", groups = {"search", "positive"})
    public void searchByPhraseInTitle() {
        new BookStore(driver)
                .checkBookCount(8)
                .setSearchRequest("JavaScript")
                .checkBookCount(4)
                .checkAllBooksOnPageContainsPhraseInTitle("JavaScript");
    }


    @Test(testName = "Поиск по автору", groups = {"search", "positive"})
    public void searchByAuthor() {
        new BookStore(driver)
                .checkBookCount(8)
                .setSearchRequest("Kyle Simpson")
                .checkBookCount(1)
                .checkAllBooksOnPageHaveAnAuthor("Kyle Simpson");
    }


    @Test(testName = "Поиск по издателю", groups = {"search", "positive"})
    public void searchByPublisher() {
        new BookStore(driver)
                .checkBookCount(8)
                .setSearchRequest("O'Reilly Media")
                .checkBookCount(6)
                .checkAllBooksOnPageHaveAPublisher("O'Reilly Media");
    }


    @Test(testName = "Поиск несуществующей книги", groups = {"search", "negative"})
    public void noBooksSearch() {
        new BookStore(driver)
                .checkBookCount(8)
                .setSearchRequest("Java is the best of the best")
                .checkBookCount(0);
    }


    @AfterMethod(alwaysRun = true)
    public void tearDownMethod() {
        DriverManager.dropWebDriver();
    }


}
