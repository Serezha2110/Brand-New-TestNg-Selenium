package book_store;

import org.testng.annotations.*;
import pages.BookStore;
import utils.BaseTest;

public class BookCountSelectorTest extends BaseTest {

    String bookStorePage = "https://demoqa.com/books";

    @Test(testName = "Максимальное количество книг на странице = 5",
            groups = {"countOfBooksSelector", "positive", "testNow"})
    public void maxCountOfBooks5() {
        driver.get(bookStorePage);
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
        driver.get(bookStorePage);
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
        driver.get(bookStorePage);
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
        driver.get(bookStorePage);
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
        driver.get(bookStorePage);
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
        driver.get(bookStorePage);
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



}
