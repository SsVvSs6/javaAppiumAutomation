package tests;

import lib.CoreTestCase;
import lib.iu.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchTests extends CoreTestCase {

    private SearchPageObject searchPageObject;
    private String javaSearchText = "Java";
    private String javaArticleDescriptionText = "Object-oriented programming language";
    private String searchFieldText = "Search Wikipedia";

    protected void setUp() throws Exception {
        super.setUp();

        searchPageObject = new SearchPageObject(driver);
    }

    @Test
    public void testSearchFieldTextOnMainPage() {
        searchPageObject.clickSkipButton();
        searchPageObject.checkSearchFieldText(searchFieldText);
    }

    @Test
    public void testSearchFieldTextOnSearchPage() {
        searchPageObject.clickSkipButton();
        searchPageObject.initSearchInput();
        searchPageObject.checkSearchFieldText(searchFieldText);
    }

    @Test
    public void testSearch() {
        searchPageObject.clickSkipButton();
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(javaSearchText);
        searchPageObject.waitForSearchResult(javaArticleDescriptionText);
    }

    @Test
    public void testCancelSearch() {
        searchPageObject.clickSkipButton();
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(javaSearchText);
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelButton();
        searchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        String searchLine = "Linkin Park Diskography";
        searchPageObject.clickSkipButton();
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchLine);
        int amountOfSearchedResults = searchPageObject.getAmountOfFoundArticles();
        assertTrue("We found too few results", amountOfSearchedResults > 0);
    }

    @Test
    public void testAmountOfEmptySearch() {
        String searchLine = "fghfjfj";
        searchPageObject.clickSkipButton();
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchLine);
        searchPageObject.waitForEmptyResultLabel();
        searchPageObject.assertThereIsNoResultsOfSearch();
    }

    @Test
    public void testSearchAndCancel() {
        searchPageObject.clickSkipButton();
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(javaSearchText);
        searchPageObject.waitForSearchResultsPresent();
        searchPageObject.clickCancelButton();
        boolean isResultsDisplayed = searchPageObject.waitForSearchResultsNotPresent();
        assertTrue("Search results are still displayed", isResultsDisplayed);
    }

    @Test
    public void testWordInResultList() {
        searchPageObject.clickSkipButton();
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(javaSearchText);
        searchPageObject.waitForSearchResultsPresent();
        List<WebElement> resultsList = searchPageObject.getResultsList();
        for (int i = 0; i < resultsList.size(); i++) {
            assertTrue("Text is not contains '" + javaSearchText + "'",
                    searchPageObject.checkSearchResultElementContainsText(resultsList, javaSearchText, i));
        }
    }
}
