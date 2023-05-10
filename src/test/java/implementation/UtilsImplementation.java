package implementation;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.PageObject;
import org.apache.commons.io.FileUtils;
import java.io.File;
import com.gemini.generic.ui.utils.DriverManager;
import pageobjectgenerator.Settings;
import net.serenitybdd.core.Serenity;
import static org.junit.Assert.assertTrue;
import org.junit.Assert;
import static org.junit.Assert.assertFalse;
import locators.Sample;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Keys;
import java.util.Objects;
import org.openqa.selenium.*;
import net.serenitybdd.core.pages.SerenityActions;
import org.openqa.selenium.By;
import java.lang.reflect.Field;
import java.util.List;
import java.util.ArrayList;
import utils.UtilsMethodCodeGenerator;

public class UtilsImplementation extends PageObject {

    public String getURL() {
        String currentUrl = "";
        try{
			currentUrl = getDriver().getCurrentUrl();
        	Settings.LOGGER.info("User successfully gets current URL");
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
			Settings.LOGGER.info("Unable to get current URL");
			Serenity.recordReportData().withTitle("Failure").andContents("Unable to get current URL");
			Assert.fail(e.getMessage());
        }
		return currentUrl;
    }

    public void verifyURL(String expectedURL) {
        try{
			String actualURL = getURL();
        	assertTrue("Actual URL: " + getURL(), actualURL.equals(expectedURL));
        	Settings.LOGGER.info("User successfully navigated back");
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
			Settings.LOGGER.info("Actual URL: " + getURL());
			Serenity.recordReportData().withTitle("Failure").andContents("Actual URL: " + getURL());
			Assert.fail(e.getMessage());
        };
    }

    public String getTitle() {
        String title = "";
        try{
			title = getDriver().getTitle();
        	Settings.LOGGER.info("User successfully gets current title");
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
			Settings.LOGGER.info("Unable to get current title");
			Serenity.recordReportData().withTitle("Failure").andContents("Unable to get current title");
			Assert.fail(e.getMessage());
        }
		return title;
    }

    public void verifyTitle(String expectedTitle) {
        try{
			String actualTitle = getTitle();
        	assertTrue("Actual title: " + actualTitle, actualTitle.equals(expectedTitle));
        	Settings.LOGGER.info("User successfully verifies title");
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
			Settings.LOGGER.info("Actual Title: " + getTitle());
			Serenity.recordReportData().withTitle("Failure").andContents("Actual Title: " + getTitle());
			Assert.fail(e.getMessage());
        };
    }

    public void navigateTo(String url) {
        try{
			getDriver().navigate().to(url);
        	Settings.LOGGER.info("User successfully navigated to URL: " + url);
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
			Settings.LOGGER.info("Could not navigate to URL: " + url);
			Serenity.recordReportData().withTitle("Failure").andContents("Could not navigate to URL: " + url);
			Assert.fail(e.getMessage());
        };
    }

    public void forwardNavigation() {
        try{
			getDriver().navigate().forward();
        	Settings.LOGGER.info("User successfully navigated Forward");
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
			Settings.LOGGER.info("Could not navigate forward");
			Serenity.recordReportData().withTitle("Failure").andContents("Could not navigate forward");
			Assert.fail(e.getMessage());
        };
    }

    public void backwardNavigation() {
        try{
			getDriver().navigate().back();
        	Settings.LOGGER.info("User successfully navigated back");
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
			Settings.LOGGER.info("Could not navigate back");
			Serenity.recordReportData().withTitle("Failure").andContents("Could not navigate back");
			Assert.fail(e.getMessage());
        };
    }

    public void switchActiveElement() {
        try{
			getDriver().switchTo().activeElement();
        	Settings.LOGGER.info("User successfully switched to active element");
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
			Settings.LOGGER.info("Could not switch to active element");
			Serenity.recordReportData().withTitle("Failure").andContents("Could not switch to active element");
			Assert.fail(e.getMessage());
        };
    }

    public void switchDefaultContent() {
        try{
			getDriver().switchTo().defaultContent();
        	Settings.LOGGER.info("User successfully switched to default content");
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
			Settings.LOGGER.info("Could not switch to default element");
			Serenity.recordReportData().withTitle("Failure").andContents("Could not switch to default element");
			Assert.fail(e.getMessage());
        };
    }

