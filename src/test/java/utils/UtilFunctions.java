package utils;

import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverManager;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.SerenityActions;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.webdriver.SerenityWebdriverManager;
import org.junit.Assert;
import org.junit.rules.Timeout;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import pageobjectgenerator.Settings;
import java.io.File;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.gemini.generic.ui.utils.DriverAction.*;

public class UtilFunctions extends PageObject {

    public static boolean isFileDownloaded(String fileName, int timeoutSeconds) throws InterruptedException {
        WebDriver driver = SerenityWebdriverManager.inThisTestThread().getCurrentDriver();
        File dir = new File(System.getProperty("user.home") + "/Downloads");
        File[] files = dir.listFiles();
        long startTime = System.currentTimeMillis();
        while ((System.currentTimeMillis() - startTime) < (timeoutSeconds * 1000)) {
            for (File file : files) {
                if (file.getName().equalsIgnoreCase(fileName)) {
                    return true;
                }
            }
            driver.manage().timeouts().wait(10000);
        }
        return false;
    }

    public static void switchToTab(String tabName) {
        WebDriver driver = SerenityWebdriverManager.inThisTestThread().getCurrentDriver();
        String currentHandle = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
            String title = driver.getTitle();
            if (title.contains(tabName)) {
                return;
            }
        }
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
        WebDriver driver = SerenityWebdriverManager.inThisTestThread().getCurrentDriver();
        driver.close();
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
//            LOG.info("Could not do right click on element " + $(locator));
        }
    }

    public void rightClick(WebElementFacade element) {
        try {
            element.contextClick();
        } catch (Exception e) {
            Assert.fail("Could not do right click on element: " + element);
//            LOG.info("Could not do right click on element: " + element);
        }
    }

    public void acceptAlert() {
        try {
            getDriver().switchTo().alert().accept();
        } catch (Exception e) {
            Assert.fail("Could not do accept alert");
//            LOG.info("Could not do accept alert");
        }
    }

    public void noOfTabs(String expectedTitle) {
        ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());
//        LOG.info("No of tabs: " + tabs.size());
    }

    public void changeFocusOfElement(WebElementFacade elementFacade) {
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
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

    public void pressEnter() {
        try {
            withAction().sendKeys(Keys.ENTER).build().perform();
        } catch (Exception e) {
            Assert.fail("Could not press enter");
        }
    }

    public void isImage(WebElementFacade element) {
        try {
            Assert.assertEquals("img", element.getTagName());
        } catch (Exception e) {
            Assert.fail("Could not enter text for alert");
        }
    }

    public void isImage(By locator) {
        try {
            Assert.assertEquals("img", $(locator).getTagName());
        } catch (Exception e) {
            Assert.fail("Could not enter text for alert");
        }
    }

    public void selectAll() {
        try {
            withAction().keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).build().perform();
        } catch (Exception e) {
            Assert.fail("Could not perform select All operation");
        }
    }

    public void copy() {
        try {
            withAction().keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).build().perform();
        } catch (Exception e) {
            Assert.fail("Could not perform copy operation");
        }
    }

    public void paste() {
        try {
            withAction().keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).build().perform();
        } catch (Exception e) {
            Assert.fail("Could not perform paste operation");
        }
    }

    public void closeTabAndSwitch(String window) {
        try {
            DriverManager.closeDriver();
            DriverManager.getWebDriver().switchTo().window(window);
            GemTestReporter.addTestStep("Close tab and switch to window", "Tab closed successfully and user is able to switch to another window", STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Close tab and switch to window", "Unable to close tab", STATUS.FAIL);
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
            driver.quit();
        } catch (Exception e) {
            GemTestReporter.addTestStep("Add cookie", "User is unable to add cookie", STATUS.FAIL);
            Settings.LOGGER.info("User gets an exception: " + e);
        }
    }

    public Object getCookieNamed(String url, String key) {
        WebDriver driver = DriverManager.getWebDriver();
        try {
            driver.get(url);
            Cookie cookie = driver.manage().getCookieNamed(key);
            GemTestReporter.addTestStep("Get cookie for: key "+key, "User is able to get cookie for: key "+key, STATUS.FAIL);
            Settings.LOGGER.info("User gets cookies associated with: URL" + url);
            driver.quit();
            return cookie;
        } catch (Exception e) {
            GemTestReporter.addTestStep("Get cookie", "User is unable to get cookie", STATUS.FAIL);
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
            GemTestReporter.addTestStep("Get all cookies", "User is able to get all cookie", STATUS.FAIL);
            Settings.LOGGER.info("User gets all cookies associated with: " + url);
            driver.quit();
            return cookies;
        } catch (Exception e) {
            GemTestReporter.addTestStep("Get all cookies", "User is unable to get all cookie", STATUS.FAIL);
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
        } catch (Exception e) {
            GemTestReporter.addTestStep("Get all cookies", "User is unable to get all cookie", STATUS.FAIL);
            Settings.LOGGER.info("User gets an exception: " + e);
        }
    }

    public void deleteAllCookies(String url) {
            WebDriver driver = DriverManager.getWebDriver();
            try {
                driver.get(url);
                // deletes all cookies
                driver.manage().deleteAllCookies();
            } catch (Exception e) {
                GemTestReporter.addTestStep("Get all cookies", "User is unable to get all cookie", STATUS.FAIL);
                Settings.LOGGER.info("User gets an exception: " + e);
            }
        }
}
