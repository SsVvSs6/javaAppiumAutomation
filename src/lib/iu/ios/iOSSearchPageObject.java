package lib.iu.ios;

import io.appium.java_client.AppiumDriver;
import lib.iu.SearchPageObject;

public class iOSSearchPageObject extends SearchPageObject {

    static {
        SKIP_BUTTON_XPATH = "xpath://*[contains(@text,'SKIP')]";
        SEARCH_INIT_ELEMENT_XPATH = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT_XPATH = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_RESULT_BY_SUBSTRING_TPL_XPATH = "xpath://XCUIElementTypeLink[contains(@name,'%s')]";
        SEARCH_CANCEL_BUTTON_ID = "id:Close";
        SEARCH_RESULT_XPATH = "xpath://XCUIElementTypeLink";
        EMPTY_RESULT_LABEL_XPATH = "xpath://XCUIElementTypeStaticText[@name='No results found']";
        RESULT_ID = "id:org.wikipedia:id/search_results_display";
        RESULTS_TITLE_ID = "id:org.wikipedia:id/page_list_item_title";
    }

    public iOSSearchPageObject(AppiumDriver driver) {
        super(driver);
    }
}
