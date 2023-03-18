package lib.iu.android;

import io.appium.java_client.AppiumDriver;
import lib.iu.MyListsPageObject;

public class AndroidMyListPageObject extends MyListsPageObject {

    static {
        FOLDER_NAME_TPL_XPATH = "xpath://*[contains(@text,'%s')]";
        ARTICLE_TITLE_TPL_XPATH = "xpath://*[contains(@text,'%s')]";
    }

    public AndroidMyListPageObject(AppiumDriver driver) {
        super(driver);
    }
}
