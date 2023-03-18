package lib.iu.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.iu.MyListsPageObject;
import lib.iu.android.AndroidMyListPageObject;
import lib.iu.ios.iOSMyListsPageObject;

public class MyListsPageObjectFactory {

    public static MyListsPageObject get(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidMyListPageObject(driver);
        } else {
            return new iOSMyListsPageObject(driver);
        }
    }
}
