package lib.ui.android;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "xpath://android.widget.TextView[@text='Search Wikipedia']";
        SEARCH_INPUT = "xpath://*[@text='Search…']";
        SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@text='{SUBSTRING}']";
        SEARCH_RESULT_ELEMENT_AMOUNT_TPL = "xpath://*[@resource-id = 'org.wikipedia:id/search_results_list']/*[contains(@text,'{SUBSTRING}')]";
        EMPTY_RESULT_LABEL = "xpath://*[@text = 'No results found']";
    }

    public AndroidSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }

}
