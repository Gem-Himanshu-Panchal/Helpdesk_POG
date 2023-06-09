package utils;

import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverManager;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.SerenityActions;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.webdriver.SerenityWebdriverManager;
import org.apache.logging.log4j.Level;
import org.junit.Assert;
import org.junit.rules.Timeout;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import pageobjectgenerator.Settings;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.gemini.generic.ui.utils.DriverAction.*;

public class UtilFunctions extends PageObject {

    /**
     * @author - Sajith, Hem, Ayush, Jasleen, Priyanshu, Rahul Tagra and Siddhanshi
     * @version - 1.0
     * @since - 5/16/2023
     */

    public static boolean isFileDownloaded(String fileName) throws InterruptedException, IOException {
        try {
            File dir = new File(System.getProperty("user.home") + "/Downloads");
            File[] files = dir.listFiles();
            long startTime = System.currentTimeMillis();
            while ((System.currentTimeMillis() - startTime) < Long.parseLong(UtilsMethodCodeGenerator.readProperties("timeOut"))) {
                for (File file : files) {
                    if (file.getName().equalsIgnoreCase(fileName)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            Assert.fail("");
        }
        return false;
    }

    public static void switchToTab(String tabName) {
        WebDriver driver = DriverManager.getWebDriver();
        String currentHandle = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
            String title = driver.getTitle();
            if (title.contains(tabName)) {
                return;
            }
        }
        closeCurrentTab();
        driver.switchTo().window(currentHandle);
    }

    public static void switchToTab(int tabIndex) {
        WebDriver driver = SerenityWebdriverManager.inThisTestThread().getCurrentDriver();
        String currentHandle = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(currentHandle)) {
                driver.switchTo().window(handle);
                if (--tabIndex == 0) {
                    break;
                }
            }
        }
    }

    public static void closeCurrentTab() {
        try {
            WebDriver driver = SerenityWebdriverManager.inThisTestThread().getCurrentDriver();
            driver.close();
        }
        catch (Exception e)
        {

        }
    }

    public static void hoverOverElement(WebElementFacade element) {
        Actions action = new Actions(Serenity.getDriver());
        WebElement webElement = element.getElement();
        action.moveToElement(webElement).perform();
    }

    public void rightClick(By locator) {
        try {
            $(locator).contextClick();
        } catch (Exception e) {
            Assert.fail("Could not do right click on element " + $(locator));
            Settings.LOGGER.info("Could not do right click on element " + $(locator));
        }
    }

    public void rightClick(WebElementFacade element) {
        try {
            element.contextClick();
        } catch (Exception e) {
            Assert.fail("Could not do right click on element: " + element);
            Settings.LOGGER.info("Could not do right click on element: " + element);
        }
    }

