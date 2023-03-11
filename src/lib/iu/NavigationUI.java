package lib.iu;

import io.appium.java_client.AppiumDriver;

public class NavigationUI extends MainPageObject {

    private static final String BACK_BUTTON_XPATH = "xpath://*[@class='android.widget.ImageButton']";
    private static final String MY_LISTS_BUTTON_ID = "id:org.wikipedia:id/nav_tab_reading_lists";

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