    public void switchParentFrame() {
        try{
			getDriver().switchTo().parentFrame();
        	Settings.LOGGER.info("User successfully switched to parent frame");
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
			Settings.LOGGER.info("Could not switch to parent frame");
			Serenity.recordReportData().withTitle("Failure").andContents("Could not switch to parent frame");
			Assert.fail(e.getMessage());
        };
    }

    public void switchFrame(String nameOrId) {
        try{
			getDriver().switchTo().frame(nameOrId);
        	Settings.LOGGER.info("User successfully switched to frame" + nameOrId);
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
			Settings.LOGGER.info("Could not switch to frame: " + nameOrId);
			Serenity.recordReportData().withTitle("Failure").andContents("Could not switch to frame: " + nameOrId);
			Assert.fail(e.getMessage());
        };
    }

    public void switchFrame(int index) {
        try{
			getDriver().switchTo().frame(index);
        	Settings.LOGGER.info("User successfully switched to frame" + index);
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
			Settings.LOGGER.info("Could not switch to frame: " + index);
			Serenity.recordReportData().withTitle("Failure").andContents("Could not switch to frame: " + index);
			Assert.fail(e.getMessage());
        };
    }

    public void switchWindow(String nameOrHandle) {
        try{
			getDriver().switchTo().window(nameOrHandle);
        	Settings.LOGGER.info("User successfully switched to window" + nameOrHandle);
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
			Settings.LOGGER.info("Could not switch to window: " + nameOrHandle);
			Serenity.recordReportData().withTitle("Failure").andContents("Could not switch to window: " + nameOrHandle);
			Assert.fail(e.getMessage());
        };
    }

    public void wait(int duration) {
        try{
			waitABit(duration);
        	Settings.LOGGER.info("User waits for " + duration + " seconds");
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
			Settings.LOGGER.info("Could not wait for : " + duration + " seconds");
			Serenity.recordReportData().withTitle("Failure").andContents("Could not wait for : " + duration + " seconds");
			Assert.fail(e.getMessage());
        };
    }

    public void clicksAndHold(String locatorName) {
        try{
			By locator = getLocator(locatorName);
			new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut")))).until(ExpectedConditions.elementToBeClickable(locator));
        	new SerenityActions(getDriver()).moveToElement($(locator)).clickAndHold().build().perform();
        	Settings.LOGGER.info("User successfully clicks and holds " + locator + " element");
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
			Settings.LOGGER.info("Could not click and hold: " + locatorName);
			Serenity.recordReportData().withTitle("Failure").andContents("Could not click and hold: " + locatorName);
			Assert.fail(e.getMessage());
        };
    }

    public void tearDown() {
        try{
			getDriver().quit();
        	Settings.LOGGER.info("User successfully closed driver");
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
			Settings.LOGGER.info("Could not close driver");
			Serenity.recordReportData().withTitle("Failure").andContents("Could not close driver");
			Assert.fail(e.getMessage());
        };
    }

    public void openApplication() {
        try{
			getDriver().get(Settings.URL);
        Settings.LOGGER.info("User launches the application");
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
			Settings.LOGGER.info("Could not launch the application with URL: " + Settings.URL);
			Serenity.recordReportData().withTitle("Failure").andContents("Could not launch the application with URL: " + Settings.URL);
			Assert.fail(e.getMessage());
		};
    }

    public void maximizeBrowserToDefault() {
        try{
			getDriver().manage().window().maximize();
        	Settings.LOGGER.info("Browser Maximization to default successful");
		} catch(Exception e){;
        		Settings.LOGGER.info("Unable to maximize browser to default");
			Settings.LOGGER.info("User gets an exception: "+e);
        	Serenity.recordReportData().withTitle("Failure").andContents("Could not maximize browser to default");
			Assert.fail(e.getMessage());
        };
    }

    public void minimizeGivenBrowser() {
        try{
			getDriver().manage().window().minimize();
        			Settings.LOGGER.info("Browser Minimization successful.");
}catch(Exception e){;
        			Settings.LOGGER.info("Unable to minimize browser.");
			Settings.LOGGER.info("User gets an exception: "+e);
        	Serenity.recordReportData().withTitle("Failure").andContents("Could not minimize browser");
			Assert.fail(e.getMessage());
        };
    }

