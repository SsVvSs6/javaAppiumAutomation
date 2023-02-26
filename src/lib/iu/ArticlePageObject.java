package lib.iu;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String ARTICLE_TITLE_XPATH = "//android.view.View[@instance=2]";
    private static final String FOOTER_ELEMENT_XPATH = "//*[contains(@text,'View article in browser')]";
    private static final String SAVE_BUTTON_XPATH = "//*[@content-desc='Save']";
    private static final String ADD_TO_LIST_BUTTON_ID = "org.wikipedia:id/snackbar_action";
    private static final String FOLDER_NAME_INPUT_ID = "org.wikipedia:id/text_input";
    private static final String OK_BUTTON_XPATH = "android:id/button1";
    private static final String CLOSE_BUTTON_XPATH = "//*[@content-desc='Navigate up']";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(By.xpath(ARTICLE_TITLE_XPATH),
                        "Cannot find article title on page", 15);
    }

    public String getArticleTitle() {
        return waitForTitleElement().getAttribute("text");
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(By.xpath(FOOTER_ELEMENT_XPATH),
                "Cannot find the end of the article", 20);
    }

    //аналог saveArticleToTheNewListIfNoListsExists
    public void addFirstArticleToMyFirstList(String listName) {
        this.startSavingArticleToList();
        this.waitForElementAndClear(By.id(FOLDER_NAME_INPUT_ID),
                "Cannot find input to set name of list", 5);
        this.waitForElementAndSendKeys(By.id(FOLDER_NAME_INPUT_ID), listName,
                "Cannot put text to list field name", 5);
        this.waitForElementAndClick(By.id(OK_BUTTON_XPATH), "Cannot find 'OK' button", 5);
    }

    public void startSavingArticleToList() {
        waitForElementAndClick(By.xpath(SAVE_BUTTON_XPATH),
                "Cannot find option to add article to reading list", 5);
        waitForElementAndClick(By.id(ADD_TO_LIST_BUTTON_ID),
                "Cannot find 'Add to list' tip overlay", 5);
    }

    public void closeArticle() {
        this.waitForElementAndClick(By.xpath(CLOSE_BUTTON_XPATH), "Cannot find 'X' link", 5);
    }
}