    public void noOfTabs(String expectedTitle) {
        ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());
        Settings.LOGGER.info("No of tabs: " + tabs.size());
    }

    public void changeFocusOfElement(WebElementFacade elementFacade) {
        JavascriptExecutor executor = (JavascriptExecutor) DriverManager.getWebDriver();
        executor.executeScript("arguments[0].focus();", elementFacade);
    }

    public static void getWindowFocus(String windowHandle) {
        try {
            DriverManager.getWebDriver().switchTo().window(windowHandle);
            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();
            js.executeScript("window.focus();");
        } catch (Exception e) {
            Settings.LOGGER.info("User gets an exception: " + e);
        }
    }

    public boolean isDisabled(By locator) {
        Boolean isElementDisabled = false;
        try {
            isElementDisabled = $(locator).getAttribute("disabled") != null;
        } catch (Exception e) {
            Settings.LOGGER.info("User gets an exception: " + e);
        }
        return isElementDisabled;
    }

    public void isImage(WebElementFacade element) {
        try {
            Assert.assertEquals("img", element.getTagName());
        } catch (Exception e) {
            Settings.LOGGER.info("Could not enter text for alert");
            Assert.fail("Could not enter text for alert");
        }
    }

    public void isImage(By locator) {
        try {
            Assert.assertEquals("img", $(locator).getTagName());
        } catch (Exception e) {
            Settings.LOGGER.info("Could not enter text for alert");
            Assert.fail("Could not enter text for alert");
        }
    }

    public void closeTabAndSwitch(String window) {
        try {
            DriverManager.closeDriver();
            DriverManager.getWebDriver().switchTo().window(window);
            GemTestReporter.addTestStep("Close tab and switch to window", "Tab closed successfully and user is able to switch to another window", STATUS.PASS);
            Settings.LOGGER.info("Tab closed successfully and user is able to switch to another window");
        } catch (Exception e) {
            GemTestReporter.addTestStep("Close tab and switch to window", "Unable to close tab", STATUS.FAIL);
            Settings.LOGGER.info("Unable to close tab");
            Settings.LOGGER.info("User gets an exception: " + e);
        }
    }

    public void addCookie(String url, String key, String value) {
        WebDriver driver = DriverManager.getWebDriver();
        try {
            driver.get(url);
            // Adds the cookie into current browser context
            driver.manage().addCookie(new Cookie(key, value));
            GemTestReporter.addTestStep("Add cookie", "Cookie added successfully", STATUS.PASS);
            Settings.LOGGER.info("Cookie added successfully");
            driver.quit();
        } catch (Exception e) {
            GemTestReporter.addTestStep("Add cookie", "User is unable to add cookie", STATUS.FAIL);
            Settings.LOGGER.info("User is unable to add cookie");
            Settings.LOGGER.info("User gets an exception: " + e);
        }
    }

    public Object getCookieNamed(String url, String key) {
        WebDriver driver = DriverManager.getWebDriver();
        try {
            driver.get(url);
            Cookie cookie = driver.manage().getCookieNamed(key);
            GemTestReporter.addTestStep("Get cookie for: key "+key, "User is able to get cookie for: key "+key, STATUS.PASS);
            Settings.LOGGER.info("User gets cookies associated with: URL" + url);
            driver.quit();
            return cookie;
        } catch (Exception e) {
            GemTestReporter.addTestStep("Get cookie", "User is unable to get cookie", STATUS.FAIL);
            Settings.LOGGER.info("User is unable to get cookie");
            Settings.LOGGER.info("User gets an exception: " + e);
            return null;
        }
    }

    public Object getAllCookies(String url) {
        WebDriver driver = DriverManager.getWebDriver();
        try {
            driver.get(url);
            // Get All available cookies
            Set<Cookie> cookies = driver.manage().getCookies();
            GemTestReporter.addTestStep("Get all cookies", "User is able to get all cookies", STATUS.PASS);
            Settings.LOGGER.info("User gets all cookies associated with: " + url);
            driver.quit();
            return cookies;
        } catch (Exception e) {
            GemTestReporter.addTestStep("Get all cookies", "User is unable to get all cookies", STATUS.FAIL);
            Settings.LOGGER.info("User is unable to get all cookies");
            Settings.LOGGER.info("User gets an exception: " + e);
            return null;
        }
    }

    public void deleteCookie(String url,String key) {
        WebDriver driver = DriverManager.getWebDriver();
        try {
            driver.get(url);
            // delete a cookie with name 'test1'
            driver.manage().deleteCookieNamed(key);
            GemTestReporter.addTestStep("Delete cookie", "User is able to delete cookie", STATUS.PASS);
            Settings.LOGGER.info("User is able to delete cookie "+url);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Delete cookie", "User is unable to delete cookie", STATUS.FAIL);
            Settings.LOGGER.info("User is unable to delete cookie");
            Settings.LOGGER.info("User gets an exception: " + e);
        }
    }

    public void deleteAllCookies(String url) {
        WebDriver driver = DriverManager.getWebDriver();
        try {
            driver.get(url);
            // deletes all cookies
            driver.manage().deleteAllCookies();
            GemTestReporter.addTestStep("Delete cookies", "User is able to delete cookies", STATUS.PASS);
            Settings.LOGGER.info("User is able to delete cookie "+url);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Delete cookies", "User is unable to delete cookies", STATUS.FAIL);
            Settings.LOGGER.info("User is unable to delete cookies "+url);
            Settings.LOGGER.info("User gets an exception: " + e);
        }
    }

    public void table(By table,By row,By col){
        WebDriver driver = DriverManager.getWebDriver();
        WebElement baseTable = driver.findElement(table);

        //To find third row of table
        WebElement tableRow = baseTable.findElement(row);
        String rowtext = tableRow.getText();
        if(!rowtext.isEmpty())
            System.out.println("Third row of table : "+rowtext);

        //to get 3rd row's 2nd column data
        WebElement cellIneed = tableRow.findElement(col);
        String valueIneed = cellIneed.getText();
        System.out.println("Cell value is : " + valueIneed);
        driver.close();
    }

}