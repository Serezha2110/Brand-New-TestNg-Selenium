package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.DriverManager;
import utils.General;

import java.util.List;
import java.util.Objects;

public class BookStore {

    WebDriver driver;

    By loginButton = By.cssSelector("#login");
    By searchField = By.cssSelector("#searchBox");
    By row = By.cssSelector(".rt-tr-group");
    By bookTitle = By.cssSelector(".mr-2 a");
    By author = By.cssSelector(".rt-tr-group div div:nth-child(3)");
    By publisher = By.cssSelector(".rt-tr-group div div:nth-child(4)");
    By maxBookCountSelector = By.cssSelector(".select-wrap select");
    By totalPagesCounter = By.cssSelector(".-totalPages");
    By jumpToPageInput = By.cssSelector(".-pageJump input");

    By nextButton = By.cssSelector(".-next button");
    By previousButton = By.cssSelector(".-previous button");

    public BookStore(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLoginButton() {
        General.fluentWaitElement(loginButton).click();
    }

    public BookStore setSearchRequest(String request) {
        General.fluentWaitElement(searchField).sendKeys(request);
        return this;
    }

    public BookStore checkBookCount(int expectedBookCount) {
        try {
            General.fluentWaitElement(bookTitle);
        } catch (TimeoutException e) {
            if (expectedBookCount == 0) {
                return this;
            }
        }
        int actualBookCount = driver.findElements(bookTitle).size();
        Assert.assertEquals(actualBookCount, expectedBookCount
                , "Unexpected actual count of books. Actual: " + actualBookCount + " Expected: " + expectedBookCount);
        return this;
    }


    public BookStore checkAllBooksOnPageContainsPhraseInTitle(String phrase) {
        General.fluentWaitElement(bookTitle);
        List<WebElement> bookTitles = driver.findElements(bookTitle);
        bookTitles.forEach(o -> Assert.assertTrue(o.getText().contains(phrase)
                , "Book on page '" + bookTitle + "' does not have phrase '" + phrase + "' in title"));
        return this;
    }


    public BookStore checkAllBooksOnPageHaveAnAuthor(String author) {
        General.fluentWaitElement(this.author);

        List<WebElement> bookTitles = driver.findElements(bookTitle);
        List<WebElement> bookAuthors = driver.findElements(this.author);
        for (int i = 0; i < bookTitles.size(); i++) {
            Assert.assertTrue(bookAuthors.get(i).getText().contains(author)
                    , "Book on page '" + bookTitles.get(i) + "' does not have author '" + author + "'");
        }
        return this;
    }


    public BookStore checkAllBooksOnPageHaveAPublisher(String publisher) {
        General.fluentWaitElement(this.publisher);

        List<WebElement> bookTitles = driver.findElements(bookTitle);
        List<WebElement> bookPublishers = driver.findElements(this.publisher);
        for (int i = 0; i < bookTitles.size(); i++) {
            Assert.assertTrue(bookPublishers.get(i).getText().contains(publisher)
                    , "Book on page '" + bookTitles.get(i) + "' does not have publisher '" + publisher + "'");
        }
        return this;
    }


    public BookStore setMaxBookCountSelectorByValue(String value) {
        General.fluentWaitElement(maxBookCountSelector);
        WebElement selector = driver.findElement(maxBookCountSelector);
        Select select = new Select(selector);
        select.selectByValue(value);
        return this;
    }


    public BookStore checkRowsCount(int expectedRowsCount) {
        General.fluentWaitElement(row);
        int actualRowsCount = driver.findElements(row).size();
        Assert.assertEquals(actualRowsCount, expectedRowsCount
                , "Unexpected count of rows. Actual: " + actualRowsCount + " Expected: " + expectedRowsCount);
        return this;
    }


    public BookStore checkTotalPagesCount(String expectedTotalPagesCount) {
        General.fluentWaitElement(totalPagesCounter);
        String actualTotalPagesCount = driver.findElement(totalPagesCounter).getText();
        Assert.assertEquals(actualTotalPagesCount, expectedTotalPagesCount
                , "Unexpected count of pages. Actual: " + actualTotalPagesCount + " Expected: " + expectedTotalPagesCount);
        return this;
    }


    public BookStore clickNext() throws ElementClickInterceptedException {
        try {
            General.fluentWaitElement(nextButton).click();
        } catch (ElementClickInterceptedException e) {
        }
        return this;
    }


    public BookStore clickPrevious() {
        try {
            General.fluentWaitElement(previousButton).click();
        } catch (ElementClickInterceptedException e) {
        }
        return this;
    }


    public BookStore checkCurrentPageIs(String expectedCurrentPage) {
        String actualCurrentPage = General.fluentWaitElement(jumpToPageInput).getAttribute("value");
        Assert.assertEquals(actualCurrentPage, expectedCurrentPage, "Actual current page number is '" + actualCurrentPage + "'");
        return this;
    }


    public BookStore checkPaginationButtonIsEnabled(String button, boolean isEnabled) {
        By elementButton = null;
        if (button.equals("previous")) {
            elementButton = previousButton;
        } else if (button.equals("next")) {
            elementButton = nextButton;
        }
        String buttonActualStatus = General.fluentWaitElement(elementButton).getAttribute("disabled");

        Assert.assertEquals(buttonActualStatus == null, isEnabled
                , "Actual button status is '" + buttonActualStatus + "'");
        return this;
    }


}
