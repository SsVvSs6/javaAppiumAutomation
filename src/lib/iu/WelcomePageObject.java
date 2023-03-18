package lib.iu;

import io.appium.java_client.AppiumDriver;

public class WelcomePageObject extends MainPageObject {

    private static final String STEP_LEARN_MORE_LINK_ID = "id:Learn more about Wikipedia";
    private static final String STEP_NEW_WAYS_TO_EXPLORE_TEXT_ID = "id:New ways to explore";
    private static final String STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK_ID = "id:Add or edit preferred language";
    private static final String STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK_ID = "id:Learn more about data collected";
    private static final String NEXT_LINK_ID = "id:Next";
    private static final String GET_STARTED_LINK_ID = "id:Get started";
    private static final String SKIP_BUTTON_XPATH = "id:Skip";

    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void waitForLearnMoreLink() {
        this.waitForElementPresent(STEP_LEARN_MORE_LINK_ID,
                "Cannot find '" + STEP_LEARN_MORE_LINK_ID +"' link", 10);
    }

    public void waitForNewWayToExploreText() {
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE_TEXT_ID,
                "Cannot find '" + STEP_NEW_WAYS_TO_EXPLORE_TEXT_ID + "' text", 10);
    }

    public void waitForAddOrEditPreferredLangText() {
        this.waitForElementPresent(STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK_ID,
                "Cannot find '" + STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK_ID + "' link", 10);
    }

    public void waitForLearnMoreAboutDataCollectedText() {
        this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK_ID,
                "Cannot find '" + STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK_ID + "' link",
                10);
    }

    public void clickNextButton() {
        this.waitForElementAndClick(NEXT_LINK_ID,
                "Cannot find and click '" + NEXT_LINK_ID + "' button", 10);
    }

    public void clickGetStartedButton() {
        this.waitForElementAndClick(GET_STARTED_LINK_ID,
                "Cannot find and click '" + GET_STARTED_LINK_ID + "' button", 10);
    }

    public void clickSkip() {
        this.waitForElementAndClick(SKIP_BUTTON_XPATH, "Cannot find and click skip button",
                5);
    }
}
