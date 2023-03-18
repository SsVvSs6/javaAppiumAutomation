package lib.iu.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.iu.ArticlePageObject;
import lib.iu.android.AndroidArticlePageObject;
import lib.iu.ios.iOSArticlePageObject;

public class ArticlePageObjectFactory {

    public static ArticlePageObject get(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidArticlePageObject(driver);
        } else {
            return new iOSArticlePageObject(driver);
        }
    }
}
