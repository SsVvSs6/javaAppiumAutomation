package lib.iu;

import io.appium.java_client.AppiumDriver;

abstract public class NavigationUI extends MainPageObject {

    protected static  String BACK_BUTTON_XPATH;
    protected static String MY_LISTS_BUTTON_ID;

    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    public void clickBackIcon() {
        this.waitForElementAndClick(BACK_BUTTON_XPATH, "Cannot find 'X' link", 5);
    }

    public void clickMyLists() {
        this.waitForElementAndClick(MY_LISTS_BUTTON_ID,
                "Cannot find navigation button to My lists", 5);
    }
}
