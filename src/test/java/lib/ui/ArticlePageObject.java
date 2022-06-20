package lib.ui;

import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject
{
    protected static String
        TITLE,
        FOOTER_ELEMENT,
        OPTIONS_ADD_TO_MY_LIST_BUTTON,
        GO_IT_OPTIONS_BUTTON,
        CREATE_NEW_OPTIONS_BUTTON,
        NAME_OF_LIST_OPTIONS_FIELD,
        MY_LIST_OK_BUTTON,
        CLOSE_ARTICLE_BUTTON,
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON;


    public ArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }


    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page", 5);
    }


    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        if(Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        }else if(Platform.getInstance().isIOS()) {
           return title_element.getAttribute("name");
        }else {
            return title_element.getText();
        }
    }

    public void swipeToFooter()
    {
        if(Platform.getInstance().isAndroid()){
            this.swipeUpToFindeElement(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article ",
                    20
            );
        }else if (Platform.getInstance().isIOS()){
            this.swipeUpTitleElementAppear(FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    40);
        }else {
            this.scrollWebPageTillElementNotVisible(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    40
            );
        }
    }

    public void closeArticle()
    {
        if(Platform.getInstance().isIOS() || Platform.getInstance().isAndroid() ){
            this.waitForElementAndClick(CLOSE_ARTICLE_BUTTON,
                    "Cannot find close article button", 5);
        } else {
            System.out.println("Method swipeElementToLeft() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }
    public void addFirstArticleToMyList(String name_of_folder)
    {

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find 'Add this article to a reading list' element",
                5);

//        this.waitForElementAndClick(
//                By.xpath(GO_IT_OPTIONS_BUTTON),
//                "Cannot find 'GOT IT' button",
//                5);

        this.waitForElementAndClick(
                CREATE_NEW_OPTIONS_BUTTON,
                "Cannot find 'Create new' element1",
                10);

        this.waitForElementAndSendKeys(
                NAME_OF_LIST_OPTIONS_FIELD,
                name_of_folder,
                "Cannot find 'Text input in My List' element",
                10);

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot find Button 'OK'",
                5);

    }

    public void addNextArticleToMyList(String name_of_folder)
    {

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find 'Add this article to a reading list' element",
                5);

        this.waitForElementAndSendKeys(
                NAME_OF_LIST_OPTIONS_FIELD,
                name_of_folder,
                "Cannot find 'Text input in My List' element",
                5);

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot find Button 'OK'",
                5);
    }


    public void addArticleToMySaved()
    {
        if(Platform.getInstance().isMw()){
            this.removeArticleFromSavedIfItAdded();
        }
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot finde options button 'Add to my list'",
                5);

    }

    public void removeArticleFromSavedIfItAdded(){
        if(this.isElementPresent( OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)){
            this.waitForElementAndClick(
                    OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot click button to remove an article from saved",
                    1
            );
            this.waitForElementPresent(
                    OPTIONS_ADD_TO_MY_LIST_BUTTON,
                    "Cannot find button to add article in  my list",
                    5
            );
        }
    }


}
