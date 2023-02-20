package lesson2;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class FirstTest {

    private AppiumDriver driver;
    private String searchFieldXPath = "//*[contains(@text,'Search Wikipedia')]";
    private String skipButtonXPath = "//*[contains(@text,'SKIP')]";
    private String javaFoundElementXPath = "//*[contains(@text,'Object-oriented programming language')]";
    private String crossId = "org.wikipedia:id/search_close_btn";

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","10");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app",
                "/Users/svetlanaseredenko/Desktop/Courses/HomeWorks/others/javaAppiumAutomation/apks/org.wikipedia.apk");
        capabilities.setCapability("orientation","PORTRAIT");

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
        String articleTitle = waitForElementPresent(By.xpath("//android.view.View[@instance=2]"), "Cannot find article title",
                15).getAttribute("text");
        Assert.assertEquals("Title is unexpected", "Java (programming language)", articleTitle);
    }

    @Test
    public void testSearchFieldTextOnMainPage() {
        waitForElementAndClick(By.xpath(skipButtonXPath), "Cannot find Skip button", 5);
        assertElementHasText(By.xpath(searchFieldXPath), "Search Wikipedia",
                "Search input text is incorrect");
    }

    @Test
    public void testSearchFieldTextOnSearchPage() {
        waitForElementAndClick(By.xpath(skipButtonXPath), "Cannot find Skip button", 5);
        waitForElementAndClick(By.xpath(searchFieldXPath), "Cannot find 'Search Wikipedia' input on Menu page", 5);
        assertElementHasText(By.xpath(searchFieldXPath), "Search Wikipedia",
                "Search input text is incorrect");
    }

    @Test
    public void testSearchAndCancel() {
        String resultsId = "org.wikipedia:id/search_results_display";
        waitForElementAndClick(By.xpath(skipButtonXPath), "Cannot find Skip button", 5);
        waitForElementAndClick(By.xpath(searchFieldXPath), "Cannot find 'Search Wikipedia' input on Menu page", 5);
        waitForElementAndSendKeys(By.xpath(searchFieldXPath), "Java", "Cannot find search input", 5);
        waitForElementPresent(By.id(resultsId), "Cannot find search results", 15);
        waitForElementAndClick(By.id(crossId),"Cannot find 'X' to cancel search", 5);
        boolean isResultsDisplayed = waitForElementNotPresent(By.id(resultsId),
                "Search results are still displayed", 15);
        Assert.assertTrue("Search results are still displayed", isResultsDisplayed);
    }

    @Test
    public void testWordInResultList() {
        String searchText = "Java";
        waitForElementAndClick(By.xpath(skipButtonXPath), "Cannot find Skip button", 5);
        waitForElementAndClick(By.xpath(searchFieldXPath), "Cannot find 'Search Wikipedia' input on Menu page", 5);
        waitForElementAndSendKeys(By.xpath(searchFieldXPath), searchText, "Cannot find search input", 5);
        waitForElementPresent(By.id("org.wikipedia:id/page_list_item_title"), "Result is not displayed", 15);
        List<WebElement> resultsList = driver.findElements(By.id("org.wikipedia:id/page_list_item_title"));
        for (int i = 0; i < resultsList.size(); i++) {
            Assert.assertTrue("Text is not contains '" + searchText + "'",
                    checkElementContainsText(resultsList.get(i).getAttribute("text"), searchText));
        }
    }

    @Test
    public void testSwipeArticle() {
        waitForElementAndClick(By.xpath(skipButtonXPath), "Cannot find Skip button", 5);
        waitForElementAndClick(By.xpath(searchFieldXPath), "Cannot find 'Search Wikipedia' input on Menu page", 5);
        waitForElementAndSendKeys(By.xpath(searchFieldXPath), "Appium", "Cannot find search input", 5);
        waitForElementAndClick(By.xpath("//*[contains(@text,'Automation for Apps')]"),
                "Cannot find 'Automation for Apps' topic searching by Java",
                15);
        waitForElementPresent(By.xpath(searchFieldXPath), "Cannot find article title",
                15);
        swipeUpToFindElement(By.xpath("//*[contains(@text,'View article in browser')]"),
                "Cannot find the end of the article", 20);
    }

    @Test
    public void saveFirstArticleToMyList() {
        String listName = "Learning programming";
        waitForElementAndClick(By.xpath(skipButtonXPath), "Cannot find Skip button", 5);
        waitForElementAndClick(By.xpath(searchFieldXPath), "Cannot find 'Search Wikipedia' input on Menu page", 5);
        waitForElementAndSendKeys(By.xpath(searchFieldXPath), "Java", "Cannot find search input", 5);
        waitForElementAndClick(By.xpath(javaFoundElementXPath),
                "Cannot find 'Object-oriented programming language' topic searching by Java",
                15);
        waitForElementAndClick(By.xpath("//*[@content-desc='Save']"),
                "Cannot find option to add article to reading list", 5);
        waitForElementAndClick(By.id("org.wikipedia:id/snackbar_action"),
                "Cannot find 'Add to list' tip overlay", 5);
        waitForElementAndClear(By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of list", 5);
        waitForElementAndSendKeys(By.id("org.wikipedia:id/text_input"), listName,
                "Cannot put text to list field name", 5);
        waitForElementAndClick(By.id("android:id/button1"), "Cannot find 'OK' button", 5);
        waitForElementAndClick(By.xpath("//*[@content-desc='Navigate up']"),
                "Cannot find 'X' link", 5);
        waitForElementAndClick(By.xpath("//*[@class='android.widget.ImageButton']"),
                "Cannot find 'X' link", 5);
        waitForElementAndClick(By.id("org.wikipedia:id/nav_tab_reading_lists"),
                "Cannot find navigation button to My lists", 5);
        waitForElementAndClick(By.xpath("//*[contains(@text,'" + listName + "')]"),
                "Cannot find saved list", 5);
        swipeElementToLeft(By.xpath(javaFoundElementXPath), "Cannot find saved article");
        waitForElementNotPresent(By.xpath(javaFoundElementXPath), "Cannot delete saved article", 5);
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        String searchLine = "Linkin Park Diskography";
        waitForElementAndClick(By.xpath(skipButtonXPath), "Cannot find Skip button", 5);
        waitForElementAndClick(By.xpath(searchFieldXPath), "Cannot find 'Search Wikipedia' input on Menu page", 5);
        waitForElementAndSendKeys(By.xpath(searchFieldXPath), searchLine, "Cannot find search input", 5);
        String searchedResultXPath = "//*[@resource-id='org.wikipedia:id/page_list_item_title']";
        waitForElementPresent(By.xpath(searchedResultXPath), "Cannot find anything by request" + searchLine, 15);
        int amountOfSearchedResults = getAmountOfElements(By.xpath(searchedResultXPath));
        Assert.assertTrue("We found too few results", amountOfSearchedResults > 0);
    }

    @Test
    public void testAmountOfEmptySearch() {
        String searchLine = "fghfjfj";
        waitForElementAndClick(By.xpath(skipButtonXPath), "Cannot find Skip button", 5);
        waitForElementAndClick(By.xpath(searchFieldXPath), "Cannot find 'Search Wikipedia' input on Menu page", 5);
        waitForElementAndSendKeys(By.xpath(searchFieldXPath), searchLine, "Cannot find search input", 5);
        String searchedResultXPath = "//*[@resource-id='org.wikipedia:id/page_list_item_title']";
        String emptyResultLabel = "//*[@text='No results']";
        waitForElementPresent(By.xpath(emptyResultLabel), "Cannot find empty result by request " + searchLine, 15);
        assertElementNotPresent(By.xpath(searchedResultXPath), "We've found some elements by request " + searchLine);
    }

    @Test
    public void testChangeScreenOrientationOnSearchResult() {
        String searchLine = "Java";
        waitForElementAndClick(By.xpath(skipButtonXPath), "Cannot find Skip button", 5);
        waitForElementAndClick(By.xpath(searchFieldXPath), "Cannot find 'Search Wikipedia' input on Menu page", 5);
        waitForElementAndSendKeys(By.xpath(searchFieldXPath), searchLine, "Cannot find search input", 5);
        waitForElementAndClick(By.xpath(javaFoundElementXPath),
                "Cannot find 'Object-oriented programming language' topic searching by " + searchLine,
                15);
        String titleBeforeRotation = waitForElementAndGetAttribute(By.xpath(javaFoundElementXPath), "text",
                "Cannot find title of article", 15);
        driver.rotate(ScreenOrientation.LANDSCAPE);
        String titleAfterRotation = waitForElementAndGetAttribute(By.xpath(javaFoundElementXPath), "text",
                "Cannot find title of article", 15);
        Assert.assertEquals("Article title has been changed after screen rotation", titleBeforeRotation,
                titleAfterRotation);
        driver.rotate(ScreenOrientation.PORTRAIT);
        String titleAfterSecondRotation = waitForElementAndGetAttribute(By.xpath(javaFoundElementXPath), "text",
                "Cannot find title of article", 15);
        Assert.assertEquals("Article title has been changed after second screen rotation", titleBeforeRotation,
                titleAfterSecondRotation);
    }

    @Test
    public void testCheckSearchArticleInBackground() {
        String searchLine = "Java";
        waitForElementAndClick(By.xpath(skipButtonXPath), "Cannot find Skip button", 5);
        waitForElementAndClick(By.xpath(searchFieldXPath), "Cannot find 'Search Wikipedia' input on Menu page", 5);
        waitForElementAndSendKeys(By.xpath(searchFieldXPath), searchLine, "Cannot find search input", 5);
        waitForElementPresent(By.xpath(javaFoundElementXPath),
                "Cannot find 'Object-oriented programming language' topic searching by " + searchLine,
                15);
        driver.runAppInBackground(5);
        waitForElementPresent(By.xpath(javaFoundElementXPath),
                "Cannot find article after running from background", 15);
    }

    @Test
    public void testSaveTwoArticlesAndDeleteOne() {
        String listName = "Learning programming";
        String javaSecondFoundElementXPath = "//*[contains(@text,'Island in Indonesia')]";
        waitForElementAndClick(By.xpath(skipButtonXPath), "Cannot find Skip button", 5);
        waitForElementAndClick(By.xpath(searchFieldXPath), "Cannot find 'Search Wikipedia' input on Menu page", 5);
        waitForElementAndSendKeys(By.xpath(searchFieldXPath), "Java", "Cannot find search input", 5);
        waitForElementAndClick(By.xpath(javaFoundElementXPath),
                "Cannot find 'Object-oriented programming language' topic searching by Java",
                15);
        saveArticleToTheNewListIfNoListsExists(listName);
        waitForElementAndClick(By.xpath("//*[@content-desc='Navigate up']"),
                "Cannot find 'X' link", 5);
        waitForElementAndClick(By.xpath(javaSecondFoundElementXPath),
                "Cannot find 'Island in Indonesia' topic searching by Java",
                15);
        saveArticleToExistList(By.xpath("//*[@text='" + listName + "']"));
        waitForElementAndClick(By.xpath("//*[@content-desc='Navigate up']"),
                "Cannot find 'X' link", 5);
        waitForElementAndClick(By.xpath("//*[@class='android.widget.ImageButton']"),
                "Cannot find 'X' link", 5);
        waitForElementAndClick(By.id("org.wikipedia:id/nav_tab_reading_lists"),
                "Cannot find navigation button to My lists", 5);
        waitForElementAndClick(By.xpath("//*[contains(@text,'" + listName + "')]"),
                "Cannot find saved list", 5);
        swipeElementToLeft(By.xpath(javaFoundElementXPath), "Cannot find saved article");
        waitForElementNotPresent(By.xpath(javaFoundElementXPath), "Cannot delete saved article", 5);
        waitForElementAndClick(By.xpath(javaSecondFoundElementXPath),
                "Cannot find 'Island in Indonesia' in the list of saved", 15);
        String titleOfArticle = waitForElementAndGetAttribute(By.xpath("//android.view.View[@instance=2]"), "text",
                "Cannot find title of article", 15);
        Assert.assertEquals("Article title is not correct", "Java", titleOfArticle);
    }

    @Test
    public void testTitleIsDisplayed() {
        waitForElementAndClick(By.xpath(skipButtonXPath), "Cannot find Skip button", 5);
        waitForElementAndClick(By.xpath(searchFieldXPath), "Cannot find 'Search Wikipedia' input on Menu page", 5);
        waitForElementAndSendKeys(By.xpath(searchFieldXPath), "Java", "Cannot find search input", 5);
        waitForElementAndClick(By.xpath(javaFoundElementXPath),
                "Cannot find 'Object-oriented programming language' topic searching by Java",
                15);
        assertElementPresent(By.xpath("//android.view.View[@instance=2]"),
                "Title is not found");
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

    private boolean checkElementContainsText(String actualText, String expectedText) {
        return actualText.contains(expectedText);
    }

    protected void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);
        action.press(x, startY).waitAction(timeOfSwipe).moveTo(x, endY).release().perform();
    }

    protected void swipeUpQuick() {
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String errorMessage, int maxSwipes) {
        int alreadySwiped = 0;
        while (driver.findElements(by).size() == 0) {
            if (alreadySwiped > maxSwipes) {
                waitForElementPresent(by, "Cannot find element by swipping up. \n" + errorMessage, 0);
                return;
            }
            swipeUpQuick();
            ++alreadySwiped;
        }
    }

    protected void swipeElementToLeft(By by, String errorMessage) {
        WebElement element = waitForElementPresent(by, errorMessage, 5);
        int leftX = element.getLocation().getX();
        int rightX = leftX + element.getSize().getWidth();
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;
        TouchAction action = new TouchAction(driver);
        action.press(rightX, middleY).waitAction(150).moveTo(leftX, middleY).release().perform();
    }

    protected int getAmountOfElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }

    private void assertElementNotPresent(By by, String errorMessage) {
        int amountOfElements = getAmountOfElements(by);
        if (amountOfElements > 0) {
            String defaultMessage = "An element '" + by.toString() + "' supposed not to be present";
            throw  new AssertionError(defaultMessage + " " + errorMessage);
        }
    }

    private String waitForElementAndGetAttribute(By by, String attribute, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    private void startSavingArticleToList() {
        waitForElementAndClick(By.xpath("//*[@content-desc='Save']"),
                "Cannot find option to add article to reading list", 5);
        waitForElementAndClick(By.id("org.wikipedia:id/snackbar_action"),
                "Cannot find 'Add to list' tip overlay", 5);
    }

    private void saveArticleToTheNewListIfNoListsExists(String listName) {
        startSavingArticleToList();
        waitForElementAndClear(By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of list", 5);
        waitForElementAndSendKeys(By.id("org.wikipedia:id/text_input"), listName,
                "Cannot put text to list field name", 5);
        waitForElementAndClick(By.id("android:id/button1"), "Cannot find 'OK' button", 5);
    }

    private void saveArticleToExistList(By by) {
        startSavingArticleToList();
        waitForElementAndClick(by, "Cannot find created list", 15);
    }

    private void assertElementPresent(By by, String errorMessage) {
        int amountOfElements = getAmountOfElements(by);
        if (amountOfElements == 0) {
            String defaultMessage = "An element '" + by.toString() + "' supposed to be present";
            throw  new AssertionError(defaultMessage + ". " + errorMessage);
        }
    }
}
