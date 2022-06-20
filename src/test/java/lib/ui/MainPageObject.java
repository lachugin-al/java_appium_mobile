package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {

    protected RemoteWebDriver driver;

    public MainPageObject(RemoteWebDriver driver)
    {
        this.driver = driver;
    }


    public void swipeWindow(int timeOfSeconds)
    {
        if(driver instanceof AppiumDriver){
            TouchAction action = new TouchAction((AppiumDriver) driver);
            Dimension size = driver.manage().window().getSize();
            int x = (int) (size.width / 2);
            int start_y = (int) (size.height * 0.8);
            int end_y = (int) (size.height * 0.2);

            action.moveTo(x, start_y).waitAction(timeOfSeconds).moveTo(x, end_y).perform();
        } else {
            System.out.println("Method swipeWindow() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public WebElement inputSearchText(String locator, String Keys)
    {

        WebElement element = waitForElementPresent(
                locator,
                "Cannot find 'Search…' line",
                5);

        String find_element = element.getText();

        Assert.assertEquals(
                "Search…",
                "Search…",
                find_element
        );

        element.sendKeys(Keys);
        return element;

    }

    public List<WebElement> waitForListElements(String locator, String error_message, long timeoutInSeconds)
    {
        By by = getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        List<WebElement> webElementList =  driver.findElements(by);
        return webElementList;
    }

    public WebElement waitForElementPresent(String locator, String error_message, long timeoutInSeconds)
    {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait =  new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementPresent(String locator, String error_message)
    {
        return waitForElementPresent(locator, error_message, 5);
    }

    public WebElement waitForElementAndClick(String locator, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent( locator,  error_message,  timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(String locator, String Keys, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent( locator,  error_message,  timeoutInSeconds);
        element.sendKeys(Keys);
        return element;
    }

    public boolean waitElementNotPresent(String locator, String error_message, long timeOutInSeconds)
    {
        By by = getLocatorByString(locator);
        WebDriverWait wait =  new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public void swipeUp(int timeOfSwipe)
    {
        if(driver instanceof AppiumDriver){
            TouchAction action = new TouchAction((AppiumDriver) driver);
            Dimension size = driver.manage().window().getSize();
            int x = size.width /2;
            int start_y =(int) (size.height * 0.8);
            int end_y =(int) (size.height * 0.2);

            action
                    .press(x, start_y)
                    .waitAction(timeOfSwipe)
                    .moveTo(x, end_y)
                    .release()
                    .perform();
        } else {
            System.out.println("Method swipeUp() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void swipeUpQuick()
    {
        swipeUp(200);
    }

    public void swipeUpToFindeElement(String locator, String error_messgae, int max_swipes)
    {
        int already_swiped = 0;
        By by = getLocatorByString(locator);
        while(driver.findElements(by).size() == 0)
        {
            if(already_swiped > max_swipes){
                waitForElementPresent(locator, "Cannot find element by swiping up. \n" + error_messgae, 10);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        };
    }

    public void swipeUpTitleElementAppear(String locator, String error_message, int max_swipe)
    {
        int already_swiped = 0;

        while(!this.isElementLocatedOnTheScreen(locator))
        {
            if(already_swiped > max_swipe){
                Assert.assertTrue(error_message, this.isElementLocatedOnTheScreen(locator));
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }


    public boolean isElementLocatedOnTheScreen(String locator)
    {
        int element_location_by_y = this.waitForElementPresent(locator, "Cannot fond element by locator", 1).getLocation().getY();
        if(Platform.getInstance().isMw()){
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            Object is_result = JSExecutor.executeScript("return window.pageYOffset");
            element_location_by_y -= Integer.parseInt(is_result.toString());
        }
        int screen_size_by_y = driver.manage().window().getSize().getHeight();
        return element_location_by_y < screen_size_by_y;
    }


    public void clickElementToTheRightUpperCorner(String locator, String error_message){
        if(driver instanceof AppiumDriver){
            WebElement element = this.waitForElementPresent(locator + "/.." , error_message);
            int right_x = element.getLocation().getX();
            int upper_y = element.getLocation().getY();
            int lower_y = upper_y + element.getSize().height;
            int middle_y = (upper_y + lower_y) / 2;
            int width = element.getSize().getWidth();

            int point_to_click_x = (right_x + width) - 3;
            int point_to_click_y = middle_y;

            TouchAction action = new TouchAction((AppiumDriver) driver);
            action.tap(point_to_click_x,point_to_click_y).perform();
        } else  {
            System.out.println("Method clickElementToTheRightUpperCorner() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }


    public void swipeElementToLeft(String locator, String error_message)
    {
        if(driver instanceof AppiumDriver) {
            WebElement element = waitForElementPresent(
                    locator,
                    error_message,
                    10);

            int left_x = element.getLocation().getX();
            int right_x = left_x + element.getSize().width;

            int upper_y = element.getLocation().getY();
            int lower_y = upper_y + element.getSize().height;
            int middle_y = (upper_y + lower_y) / 2;

            TouchAction action = new TouchAction((AppiumDriver) driver);

            action.press(right_x, middle_y);
            action.waitAction(300);

            if (Platform.getInstance().isAndroid()) {
                action.moveTo(left_x, middle_y);
            } else {
                int offset_x = (-1 * element.getSize().getWidth());
                action.moveTo(offset_x, 0);
            }
            action.release();
            action.perform();
        }else  {
            System.out.println("Method swipeElementToLeft() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void tryClickElementWithFewAttempts(String locator, String error_message, int amount_of_attempts){
        int current_attempts= 0;
        boolean need_more_attempts = true;

        while(need_more_attempts){
            try{
                this.waitForElementAndClick(locator, error_message, 1);
                need_more_attempts = false;
            } catch (Exception e){
                    if(current_attempts > amount_of_attempts){
                        this.waitForElementAndClick(locator,error_message,1);
                    }
            }
            ++current_attempts;
        }
    }


    public int getAmountOfElements(String locator)
    {
        By by = getLocatorByString(locator);
        List element = driver.findElements(by);
        return element.size();
    }

    public void assertElementNotPresent(String locator, String error_message)
    {

        int amount_of_elements = getAmountOfElements(locator);
        if (amount_of_elements > 0){
            String default_message = "An element " + locator + " supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    public String waitElementAndGetAttribute(String locator, String attribute, String error_message, long timeoutInSecond)
    {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSecond);
        return element.getAttribute(attribute);
    }


    public void assertElementPresent(WebElement element)
    {
        String title = element.getText();
        if (title.isEmpty()){
            throw new AssertionError("Element " + element.toString() + " not contain attribute 'title'");
        }
    }

    private By getLocatorByString(String locator_with_type)
    {
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"),2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];

        if (by_type.equals("xpath")){
            return By.xpath(locator);
        } else if(by_type.equals("id")){
            return By.id(locator);
        } else if(by_type.equals("css")){
            return By.cssSelector(locator);
        }else {
            throw  new IllegalArgumentException("Cannot get type of locator: " + locator_with_type);
        }
    }

    public void scrollWepPageUp(){
        if(Platform.getInstance().isMw()){
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            JSExecutor.executeScript("window.scrollBy(0, 250)");
        } else {
            System.out.println("Method scrollWepPageUp() do nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void scrollWebPageTillElementNotVisible(String locator, String error_message, int max_swipes){
        int already_swiped = 0;

        WebElement element = this.waitForElementPresent(locator, error_message);
        while(!this.isElementLocatedOnTheScreen(locator)){
            scrollWepPageUp();
            ++already_swiped;
            if(already_swiped > max_swipes){
                Assert.assertTrue(error_message, element.isDisplayed());
            }
        }
    }

    public boolean isElementPresent(String locator){
        return getAmountOfElements(locator) > 0;
    }
}
