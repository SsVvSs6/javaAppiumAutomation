package lib.iu.android;

import io.appium.java_client.AppiumDriver;
import lib.iu.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {

    static {
        ARTICLE_TITLE_XPATH = "xpath://android.view.View[@instance=2]";
        FOOTER_ELEMENT_XPATH = "xpath://*[contains(@text,'View article in browser')]";
        SAVE_BUTTON_XPATH = "xpath://*[@content-desc='Save']";
        ADD_TO_LIST_BUTTON_ID = "id:org.wikipedia:id/snackbar_action";
        FOLDER_NAME_INPUT_ID = "id:org.wikipedia:id/text_input";
        OK_BUTTON_XPATH = "xpath://*[@text='OK']";
        CLOSE_BUTTON_XPATH = "xpath://*[@content-desc='Navigate up']";
        MY_LIST_XPATH = "xpath://*[contains(@text,'%s')]";
    }

    public AndroidArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}
