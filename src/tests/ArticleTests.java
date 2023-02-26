package tests;

import lib.CoreTestCase;
import lib.iu.*;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {

    private SearchPageObject searchPageObject;
    private ArticlePageObject articlePageObject;
    private String javaSearchText = "Java";
    private String javaArticleDescriptionText = "Object-oriented programming language";
    private String appiumSearchText = "Appium";
    private String appiumArticleDescriptionText = "Automation for Apps";

    protected void setUp() throws Exception {
        super.setUp();

        searchPageObject = new SearchPageObject(driver);
        articlePageObject = new ArticlePageObject(driver);
    }

    @Test
    public void testCompareArticleTitle() {
        searchPageObject.clickSkipButton();
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(javaSearchText);
        searchPageObject.clickByArticleWithSubstring(javaArticleDescriptionText);
        String articleTitle = articlePageObject.getArticleTitle();
        assertEquals("Title is unexpected", "Java (programming language)", articleTitle);
    }

    @Test
    public void testSwipeArticle() {
        searchPageObject.clickSkipButton();
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(appiumSearchText);
        searchPageObject.clickByArticleWithSubstring(appiumArticleDescriptionText);
        articlePageObject.waitForTitleElement();
        articlePageObject.swipeToFooter();
    }
}
