package lib.iu;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String ARTICLE_TITLE_XPATH;
    protected static String FOOTER_ELEMENT_XPATH;
    protected static String SAVE_BUTTON_XPATH;
    protected static String ADD_TO_LIST_BUTTON_ID;
    protected static String FOLDER_NAME_INPUT_ID;
    protected static String OK_BUTTON_XPATH;
    protected static String CLOSE_BUTTON_XPATH ;
    protected static String MY_LIST_XPATH;

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(ARTICLE_TITLE_XPATH,
                        "Cannot find article title on page", 15);
    }

    public String getArticleTitle() {
        if (Platform.getInstance().isAndroid()) {
            return waitForTitleElement().getAttribute("text");
        } else {
            return waitForTitleElement().getAttribute("name");
        }
    }

    public void swipeToFooter() {
        if(Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(FOOTER_ELEMENT_XPATH,
                    "Cannot find the end of the article", 40);
        } else {
            this.swipeUpTillElementAppear(FOOTER_ELEMENT_XPATH,
                    "Cannot find the end of the article", 40);
        }
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

    public void addArticleToSavedList() {
        this.waitForElementAndClick(ADD_TO_LIST_BUTTON_ID,
                "Cannot find option to add article to reding list", 5);
    }

    public void closeArticle() {
        this.waitForElementAndClick(CLOSE_BUTTON_XPATH, "Cannot find 'X' link", 5);
    }

    public void waitForTitleElementWithAssert() {
        this.assertElementPresent(ARTICLE_TITLE_XPATH, "Title is not found");
    }
}
