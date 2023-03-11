package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class CoreTestCase extends TestCase {

    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";

    protected AppiumDriver driver;
    private static String appiumUrl = "http://127.0.0.1:4723/wd/hub";

    @Override
    protected void setUp() throws Exception {

        super.setUp();

        DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEnv();

        driver= this.getDriver(capabilities);
    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();

        super.tearDown();
    }

    protected void rotateScreenPortrait() {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape() {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundApp(int seconds) {
        driver.runAppInBackground(Duration.ofSeconds(seconds));
    }

    private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception {
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (platform.equals(PLATFORM_ANDROID)) {
            capabilities.setCapability("platformName","Android");
            capabilities.setCapability("deviceName","AndroidTestDevice");
            capabilities.setCapability("platformVersion","10");
            capabilities.setCapability("automationName","Appium");
            capabilities.setCapability("appPackage","org.wikipedia");
            capabilities.setCapability("appActivity",".main.MainActivity");
            capabilities.setCapability("app",
                    "/Users/svetlanaseredenko/Desktop/Courses/HomeWorks/others/javaAppiumAutomation/apks/org.wikipedia.apk");
            capabilities.setCapability("orientation","PORTRAIT");
        } else if (platform.equals(PLATFORM_IOS)) {
            capabilities.setCapability("platformName","iOS");
            capabilities.setCapability("deviceName","iPhone 14");
            capabilities.setCapability("platformVersion","16.2");
            capabilities.setCapability("app",
                    "/Users/svetlanaseredenko/Desktop/Courses/HomeWorks/others/javaAppiumAutomation/apks/Wikipedia.app");
            capabilities.setCapability("orientation","PORTRAIT");
        } else {
            throw new Exception("Cannot get run platform from env variable. Platform value " + platform);
        }
        return capabilities;
    }

    private AppiumDriver getDriver(Capabilities capabilities) throws Exception {
        String platform = System.getenv("PLATFORM");
        if (platform.equals(PLATFORM_ANDROID)) {
            return new AndroidDriver(new URL(appiumUrl), capabilities);
        } else if (platform.equals(PLATFORM_IOS)) {
            return new IOSDriver(new URL(appiumUrl), capabilities);
        } else {
            throw new Exception("Cannot get driver from env variable. Platform value " + platform);
        }
    }
}
