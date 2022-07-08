package book_store;

import org.testng.annotations.*;
import pages.BookStore;
import utils.BaseTest;

public class SearchFieldTest extends BaseTest {

    String bookStorePage = "https://demoqa.com/books";

    @Test(testName = "Поиск по названию книги", groups = {"search", "positive"})
    public void searchByFullTitle() {
        driver.get(bookStorePage);
        new BookStore(driver)
                .checkBookCount(8)
                .setSearchRequest("Speaking JavaScript")
                .checkBookCount(1)
                .checkAllBooksOnPageContainsPhraseInTitle("Speaking JavaScript");
    }


    @Test(testName = "Поиск по фразе из названия книги", groups = {"search", "positive"})
    public void searchByPhraseInTitle() {
        driver.get(bookStorePage);
        new BookStore(driver)
                .checkBookCount(8)
                .setSearchRequest("JavaScript")
                .checkBookCount(4)
                .checkAllBooksOnPageContainsPhraseInTitle("JavaScript");
    }


    @Test(testName = "Поиск по автору", groups = {"search", "positive"})
    public void searchByAuthor() {
        driver.get(bookStorePage);
        new BookStore(driver)
                .checkBookCount(8)
                .setSearchRequest("Kyle Simpson")
                .checkBookCount(1)
                .checkAllBooksOnPageHaveAnAuthor("Kyle Simpson");
    }


    @Test(testName = "Поиск по издателю", groups = {"search", "positive"})
    public void searchByPublisher() {
        driver.get(bookStorePage);
        new BookStore(driver)
                .checkBookCount(8)
                .setSearchRequest("O'Reilly Media")
                .checkBookCount(6)
                .checkAllBooksOnPageHaveAPublisher("O'Reilly Media");
    }


    @Test(testName = "Поиск несуществующей книги", groups = {"search", "negative"})
    public void noBooksSearch() {
        driver.get(bookStorePage);
        new BookStore(driver)
                .checkBookCount(8)
                .setSearchRequest("Java is the best of the best")
                .checkBookCount(0);
    }


}
