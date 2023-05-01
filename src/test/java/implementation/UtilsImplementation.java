package implementation;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.PageObject;
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
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void openApplication() {
        getDriver().get(Settings.URL);
        Settings.LOGGER.info("User launches the application");
    }
}
