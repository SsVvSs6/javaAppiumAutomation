package lib.iu.ios;

import io.appium.java_client.AppiumDriver;
import lib.iu.NavigationUI;

public class iOSNavigationUI extends NavigationUI {

    static {
        MY_LISTS_BUTTON_ID = "id:Saved";
        BACK_BUTTON_XPATH = "xpath://*[@class='android.widget.ImageButton']";
    }

    public iOSNavigationUI(AppiumDriver driver) {
        super(driver);
    }
}
