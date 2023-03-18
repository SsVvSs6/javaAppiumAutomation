package lib.iu.android;

import io.appium.java_client.AppiumDriver;
import lib.iu.NavigationUI;

public class AndroidNavigationUI extends NavigationUI {

    static {
        MY_LISTS_BUTTON_ID = "id:org.wikipedia:id/nav_tab_reading_lists";
        BACK_BUTTON_XPATH = "xpath://*[contains(@class,'ImageButton')]";
    }

    public AndroidNavigationUI(AppiumDriver driver) {
        super(driver);
    }
}
