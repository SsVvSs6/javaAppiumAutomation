package tests;

import lib.CoreTestCase;
import lib.iu.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    private SearchPageObject searchPageObject;
    private String javaSearchText = "Java";
    private String javaArticleDescriptionText = "Object-oriented programming language";

    protected void setUp() throws Exception {
        super.setUp();

        searchPageObject = new SearchPageObject(driver);
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
}
