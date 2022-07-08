package book_store;

import org.testng.annotations.Test;
import pages.BookStore;
import utils.BaseTest;

public class PaginationButtonsTest extends BaseTest {

    String bookStorePage = "https://demoqa.com/books";

    @Test(testName = "Перемещение между страницами используя кнопки Previous и Next", groups = {"PaginationButtonTest", "positive"})
    public void movingBetweenPagesUsingPreviousAndNextButtons() {
        driver.get(bookStorePage);
        new BookStore(driver).setMaxBookCountSelectorByValue("5")
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
        driver.get(bookStorePage);
        new BookStore(driver).setMaxBookCountSelectorByValue("100")
                .checkTotalPagesCount("1")
                .checkPaginationButtonIsEnabled("previous", false)
                .checkPaginationButtonIsEnabled("next", false);
    }


    @Test(testName = "Проверка отсутствия поведения при клике на неактивные кнопки пагинации", groups = {"PaginationButtonTest", "positive"})
    public void checkAbsenceOfBehaviorWhenClickOnDisabledButtons() {
        driver.get(bookStorePage);
        new BookStore(driver).setMaxBookCountSelectorByValue("100")
                .checkTotalPagesCount("1")
                .checkCurrentPageIs("1")
                .checkPaginationButtonIsEnabled("previous", false)
                .checkPaginationButtonIsEnabled("next", false)
                .clickNext()
                .checkCurrentPageIs("1")
                .clickPrevious()
                .checkCurrentPageIs("1");
    }


}
