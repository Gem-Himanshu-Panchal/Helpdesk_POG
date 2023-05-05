package implementation;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.PageObject;
import com.gemini.generic.ui.utils.DriverManager;
import pageobjectgenerator.Settings;
import net.serenitybdd.core.Serenity;
import static org.junit.Assert.assertTrue;
import org.junit.Assert;
import static org.junit.Assert.assertFalse;
import locators.Google;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Keys;
import net.serenitybdd.core.pages.SerenityActions;
import org.openqa.selenium.By;

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

    public void clicksAndHold(String locator) {
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
}
