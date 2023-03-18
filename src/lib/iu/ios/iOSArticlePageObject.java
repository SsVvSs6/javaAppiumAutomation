package lib.iu.ios;

import io.appium.java_client.AppiumDriver;
import lib.iu.ArticlePageObject;

public class iOSArticlePageObject extends ArticlePageObject {

    static {
        ARTICLE_TITLE_XPATH = "id:Java (programming language)";
        FOOTER_ELEMENT_XPATH = "id:View article in browser";
        SAVE_BUTTON_XPATH = "id:Save for later";
        CLOSE_BUTTON_XPATH = "id:Back";
        MY_LIST_XPATH = "xpath://*[contains(@text,'%s')]";
    }

    public iOSArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}
