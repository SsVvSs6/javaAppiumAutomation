package lib.iu;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPageObject extends MainPageObject {

    private static final String SKIP_BUTTON_XPATH = "xpath://*[contains(@text,'SKIP')]";
    private static final String SEARCH_INIT_ELEMENT_XPATH = "xpath://*[contains(@text,'Search Wikipedia')]";
    private static final String SEARCH_RESULT_BY_SUBSTRING_TPL_XPATH = "xpath://*[contains(@text,'%s')]";
    private static final String SEARCH_CANCEL_BUTTON_ID = "id:org.wikipedia:id/search_close_btn";
    private static final String SEARCH_RESULT_XPATH = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title']";
    private static final String EMPTY_RESULT_LABEL_XPATH = "xpath://*[@text='No results']";
    private static final String RESULT_ID = "id:org.wikipedia:id/search_results_display";
    private static final String RESULTS_TITLE_ID = "id:org.wikipedia:id/page_list_item_title";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void clickSkipButton() {
        this.waitForElementAndClick(SKIP_BUTTON_XPATH,
                "Cannot find Skip button", 5);
    }

    public void initSearchInput() {
        this.waitForElementPresent(SEARCH_INIT_ELEMENT_XPATH,
                "Cannot find search input after clicking search init element");
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT_XPATH,
                "Cannot find 'Search Wikipedia' input", 5);
    }

    public void typeSearchLine(String searchLine) {
        this.waitForElementAndSendKeys(SEARCH_INIT_ELEMENT_XPATH, searchLine,
                "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring) {
        this.waitForElementPresent(String.format(SEARCH_RESULT_BY_SUBSTRING_TPL_XPATH, substring),
                "Cannot find search result with substring " + substring);
    }

    public void clickByArticleWithSubstring(String substring) {
        this.waitForElementAndClick(String.format(SEARCH_RESULT_BY_SUBSTRING_TPL_XPATH, substring),
                "Cannot find and click search result with substring " + substring, 10);
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON_ID,
                "Cannot find search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON_ID,
                "Search cancel button is still present", 5);
    }

    public void clickCancelButton() {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON_ID,
                "Cannot find and click search cancel button", 5);
    }

    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(SEARCH_RESULT_XPATH,
                "Cannot find anything by request", 15);
        return this.getAmountOfElements(SEARCH_RESULT_XPATH);
    }

    public void waitForEmptyResultLabel() {
        this.waitForElementPresent(EMPTY_RESULT_LABEL_XPATH,
                "Cannot find empty result by request ", 15);
    }

    public void assertThereIsNoResultsOfSearch() {
        this.assertElementNotPresent(SEARCH_RESULT_XPATH, "We've found some elements by request ");
    }

    public void waitForSearchResultsPresent() {
        this.waitForElementPresent(RESULTS_TITLE_ID, "Cannot find search results", 15);
    }

    public boolean waitForSearchResultsNotPresent() {
        return this.waitForElementNotPresent(RESULT_ID, "Cannot find search results", 15);
    }

    public void checkSearchFieldText(String text) {
        this.assertElementHasText(SEARCH_INIT_ELEMENT_XPATH, text, "Search input text is incorrect");
    }

    public List<WebElement> getResultsList() {
        return driver.findElements(this.getLocatorByString(RESULTS_TITLE_ID));
    }

    public boolean checkSearchResultElementContainsText(List<WebElement> resultsList, String searchText, int i) {
        return this.checkElementContainsText(resultsList.get(i).getAttribute("text"), searchText);
    }
}
