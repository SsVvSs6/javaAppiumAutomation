package lib.iu.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.iu.NavigationUI;
import lib.iu.android.AndroidNavigationUI;
import lib.iu.ios.iOSNavigationUI;

public class NavigationUIFactory {

    public static NavigationUI get(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidNavigationUI(driver);
        } else {
            return new iOSNavigationUI(driver);
        }
    }
}
