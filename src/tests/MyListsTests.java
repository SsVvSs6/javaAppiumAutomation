package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.iu.*;
import lib.iu.factories.ArticlePageObjectFactory;
import lib.iu.factories.MyListsPageObjectFactory;
import lib.iu.factories.NavigationUIFactory;
import lib.iu.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    private SearchPageObject searchPageObject;
    private ArticlePageObject articlePageObject;
    private NavigationUI navigationUI;
    private MyListsPageObject myListsPageObject;
    private String javaSearchText = "Java";
    private String javaArticleDescriptionText = "Object-oriented programming language";
    private String listName = "Learning programming";

    protected void setUp() throws Exception {
        super.setUp();

        searchPageObject = SearchPageObjectFactory.get(driver);
        articlePageObject = ArticlePageObjectFactory.get(driver);
        navigationUI = NavigationUIFactory.get(driver);
        myListsPageObject = MyListsPageObjectFactory.get(driver);
    }

    @Test
    public void testSaveFirstArticleToMyList() {
        searchPageObject.clickSkipButton();
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(javaSearchText);
        searchPageObject.clickByArticleWithSubstring(javaArticleDescriptionText);
        //articlePageObject.waitForTitleElement();

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addFirstArticleToMyFirstList(listName);
        } else {
            articlePageObject.addArticleToSavedList();
        }

        articlePageObject.closeArticle();
        navigationUI.clickBackIcon();
        navigationUI.clickMyLists();

        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.openFolderByName(listName);
        }

        myListsPageObject.swipeByArticleToDelete(javaArticleDescriptionText);
    }

    @Test
    public void testSaveTwoArticlesAndDeleteOne() {
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
