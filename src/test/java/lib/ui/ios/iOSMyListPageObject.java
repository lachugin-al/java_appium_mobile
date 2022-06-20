package lib.ui.ios;

import lib.ui.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSMyListPageObject extends MyListPageObject {

    static {
        ARTICLE_NAME_IN_MY_LIST_TPL = "xpath://XCUIElementTypeLink[contains(@name = '{SUBSTRING}')]";
    }

    public iOSMyListPageObject (RemoteWebDriver driver){
        super(driver);
    }
}
