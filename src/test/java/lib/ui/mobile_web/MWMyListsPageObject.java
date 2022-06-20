package lib.ui.mobile_web;

import lib.ui.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListPageObject {

    static {
        ARTICLE_NAME_IN_MY_LIST_TPL = "xpath://ul[contains(@class, 'watchlist')]//h3[contains(text(),'{TITLE}')]";
        REMOVE_FROM_SAVED_BUTTON = "xpath://ul[contains(@class, 'watchlist')]//h3[contains(text(),'{TITLE}')]/../../[contains(@class, 'watched')]";

    }

    public MWMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
