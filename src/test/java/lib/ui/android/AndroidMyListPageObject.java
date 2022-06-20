package lib.ui.android;

import lib.ui.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidMyListPageObject extends MyListPageObject {

    static {
        FOLDER_NAME_TPL = "xpath://*[@text='{SUBSTRING}']";
        ARTICLE_NAME_IN_MY_LIST_TPL = "xpath://*[@text = '{SUBSTRING}']";
    }

    public AndroidMyListPageObject (RemoteWebDriver driver){
        super(driver);
    }
}
