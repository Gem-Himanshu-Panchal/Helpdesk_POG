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
import utils.UtilsMethodCodeGenerator;

public class UtilsImplementation extends PageObject {

    public String getURL() {
        try{
			String text = getDriver().getCurrentUrl();
        	Settings.LOGGER.info("User successfully navigated Forward");
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        }
		return getDriver().getCurrentUrl();
    }

    public void verifyURL(String typeText) {
        try{
			String text = getDriver().getCurrentUrl();
        	assertTrue(text.equals(typeText));
        	Settings.LOGGER.info("User successfully navigated back");
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public String getTitle() {
        try{
			String text = getDriver().getTitle();
        	Settings.LOGGER.info("User successfully navigated Forward");
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        }
		return getDriver().getCurrentUrl();
    }

    public void verifyTitle(String typeText) {
        try{
			String title = getDriver().getTitle();
        	assertTrue("Actual title: " + title, title.equals(typeText));
        	Settings.LOGGER.info("User successfully verifies title");
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void navigateTo(String url) {
        try{
			getDriver().navigate().to(url);
        	Settings.LOGGER.info("User successfully navigated Forward");
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void forwardNavigation() {
        try{
			getDriver().navigate().forward();
        	Settings.LOGGER.info("User successfully navigated Forward");
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void backwardNavigation() {
        try{
			getDriver().navigate().back();
        	Settings.LOGGER.info("User successfully navigated back");
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+ e);
        };
    }

    public void switchActiveElement() {
        try{
			getDriver().switchTo().activeElement();
        	Settings.LOGGER.info("User successfully switched to active element");
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+ e);
        };
    }

    public void switchDefaultContent() {
        try{
			getDriver().switchTo().defaultContent();
        	Settings.LOGGER.info("User successfully switched to default content");
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void switchParentFrame() {
        try{
			getDriver().switchTo().parentFrame();
        	Settings.LOGGER.info("User successfully switched to parent frame");
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+ e);
        };
    }

    public void switchFrame(String nameOrId) {
        try{
			getDriver().switchTo().frame(nameOrId);
        	Settings.LOGGER.info("User successfully switched to frame");
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+ e);
        };
    }

    public void switchFrame(int index) {
        try{
			getDriver().switchTo().frame(index);
        	Settings.LOGGER.info("User successfully switched to frame");
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+ e);
        };
    }

    public void switchWindow(String nameOrHandle) {
        try{
			getDriver().switchTo().window(nameOrHandle);
        	Settings.LOGGER.info("User successfully switched to window");
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+ e);
        };
    }

    public void wait(int duration) {
        try{
			waitABit(duration);
        	Settings.LOGGER.info("User waits for " + duration + " seconds");
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+ e);
        };
    }

    public void clicksAndHold(By locator) {
        try{
			new SerenityActions(getDriver()).moveToElement($(locator)).clickAndHold().build().perform();
        	Settings.LOGGER.info("User successfully clicks and holds " + locator + " element");
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+ e);
        };
    }

    public void tearDown() {
        try{
			getDriver().quit();
        	Settings.LOGGER.info("User successfully closed driver");
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void openApplication() {
        getDriver().get(Settings.URL);
        Settings.LOGGER.info("User launches the application");
    }

    public void maximizeBrowserToDefault() {
        try{
			getDriver().manage().window().maximize();
        	Settings.LOGGER.info("Verify Browser Maximized to Default Size ","Browser Maximization successful.");	}
			catch(Exception e){;
        			Settings.LOGGER.info("Verify Browser Maximized to Default Size ","Unable to maximize browser.");
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void minimizeGivenBrowser() {
        try{
			getDriver().manage().window().minimize();
        			Settings.LOGGER.info("Verify Browser Maximized to Default Size ","Browser Maximization successful.");	}
		catch(Exception e){;
        			Settings.LOGGER.info("Verify Browser Maximized to Default Size ","Unable to maximize browser.");
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public Object browserSize() {
        Object size = getDriver().manage().window().getSize();
        Integer sizeOfBrowser = null;
        if(size!=null);
        	sizeOfBrowser = Integer.valueOf(size.toString());
        return sizeOfBrowser;
    }

    public void setSizeOfBrowser(int width, int height) {
        try{
			Dimension newDimension = new Dimension(width, height);
			getDriver().manage().window().setSize(newDimension)		;
        	Settings.LOGGER.info("User successfully minimizes browser");
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void setPositionOfBrowser(int x, int y) {
        try{
			getDriver().manage().window().setPosition(new Point(x,y));
        	Settings.LOGGER.info("User successfully minimizes browser");
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public Object browserPosition() {
        Object position = getDriver().manage().window().getPosition();
        Integer positionOfBrowser = null;
        if(position!=null)	;
        	positionOfBrowser = Integer.valueOf(position.toString());
        return positionOfBrowser;
    }

    public Object windowHandle() {
        String windowHandle = getDriver().getWindowHandle();
        if(windowHandle==null){
			Assert.fail("Unable to get window handle");
        }
		;
        return windowHandle;
    }

    public String windowHandles() {
        String windowHandles = getDriver().getWindowHandles().toString();
        if(windowHandles==null){
			Assert.fail("Unable to get window handles");
        }
		;
        return windowHandles;
    }

    public String pageSource() {
        String pageSource = getDriver().getPageSource();
        if(pageSource==null){
			Assert.fail("Unable to get page source");
        }
		;
        return pageSource;
    }

    public void closeTab() {
        try{
			getDriver().close();
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        }
		;
    }

    public void alertSwitch() {
        try{
			getDriver().switchTo().alert();
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        }
		;
    }

    public void alertAccept() {
        try{
			getDriver().switchTo().alert().accept();
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        }
		;
    }

    public void alertDismiss() {
        try{
			getDriver().switchTo().alert().dismiss();
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        }
		;
    }

    public void inputForAlert(String input) {
        try{
			getDriver().switchTo().alert().sendKeys(input);
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        }
		;
    }

    public void scrollUp() {
        try{
			JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();
			js.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        }
		;
    }

    public void scrollDown() {
        try{
			 JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();
			js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        }
		;
    }

    public void scrollPage(int x, int y) {
        try{
			JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();
			js.executeScript("window.scrollBy("+x+","+y+")");
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        }
		;
    }

    public void elementScroll(int x, int y) {
        try{
			JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();
			js.executeScript("window.scrollBy("+x+","+y+")");
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        }
		;
    }

    public void urlNavigation(String url) {
        try{
			getDriver().navigate().to(url);
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
}
