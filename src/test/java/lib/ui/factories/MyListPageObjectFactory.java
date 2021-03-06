package lib.ui.factories;

import lib.Platform;
import lib.ui.MyListPageObject;
import lib.ui.android.AndroidMyListPageObject;
import lib.ui.ios.iOSMyListPageObject;
import lib.ui.mobile_web.MWMyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MyListPageObjectFactory {

    public static MyListPageObject get(RemoteWebDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidMyListPageObject(driver);
        } else if (Platform.getInstance().isIOS()) {
            return new iOSMyListPageObject(driver);
        } else {
            return new MWMyListsPageObject(driver);
        }
    }
}
