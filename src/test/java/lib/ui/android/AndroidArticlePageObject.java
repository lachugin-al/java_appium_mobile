package lib.ui.android;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidArticlePageObject extends ArticlePageObject
{

    static {
        TITLE = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title']";
        FOOTER_ELEMENT = "xpath://*[@text='View page in browser']";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[@content-desc = 'Add this article to a reading list']";
        GO_IT_OPTIONS_BUTTON = "xpath://*[@text = 'GOT IT']";
        CREATE_NEW_OPTIONS_BUTTON = "xpath://*[@text = 'Create new']";
        NAME_OF_LIST_OPTIONS_FIELD = "xpath://*[@text = 'Name of this list']";
        MY_LIST_OK_BUTTON = "xpath://*[@text = 'OK']";
        CLOSE_ARTICLE_BUTTON = "xpath://*[@resource-id='org.wikipedia:id/page_toolbar']/android.widget.ImageButton]";
    }

    public AndroidArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
