package lesson2;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {

    private AppiumDriver driver;
    private String searchFieldXPath = "//*[contains(@text,'Search Wikipedia')]";
    private String skipButtonXPath = "//*[contains(@text,'SKIP')]";
    private String javaFoundElementXPath = "//*[contains(@text,'Object-oriented programming language')]";

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","8.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app",
                "/Users/svetlanaseredenko/Desktop/Courses/HomeWorks/others/javaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void firstTest() {
        waitForElementAndClick(By.xpath(skipButtonXPath), "Cannot find Skip button", 5);
        waitForElementAndClick(By.xpath(searchFieldXPath), "Cannot find 'Search Wikipedia' input on Menu page", 5);
        waitForElementAndSendKeys(By.xpath(searchFieldXPath), "Java", "Cannot find search input", 5);
        waitForElementPresent(By.xpath(javaFoundElementXPath),
                "Cannot find 'Object-oriented programming language' topic searching by Java",
                15);
    }

    @Test
    public void testCancelSearch() {
        String crossId = "org.wikipedia:id/search_close_btn";
        waitForElementAndClick(By.xpath(skipButtonXPath), "Cannot find Skip button", 5);
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input on Menu page", 5);
        waitForElementAndSendKeys(By.xpath(searchFieldXPath), "Java", "Cannot find search input", 5);
//        waitForElementAndClick(By.id(crossId),"Cannot find 'X' to cancel search", 5);
        waitForElementAndClear(By.id("org.wikipedia:id/search_src_text"), "Cannot find search input", 5);
        waitForElementNotPresent(By.id(crossId), "'X' is still present on the page", 5);
    }

    @Test
    public void testCompareArticleTitle() {
        waitForElementAndClick(By.xpath(skipButtonXPath), "Cannot find Skip button", 5);
        waitForElementAndClick(By.xpath(searchFieldXPath), "Cannot find 'Search Wikipedia' input on Menu page", 5);
        waitForElementAndSendKeys(By.xpath(searchFieldXPath), "Java", "Cannot find search input", 5);
        waitForElementAndClick(By.xpath(javaFoundElementXPath),
                "Cannot find 'Object-oriented programming language' topic searching by Java",
                15);
        String articleTitle = waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"), "Cannot find article title",
                15).getAttribute("text");
        Assert.assertEquals("Title is unexpected", "Java (programming language)", articleTitle);
    }

    @Test
    public void testSearchFieldText() {
        waitForElementAndClick(By.xpath(skipButtonXPath), "Cannot find Skip button", 5);
        assertElementHasText(By.xpath(searchFieldXPath), "Search Wikipedia",
                "Search input text is incorrect");
    }

    private WebElement waitForElementPresent(By by, String errorMessage, long timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresent(By by, String errorMessage) {
        return waitForElementPresent(by, errorMessage, 5);
    }

    private WebElement waitForElementAndClick(By by, String errorMessage, long timeoutSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String errorMessage, long timeoutSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String errorMessage, long timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private WebElement waitForElementAndClear(By by, String errorMessage, long timeoutSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutSeconds);
        element.clear();
        return element;
    }

    private void assertElementHasText(By by, String expectedText, String errorMessage) {
        Assert.assertEquals(errorMessage, expectedText,
                waitForElementPresent(by, "Cannot found element by [" + by + "]").getAttribute("text"));
    }
}