    public Object browserSize() {
        Integer sizeOfBrowser = null;
        try { 
			Object size = getDriver().manage().window().getSize();
        	if(size!=null);
        	sizeOfBrowser = Integer.valueOf(size.toString());
        
		} catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
			Settings.LOGGER.info("Could not get size of browser");
			Serenity.recordReportData().withTitle("Failure").andContents("Could not get size of browser");
			Assert.fail(e.getMessage());
		};
        return sizeOfBrowser;
    }

    public void setSizeOfBrowser(int width, int height) {
        try{
			Dimension newDimension = new Dimension(width, height);
			getDriver().manage().window().setSize(newDimension)		;
        	Settings.LOGGER.info("User successfully set size of browser");
        } 
			catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
			Settings.LOGGER.info("Could not set size of browser");
			Serenity.recordReportData().withTitle("Failure").andContents("Could not set size of browser");
			Assert.fail(e.getMessage());
		};
    }

    public void setPositionOfBrowser(int x, int y) {
        try{
			getDriver().manage().window().setPosition(new Point(x,y));
        	Settings.LOGGER.info("User successfully set position of browser");
        } 
			catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
			Settings.LOGGER.info("Could not set position of browser");
			Serenity.recordReportData().withTitle("Failure").andContents("Could not set position of browser");
			Assert.fail(e.getMessage());
		};
    }

    public Object browserPosition() {
        Integer positionOfBrowser = null;
        try{
		Object position = getDriver().manage().window().getPosition();
        if(position!=null)	;
        	positionOfBrowser = Integer.valueOf(position.toString());
        	} 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
			Settings.LOGGER.info("Unable to get browser position");
			Serenity.recordReportData().withTitle("Failure").andContents("Unable to get browser position");
			Assert.fail(e.getMessage());
        }
		return positionOfBrowser;
    }

    public Object windowHandle() {
        String windowHandle = null;
        try { 
			windowHandle = getDriver().getWindowHandle();
        if(windowHandle==null){
			throw new NullPointerException();
        } 
			} catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
			Settings.LOGGER.info("Unable to get window handle");
			Serenity.recordReportData().withTitle("Failure").andContents("Unable to get window handle");
			Assert.fail(e.getMessage());
		};
        return windowHandle;
    }

    public String windowHandles() {
        String windowHandles = null;
        try { 
			windowHandles = getDriver().getWindowHandles().toString();
        if(windowHandles==null){
			throw new NullPointerException();
        } 
			} catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
			Settings.LOGGER.info("Unable to get window handles");
			Serenity.recordReportData().withTitle("Failure").andContents("Unable to get window handles");
			Assert.fail(e.getMessage());
		};
        return windowHandles;
    }

    public String pageSource() {
        String pageSource = null;
        try { 
			pageSource = getDriver().getPageSource();
        if(pageSource==null){
			throw new NullPointerException();
        } 
			} catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
			Settings.LOGGER.info("Unable to get page source");
			Serenity.recordReportData().withTitle("Failure").andContents("Unable to get page source");
			Assert.fail(e.getMessage());
		};
        return pageSource;
    }

    public void closeTab() {
        try{
			getDriver().close();
        	} 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
			Settings.LOGGER.info("Unable to close current tab");
			Serenity.recordReportData().withTitle("Failure").andContents("Unable to close current tab");
			Assert.fail(e.getMessage());
        }
		;
    }

    public void alertSwitch() {
        try{
			getDriver().switchTo().alert();
        	} 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
			Settings.LOGGER.info("Unable to switch to Alert");
			Serenity.recordReportData().withTitle("Failure").andContents("Unable to switch to Alert");
			Assert.fail(e.getMessage());
        }
		;
    }

    public void alertAccept() {
        try{
			getDriver().switchTo().alert().accept();
        	} 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
			Settings.LOGGER.info("Unable to accept Alert");
			Serenity.recordReportData().withTitle("Failure").andContents("Unable to accept Alert");
			Assert.fail(e.getMessage());
        }
		;
    }

    public void alertDismiss() {
        try{
			getDriver().switchTo().alert().dismiss();
        	} 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
			Settings.LOGGER.info("Unable to dismiss Alert");
			Serenity.recordReportData().withTitle("Failure").andContents("Unable to dismiss Alert");
			Assert.fail(e.getMessage());
        }
		;
    }

    public void inputForAlert(String input) {
        try{
			getDriver().switchTo().alert().sendKeys(input);
        	} 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
			Settings.LOGGER.info("Unable to enter " + input + "into Alert");
			Serenity.recordReportData().withTitle("Failure").andContents("Unable to enter " + input + "into Alert");
			Assert.fail(e.getMessage());
        }
		;
    }

    public void scrollUp() {
        try{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
        	} 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
			Settings.LOGGER.info("Unable to scroll to top of page");
			Serenity.recordReportData().withTitle("Failure").andContents("Unable to scroll to top of page");
			Assert.fail(e.getMessage());
        }
		;
    }

    public void scrollDown() {
        try{
			 JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        	} 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
			Settings.LOGGER.info("Unable to scroll to bottom of page");
			Serenity.recordReportData().withTitle("Failure").andContents("Unable to scroll to bottom of page");
			Assert.fail(e.getMessage());
        }
		;
    }

    public void scrollPage(int x, int y) {
        try{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("window.scrollBy("+x+","+y+")");
        	} 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
			Settings.LOGGER.info("Unable to scroll page");
			Serenity.recordReportData().withTitle("Failure").andContents("Unable to scroll page");
			Assert.fail(e.getMessage());
        }
		;
    }

    public void elementScroll(int x, int y) {
        try{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("window.scrollBy("+x+","+y+")");
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        }
		;
    }

    public void refreshPage() {
        try{
			getDriver().navigate().refresh();
        	} 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
			Settings.LOGGER.info("Unable to refresh page");
			Serenity.recordReportData().withTitle("Failure").andContents("Unable to refresh page");
			Assert.fail(e.getMessage());
        }
		;
    }

    public void takeScreenshot(String filePath) {
        try{
			TakesScreenshot scrShot =((TakesScreenshot)getDriver());
			File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
			File DestFile=new File(filePath);
			FileUtils.copyFile(SrcFile, DestFile);
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        }
		;
    }

    public By getLocator(String locatorName) {
        By locator = null;
        try{
			String className = locatorName.split("\\.")[0];
			Class<?> clazz = Class.forName("locators." + className);
			Field loc = clazz.getField(locatorName.split("\\.")[1]);
			locator = (By) loc.get(className);
        	} 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
			Settings.LOGGER.info("Unable to get locator");
			Serenity.recordReportData().withTitle("Failure").andContents("Unable to get locator");
			Assert.fail(e.getMessage());
        } 
		return locator;
    }

    public void clickUsingJS(String locatorName) {
        try{
			By locator = getLocator(locatorName);
			JavascriptExecutor executor = (JavascriptExecutor)getDriver();
			executor.executeScript("arguments[0].click();", $(locator));
        	} 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
			Settings.LOGGER.info("Unable to click using JS");
			Serenity.recordReportData().withTitle("Failure").andContents("Unable to click using JS");
			Assert.fail(e.getMessage());
        }
		;
    }

    public void dragAndDrop(String from, String to) {
        try{
			By fromLocator = getLocator(from);
			By toLocator = getLocator(to);
			new SerenityActions(getDriver()).dragAndDrop($(fromLocator), $(toLocator)).build().perform();
        	} 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
			Settings.LOGGER.info("Unable to drag and drop element");
			Serenity.recordReportData().withTitle("Failure").andContents("Unable to drag and drop element");
			Assert.fail(e.getMessage());
        }
		;
    }

    public void fileUpload(String filePath, String locatorName) {
        try{
			By locator = getLocator(locatorName);
			$(locator).sendKeys(filePath);
        	} 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
			Settings.LOGGER.info("Unable to upload file");
			Serenity.recordReportData().withTitle("Failure").andContents("Unable to upload file");
			Assert.fail(e.getMessage());
        }
		;
    }
}
