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

import java.util.ArrayList;
import java.util.Objects;
import org.openqa.selenium.*;
import net.serenitybdd.core.pages.SerenityActions;
import org.openqa.selenium.By;
import java.lang.reflect.Field;
import java.util.List;
import utils.UtilsMethodCodeGenerator;

public class GoogleImplementation extends PageObject {

    private WebDriver driver =getDriver();

    public void verifyFirstNameIsEnabled() {
        //The below function is for web element @FindBy(Google.firstName);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Google.firstName));
        	assertTrue("FirstName is not enabled", element.isEnabled());
        	Settings.LOGGER.info("User verifies the given firstName element is enabled");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			Serenity.recordReportData().withTitle("Failure").andContents("Unable to click");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        };
    }

    public String getAttributeFromFirstName(String attributeValue) {
        //This function is for web element @FindBy(Google.firstName);
        String valueOfAttribute = "";
        try{
			Settings.LOGGER.info("User gets attribute as "+attributeValue+" for firstName element");
        	valueOfAttribute = $(Google.firstName).getAttribute(attributeValue);
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not get value for attribute " + attributeValue);
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        }
		return valueOfAttribute;
    }

    public void verifyValueFromFirstName(String typeText) {
        //This function is for web element @FindBy(Google.firstName);
        assertTrue("Actual value: " + $(Google.firstName).getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,$(Google.firstName).getAttribute("value")));
        Settings.LOGGER.info("User verifies value: " + typeText + "for FirstName");
    }

    public String verifyAttributeValueForFirstName(String attributeName, String attributeValue) {
        //This function is for web element @FindBy(Google.firstName);
        try{
			assertTrue(StringUtils.equalsIgnoreCase(attributeValue,$(Google.firstName).getAttribute(attributeName)));
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        }
		return $(Google.firstName).getAttribute(attributeName);
    }

    public void hoverOverFirstName(String labelText) {
        //This function is for web element @FindBy(Google.firstName);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			serenityActions.moveToElement($(Google.firstName)).build().perform();
        	Settings.LOGGER.info("User successfully hovers over");
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void verifyFirstNameExists() {
        //This function is for web element @FindBy(Google.firstName);
        try{
			if(getDriver().findElements(Google.firstName).size()>0){;
        			Settings.LOGGER.info("firstName is present on Screen");}	
			 else{
				Assert.fail("firstName element is not present on Screen");
        			Settings.LOGGER.info("firstName element is not present on Screen");}	
		}
		catch(Exception e){
			Assert.fail("firstName element is not present on Screen");
        };
    }

    public void verifyChildElementsCountFirstName(int count) {
        //This function is for web element @FindBy(Google.firstName);
        List<WebElement> allChildElements = new ArrayList<>();
        try{
			WebElementFacade parentElement = $(Google.firstName);
			allChildElements = parentElement.findElements(By.xpath("*"));
			Assert.assertEquals(count, allChildElements.size());
        			Settings.LOGGER.info("firstName has" + allChildElements.size() + "child elements");	
		}
		catch(Exception e){
			Assert.fail("Actual child count - " + allChildElements.size());
        };
    }

    public void verifyCountOfElementsForFirstName(int count) {
        //This function is for web element @FindBy(Google.firstName);
        List<WebElement> allElements = new ArrayList<>();
        try{
			allElements = getDriver().findElements(Google.firstName);
			Assert.assertEquals(count, allElements.size());
        			Settings.LOGGER.info("firstName count is " + allElements.size());	
		}
		catch(Exception e){
			Assert.fail("Actual count - " + allElements.size());
        };
    }

    public void scrollToFirstNameElement() {
        //This function is for web element @FindBy(Google.firstName);
        try{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].scrollIntoView();", $(Google.firstName));
        			Settings.LOGGER.info(" Successful able to scroll to firstName element ");}	
			catch(Exception e){;
        			Assert.fail(" Unable to scroll to firstName element ");
        };
    }

    public void verifyFirstNameIsDisplayed() {
        //The below function is for web element @FindBy(Google.firstName);
        assertTrue("FirstName is not visible", $(Google.firstName).isDisplayed());
        Settings.LOGGER.info("User verifies firstName element is displayed");
    }

    public void verifyFirstNameContainsText(String typeText) {
        //The below function is for web element @FindBy(Google.firstName);
        try{
			assertTrue("Actual text: " + $(Google.firstName).getText(), StringUtils.containsIgnoreCase($(Google.firstName).getText(), typeText));
        	Settings.LOGGER.info("User verifies firstName contains " + typeText);
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void verifyAttributeContainsValueForFirstName(String attribute, String typeText) {
        //The below function is for web element @FindBy(Google.firstName);
        try{
			assertTrue("Actual value: " + $(Google.firstName).getAttribute(attribute), StringUtils.contains($(Google.firstName).getAttribute(attribute), typeText));
        	Settings.LOGGER.info("User verifies firstName contains " + typeText);
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void verifyFirstNameText(String typeText) {
        //The below function is for web element @FindBy(Google.firstName);
        try{
			assertTrue("Actual text: " + $(Google.firstName).getText(), StringUtils.equalsIgnoreCase(typeText,$(Google.firstName).getText()));
        	Settings.LOGGER.info("User gets the text of firstName element");
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }
}
