package lib.ui;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListPageObject extends MainPageObject {

    protected static String
            FOLDER_NAME_TPL,
            ARTICLE_NAME_IN_MY_LIST_TPL,
            REMOVE_FROM_SAVED_BUTTON;

    public MyListPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    private String getXpathNameFolder(String substring) {
        return FOLDER_NAME_TPL.replace("{SUBSTRING}", substring);
    }

    private String getXpathArticleInFolder(String substring) {
        return ARTICLE_NAME_IN_MY_LIST_TPL.replace("{SUBSTRING}", substring);
    }

    private String getRemoveButtonByTitle(String article_title) {
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", article_title);
    }

    public void openFolderByName(String folder_name) {
        String name_of_folder_xpath = getXpathNameFolder(folder_name);
        this.waitForElementAndClick(
                name_of_folder_xpath,
                "Cannot find my list = '" + folder_name + "'",
                5);
    }

    public void deleteArticleFromMyList(String article_title) {
        this.waitForArticleToAppearByTitle(article_title);
        String name_article_xpath = getXpathArticleInFolder(article_title);
        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            this.swipeElementToLeft(
                    name_article_xpath,
                    "Cannot find in list '" + article_title + "' element");
        } else {
            String remove_locator = getRemoveButtonByTitle(article_title);
            this.waitForElementAndClick(
                    remove_locator,
                    "Cannot click button to remove article",
                    5
            );
        }

        if (Platform.getInstance().isIOS()) {
            this.clickElementToTheRightUpperCorner(name_article_xpath, "Cannot find saved article");
        }
        if (Platform.getInstance().isMw()) {
            driver.navigate().refresh();
        }

        this.waitForArticleToDisappearByTitle(article_title);
    }


    public void waitForArticleToDisappearByTitle(String article_title) {
        String name_article_xpath = getXpathArticleInFolder(article_title);
        this.waitElementNotPresent(
                name_article_xpath,
                "This article '" + article_title + "' present in list",
                5
        );
    }

    public void waitForArticleToAppearByTitle(String article_title) {
        String name_article_xpath = getXpathArticleInFolder(article_title);
        this.waitForElementPresent(
                name_article_xpath,
                "Cannot find save article'" + article_title + "' present in list",
                5
        );
    }
}
