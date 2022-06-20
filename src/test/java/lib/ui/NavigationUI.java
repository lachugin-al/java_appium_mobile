package lib.ui;


import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject {

    protected static String
            MY_LIST_NAVIGATE_BUTTON,
            OPEN_NAVIGATION;

    public NavigationUI(RemoteWebDriver driver) {
        super(driver);
    }

    public void clickMyList() {
        if (Platform.getInstance().isMw()) {
            this.tryClickElementWithFewAttempts(
                    MY_LIST_NAVIGATE_BUTTON,
                    "Cannot find 'My lists' element",
                    5
            );
        }
        this.waitForElementAndClick(
                MY_LIST_NAVIGATE_BUTTON,
                "Cannot find 'My lists' element",
                5);
    }

    public void openNavigation() {
        if (Platform.getInstance().isMw()) {
            this.waitForElementAndClick(OPEN_NAVIGATION, "Cannot finde and click open navigation button", 5);
        } else {
            System.out.println("Method openNavigation not used ni platform: " + Platform.getInstance().getPlatformVar());
        }
    }
}
