package lib.ui.ios;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSNavigationUI extends NavigationUI {

    static {
        MY_LIST_NAVIGATE_BUTTON = "id:Saved";
    }

    public iOSNavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }
}
