package lib.iu.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.iu.SearchPageObject;
import lib.iu.android.AndroidSearchPageObject;
import lib.iu.ios.iOSSearchPageObject;

public class SearchPageObjectFactory {

    public static SearchPageObject get(AppiumDriver driver) {
        if(Platform.getInstance().isAndroid()) {
            return new AndroidSearchPageObject(driver);
        } else {
            return new iOSSearchPageObject(driver);
        }
    }
}
