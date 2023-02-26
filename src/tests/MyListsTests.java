package tests;

import lib.CoreTestCase;
import lib.iu.*;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    private SearchPageObject searchPageObject;
    private ArticlePageObject articlePageObject;
    private NavigationUI navigationUI;
    private MyListsPageObject myListsPageObject;
    private String javaSearchText = "Java";
    private String javaArticleDescriptionText = "Object-oriented programming language";

    protected void setUp() throws Exception {
        super.setUp();

        searchPageObject = new SearchPageObject(driver);
        articlePageObject = new ArticlePageObject(driver);
        navigationUI = new NavigationUI(driver);
        myListsPageObject = new MyListsPageObject(driver);
    }

    @Test
    public void testSaveFirstArticleToMyList() {
        String listName = "Learning programming";
        searchPageObject.clickSkipButton();
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(javaSearchText);
        searchPageObject.clickByArticleWithSubstring(javaArticleDescriptionText);
        articlePageObject.waitForTitleElement();
        articlePageObject.addFirstArticleToMyFirstList(listName);
        articlePageObject.closeArticle();
        navigationUI.clickBackIcon();
        navigationUI.clickMyLists();
        myListsPageObject.openFolderByName(listName);
        myListsPageObject.swipeByArticleToDelete(javaArticleDescriptionText);
    }

    @Test
    public void testSaveTwoArticlesAndDeleteOne() {
        String listName = "Learning programming";
        String javaSecondFoundElementText = "Island in Indonesia";
        searchPageObject.clickSkipButton();
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(javaSearchText);
        searchPageObject.clickByArticleWithSubstring(javaArticleDescriptionText);
        articlePageObject.waitForTitleElement();
        articlePageObject.addFirstArticleToMyFirstList(listName);
        articlePageObject.closeArticle();
        searchPageObject.clickByArticleWithSubstring(javaSecondFoundElementText);
        articlePageObject.addArticleToExistMyList(listName);
        articlePageObject.closeArticle();
        navigationUI.clickBackIcon();
        navigationUI.clickMyLists();
        myListsPageObject.openFolderByName(listName);
        myListsPageObject.swipeByArticleToDelete(javaArticleDescriptionText);
        myListsPageObject.openSavedArticle(javaSecondFoundElementText);
        String titleOfArticle = articlePageObject.getArticleTitle();
        assertEquals("Article title is not correct", javaSearchText, titleOfArticle);
    }
}
