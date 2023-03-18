package lib.iu.android;

import io.appium.java_client.AppiumDriver;
import lib.iu.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {

    static {
        SKIP_BUTTON_XPATH = "xpath://*[contains(@text,'SKIP')]";
        SEARCH_INIT_ELEMENT_XPATH = "xpath://*[contains(@text,'Search Wikipedia')]";
        SEARCH_RESULT_BY_SUBSTRING_TPL_XPATH = "xpath://*[contains(@text,'%s')]";
        SEARCH_CANCEL_BUTTON_ID = "id:org.wikipedia:id/search_close_btn";
        SEARCH_RESULT_XPATH = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title']";
        EMPTY_RESULT_LABEL_XPATH = "xpath://*[@text='No results']";
        RESULT_ID = "id:org.wikipedia:id/search_results_display";
        RESULTS_TITLE_ID = "id:org.wikipedia:id/page_list_item_title";
    }

    public AndroidSearchPageObject(AppiumDriver driver) {
        super(driver);
    }
}
