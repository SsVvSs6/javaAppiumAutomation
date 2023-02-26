package lib;

import lib.iu.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FirstTest extends CoreTestCase{

    private String searchFieldXPath = "//*[contains(@text,'Search Wikipedia')]";
    private String skipButtonXPath = "//*[contains(@text,'SKIP')]";
    private String javaFoundElementXPath = "//*[contains(@text,'Object-oriented programming language')]";
    private String crossId = "org.wikipedia:id/search_close_btn";
    private String javaSearchText = "Java";
    private String javaArticleDescriptionText = "Object-oriented programming language";

    private MainPageObject mainPageObject;
    private SearchPageObject searchPageObject;

    protected void setUp() throws Exception {
        super.setUp();

        mainPageObject = new MainPageObject(driver);
        searchPageObject = new SearchPageObject(driver);
    }

    @Test
    public void testSaveTwoArticlesAndDeleteOne() {
        String listName = "Learning programming";
        String javaSecondFoundElementXPath = "//*[contains(@text,'Island in Indonesia')]";
        mainPageObject.waitForElementAndClick(By.xpath(skipButtonXPath), "Cannot find Skip button", 5);
        mainPageObject.waitForElementAndClick(By.xpath(searchFieldXPath), "Cannot find 'Search Wikipedia' input on Menu page", 5);
        mainPageObject.waitForElementAndSendKeys(By.xpath(searchFieldXPath), "Java", "Cannot find search input", 5);
        mainPageObject.waitForElementAndClick(By.xpath(javaFoundElementXPath),
                "Cannot find 'Object-oriented programming language' topic searching by Java",
                15);
        mainPageObject.saveArticleToTheNewListIfNoListsExists(listName);
        mainPageObject.waitForElementAndClick(By.xpath("//*[@content-desc='Navigate up']"),
                "Cannot find 'X' link", 5);
        mainPageObject.waitForElementAndClick(By.xpath(javaSecondFoundElementXPath),
                "Cannot find 'Island in Indonesia' topic searching by Java",
                15);
        mainPageObject.saveArticleToExistList(By.xpath("//*[@text='" + listName + "']"));
        mainPageObject.waitForElementAndClick(By.xpath("//*[@content-desc='Navigate up']"),
                "Cannot find 'X' link", 5);
        mainPageObject.waitForElementAndClick(By.xpath("//*[@class='android.widget.ImageButton']"),
                "Cannot find 'X' link", 5);
        mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/nav_tab_reading_lists"),
                "Cannot find navigation button to My lists", 5);
        mainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text,'" + listName + "')]"),
                "Cannot find saved list", 5);
        mainPageObject.swipeElementToLeft(By.xpath(javaFoundElementXPath), "Cannot find saved article");
        mainPageObject.waitForElementNotPresent(By.xpath(javaFoundElementXPath), "Cannot delete saved article", 5);
        mainPageObject.waitForElementAndClick(By.xpath(javaSecondFoundElementXPath),
                "Cannot find 'Island in Indonesia' in the list of saved", 15);
        String titleOfArticle = mainPageObject.waitForElementAndGetAttribute(By.xpath("//android.view.View[@instance=2]"), "text",
                "Cannot find title of article", 15);
        assertEquals("Article title is not correct", "Java", titleOfArticle);
    }

    @Test
    public void testSearchFieldTextOnMainPage() {
        mainPageObject.waitForElementAndClick(By.xpath(skipButtonXPath), "Cannot find Skip button", 5);
        mainPageObject.assertElementHasText(By.xpath(searchFieldXPath), "Search Wikipedia",
                "Search input text is incorrect");
    }

    @Test
    public void testSearchFieldTextOnSearchPage() {
        mainPageObject.waitForElementAndClick(By.xpath(skipButtonXPath), "Cannot find Skip button", 5);
        mainPageObject.waitForElementAndClick(By.xpath(searchFieldXPath), "Cannot find 'Search Wikipedia' input on Menu page", 5);
        mainPageObject.assertElementHasText(By.xpath(searchFieldXPath), "Search Wikipedia",
                "Search input text is incorrect");
    }

    @Test
    public void testSearchAndCancel() {
        String resultsId = "org.wikipedia:id/search_results_display";
        mainPageObject.waitForElementAndClick(By.xpath(skipButtonXPath), "Cannot find Skip button", 5);
        mainPageObject.waitForElementAndClick(By.xpath(searchFieldXPath), "Cannot find 'Search Wikipedia' input on Menu page", 5);
        mainPageObject.waitForElementAndSendKeys(By.xpath(searchFieldXPath), "Java", "Cannot find search input", 5);
        mainPageObject.waitForElementPresent(By.id(resultsId), "Cannot find search results", 15);
        mainPageObject.waitForElementAndClick(By.id(crossId),"Cannot find 'X' to cancel search", 5);
        boolean isResultsDisplayed = mainPageObject.waitForElementNotPresent(By.id(resultsId),
                "Search results are still displayed", 15);
        assertTrue("Search results are still displayed", isResultsDisplayed);
    }

    @Test
    public void testWordInResultList() {
        String searchText = "Java";
        mainPageObject.waitForElementAndClick(By.xpath(skipButtonXPath), "Cannot find Skip button", 5);
        mainPageObject.waitForElementAndClick(By.xpath(searchFieldXPath), "Cannot find 'Search Wikipedia' input on Menu page", 5);
        mainPageObject.waitForElementAndSendKeys(By.xpath(searchFieldXPath), searchText, "Cannot find search input", 5);
        mainPageObject.waitForElementPresent(By.id("org.wikipedia:id/page_list_item_title"), "Result is not displayed", 15);
        List<WebElement> resultsList = driver.findElements(By.id("org.wikipedia:id/page_list_item_title"));
        for (int i = 0; i < resultsList.size(); i++) {
            assertTrue("Text is not contains '" + searchText + "'",
                    mainPageObject.checkElementContainsText(resultsList.get(i).getAttribute("text"), searchText));
        }
    }

    @Test
    public void testTitleIsDisplayed() {
        mainPageObject.waitForElementAndClick(By.xpath(skipButtonXPath), "Cannot find Skip button", 5);
        mainPageObject.waitForElementAndClick(By.xpath(searchFieldXPath), "Cannot find 'Search Wikipedia' input on Menu page", 5);
        mainPageObject.waitForElementAndSendKeys(By.xpath(searchFieldXPath), "Java", "Cannot find search input", 5);
        mainPageObject.waitForElementAndClick(By.xpath(javaFoundElementXPath),
                "Cannot find 'Object-oriented programming language' topic searching by Java",
                15);
        mainPageObject.assertElementPresent(By.xpath("//android.view.View[@instance=2]"),
                "Title is not found");
    }
}
