package lib.iu.ios;

import io.appium.java_client.AppiumDriver;
import lib.iu.MyListsPageObject;

public class iOSMyListsPageObject extends MyListsPageObject {
    static {
        ARTICLE_TITLE_TPL_XPATH = "xpath://XCUIElementTypeLink[contains(@name,'%s')]";
    }

    public iOSMyListsPageObject(AppiumDriver driver) {
        super(driver);
    }
}
