package lib.iu;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String ARTICLE_TITLE_XPATH = "xpath://android.view.View[@instance=2]";
    private static final String FOOTER_ELEMENT_XPATH = "xpath://*[contains(@text,'View article in browser')]";
    private static final String SAVE_BUTTON_XPATH = "xpath://*[@content-desc='Save']";
    private static final String ADD_TO_LIST_BUTTON_ID = "id:org.wikipedia:id/snackbar_action";
    private static final String FOLDER_NAME_INPUT_ID = "id:org.wikipedia:id/text_input";
    private static final String OK_BUTTON_XPATH = "xpath:android:id/button1";
    private static final String CLOSE_BUTTON_XPATH = "xpath://*[@content-desc='Navigate up']";
    private static final String MY_LIST_XPATH = "xpath://*[contains(@text,'%s')]";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(ARTICLE_TITLE_XPATH,
                        "Cannot find article title on page", 15);
    }

    public String getArticleTitle() {
        return waitForTitleElement().getAttribute("text");
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(FOOTER_ELEMENT_XPATH,
                "Cannot find the end of the article", 20);
    }

    public void addFirstArticleToMyFirstList(String listName) {
        this.startSavingArticleToList();
        this.waitForElementAndClear(FOLDER_NAME_INPUT_ID,
                "Cannot find input to set name of list", 5);
        this.waitForElementAndSendKeys(FOLDER_NAME_INPUT_ID, listName,
                "Cannot put text to list field name", 5);
        this.waitForElementAndClick(OK_BUTTON_XPATH, "Cannot find 'OK' button", 5);
    }

    public void addArticleToExistMyList(String listName) {
        this.startSavingArticleToList();
        waitForElementAndClick(String.format(MY_LIST_XPATH, listName),
                "Cannot find created list " + listName, 15);
    }

    public void startSavingArticleToList() {
        waitForElementAndClick(SAVE_BUTTON_XPATH,
                "Cannot find option to add article to reading list", 5);
        waitForElementAndClick(ADD_TO_LIST_BUTTON_ID,
                "Cannot find 'Add to list' tip overlay", 5);
    }

    public void closeArticle() {
        this.waitForElementAndClick(CLOSE_BUTTON_XPATH, "Cannot find 'X' link", 5);
    }

    public void waitForTitleElementWithAssert() {
        this.assertElementPresent(ARTICLE_TITLE_XPATH, "Title is not found");
    }
}
