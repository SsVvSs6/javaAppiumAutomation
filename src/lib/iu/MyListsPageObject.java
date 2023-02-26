package lib.iu;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject {

    private static final String FOLDER_NAME_TPL_XPATH = "//*[contains(@text,'%s')]";
    private static final String ARTICLE_TITLE_TPL_XPATH = "//*[contains(@text,'%s')]";

    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void openFolderByName(String listName) {
        this.waitForElementAndClick(By.xpath(String.format(FOLDER_NAME_TPL_XPATH, listName)),
                "Cannot find folder by name " + listName, 5);
    }

    public void swipeByArticleToDelete(String articleTitle) {
        this.waitForArticleToAppearByTitle(articleTitle);
        this.swipeElementToLeft(By.xpath(String.format(ARTICLE_TITLE_TPL_XPATH, articleTitle)),
                "Cannot find saved article");
        this.waitForArticleToDisappearByTitle(articleTitle);
    }

    public void waitForArticleToAppearByTitle(String articleTitle) {
        this.waitForElementPresent(By.xpath(String.format(ARTICLE_TITLE_TPL_XPATH, articleTitle)),
                "Cannot find saved article " + articleTitle, 15);
    }

    public void waitForArticleToDisappearByTitle(String articleTitle) {
        this.waitForElementNotPresent(By.xpath(String.format(ARTICLE_TITLE_TPL_XPATH, articleTitle)),
                "Cannot delete saved article " + articleTitle, 15);
    }
}
