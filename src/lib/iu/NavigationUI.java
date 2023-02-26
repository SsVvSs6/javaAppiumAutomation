package lib.iu;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject {

    private static final String BACK_BUTTON_XPATH = "//*[@class='android.widget.ImageButton']";
    private static final String MY_LISTS_BUTTON_ID = "org.wikipedia:id/nav_tab_reading_lists";

    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    public void clickBackIcon() {
        this.waitForElementAndClick(By.xpath(BACK_BUTTON_XPATH), "Cannot find 'X' link", 5);
    }

    public void clickMyLists() {
        this.waitForElementAndClick(By.id(MY_LISTS_BUTTON_ID),
                "Cannot find navigation button to My lists", 5);
    }
}
