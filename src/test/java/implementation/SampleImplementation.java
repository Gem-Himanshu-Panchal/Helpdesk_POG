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

public class SampleImplementation extends PageObject {

    private WebDriver driver =getDriver();

    public void typeTextIntoFirstName(String typeText) {
        //The below function is for web element @FindBy(Sample.firstName);
        try{
			$(Sample.firstName).wait(Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut")));
			typeInto($(Sample.firstName),typeText);
        	Settings.LOGGER.info("User enters "+typeText+" as value");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not enter " + typeText + " into FirstName");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void typeTextAndEnterForFirstName(String typeText) {
        //The below function is for web element @FindBy(Sample.firstName);
        try{
			typeInto($(Sample.firstName),typeText);
        Actions action = new Actions(DriverManager.getWebDriver());;
        action.sendKeys(Keys.ENTER);;
        	Settings.LOGGER.info("User enters "+typeText+" as value and presses enter");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not enter " + typeText + " into FirstName");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void typeTextAndTabForFirstName(String typeText) {
        //The below function is for web element @FindBy(Sample.firstName);
        try{
			typeInto($(Sample.firstName),typeText);
        	Settings.LOGGER.info("User enters "+typeText+" as value and presses Tab");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not enter " + typeText + " into FirstName");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public String getTextFromFirstName() {
        //The below function is for web element @FindBy(Sample.firstName);
        String text = "";
        try{
			text = $(Sample.firstName).getText();
			Settings.LOGGER.info("User gets the text of firstName element");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not get text of FirstName");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        }
		return text;
    }

    public void verifyFirstNameIsEnabled() {
        //The below function is for web element @FindBy(Sample.firstName);
        assertTrue("FirstName is not enabled", $(Sample.firstName).isEnabled());
        Settings.LOGGER.info("User verifies the given firstName element is enabled");
    }

    public void clearFirstName() {
        //The below function is for web element @FindBy(Sample.firstName);
        try{
			$(Sample.firstName).clear();
        	Settings.LOGGER.info("User deletes the value for firstName element");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not clear FirstName");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public String getAttributeFromFirstName(String attributeValue) {
        //This function is for web element @FindBy(Sample.firstName);
        String valueOfAttribute = "";
        try{
			Settings.LOGGER.info("User gets attribute as "+attributeValue+" for firstName element");
        	valueOfAttribute = $(Sample.firstName).getAttribute(attributeValue);
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not get value for attribute " + attributeValue);
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        }
		return valueOfAttribute;
    }

    public void verifyValueFromFirstName(String typeText) {
        //This function is for web element @FindBy(Sample.firstName);
        assertTrue("Actual value: " + $(Sample.firstName).getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,$(Sample.firstName).getAttribute("value")));
        Settings.LOGGER.info("User verifies value: " + typeText + "for FirstName");
    }

    public String verifyAttributeValueForFirstName(String attributeName, String attributeValue) {
        //This function is for web element @FindBy(Sample.firstName);
        try{
			assertTrue(StringUtils.equalsIgnoreCase(attributeValue,$(Sample.firstName).getAttribute(attributeName)));
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        }
		return $(Sample.firstName).getAttribute(attributeName);
    }

    public void verifyValueClearedForFirstName() {
        //This function is for web element @FindBy(Sample.firstName);
        assertTrue("Actual value: " + $(Sample.firstName).getAttribute("value"), StringUtils.equalsIgnoreCase("", $(Sample.firstName).getAttribute("value")));
        Settings.LOGGER.info("User verifies value is cleared for FirstName");
    }

    public void hoverOverFirstName(String labelText) {
        //This function is for web element @FindBy(Sample.firstName);
        try{
			getDriver().navigate().forward();
        	Settings.LOGGER.info("User successfully navigated Forward");
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void verifyFirstNameExists() {
        //This function is for web element @FindBy(Sample.firstName);
        try{
			if(getDriver().findElements(Sample.firstName).size()>0){;
        			Settings.LOGGER.info("firstName is present on Screen");}	
			 else{
				Assert.fail("firstName element is not present on Screen");
        			Settings.LOGGER.info("firstName element is not present on Screen");}	
		}
		catch(Exception e){
			Assert.fail("firstName element is not present on Screen");
        };
    }

    public void scrollToFirstNameElement() {
        //This function is for web element @FindBy(Sample.firstName);
        try{
			JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();
			js.executeScript("arguments[0].scrollIntoView();", Sample.firstName);
        			Settings.LOGGER.info(" Successful able to scroll to firstName element ");}	
			catch(Exception e){;
        			Assert.fail(" Unable to scroll to firstName element ");
        };
    }

    public void verifyFirstNameIsDisplayed() {
        //The below function is for web element @FindBy(Sample.firstName);
        assertTrue("FirstName is not visible", $(Sample.firstName).isDisplayed());
        Settings.LOGGER.info("User verifies firstName element is displayed");
    }

    public void verifyFirstNameContainsText(String typeText) {
        //The below function is for web element @FindBy(Sample.firstName);
        try{
			assertTrue("Actual text: " + $(Sample.firstName).getText(), StringUtils.containsIgnoreCase($(Sample.firstName).getText(), typeText));
        	Settings.LOGGER.info("User verifies firstName contains " + typeText);
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void verifyFirstNameText(String typeText) {
        //The below function is for web element @FindBy(Sample.firstName);
        try{
			assertTrue("Actual text: " + $(Sample.firstName).getText(), StringUtils.equalsIgnoreCase(typeText,$(Sample.firstName).getText()));
        	Settings.LOGGER.info("User gets the text of firstName element");
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void typeTextIntoPassword(String typeText) {
        //The below function is for web element @FindBy(Sample.password);
        try{
			$(Sample.password).wait(Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut")));
			typeInto($(Sample.password),typeText);
        	Settings.LOGGER.info("User enters "+typeText+" as value");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not enter " + typeText + " into Password");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void typeTextAndEnterForPassword(String typeText) {
        //The below function is for web element @FindBy(Sample.password);
        try{
			typeInto($(Sample.password),typeText);
        Actions action = new Actions(DriverManager.getWebDriver());;
        action.sendKeys(Keys.ENTER);;
        	Settings.LOGGER.info("User enters "+typeText+" as value and presses enter");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not enter " + typeText + " into Password");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void typeTextAndTabForPassword(String typeText) {
        //The below function is for web element @FindBy(Sample.password);
        try{
			typeInto($(Sample.password),typeText);
        	Settings.LOGGER.info("User enters "+typeText+" as value and presses Tab");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not enter " + typeText + " into Password");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public String getTextFromPassword() {
        //The below function is for web element @FindBy(Sample.password);
        String text = "";
        try{
			text = $(Sample.password).getText();
			Settings.LOGGER.info("User gets the text of password element");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not get text of Password");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        }
		return text;
    }

    public void verifyPasswordIsEnabled() {
        //The below function is for web element @FindBy(Sample.password);
        assertTrue("Password is not enabled", $(Sample.password).isEnabled());
        Settings.LOGGER.info("User verifies the given password element is enabled");
    }

    public void clearPassword() {
        //The below function is for web element @FindBy(Sample.password);
        try{
			$(Sample.password).clear();
        	Settings.LOGGER.info("User deletes the value for password element");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not clear Password");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public String getAttributeFromPassword(String attributeValue) {
        //This function is for web element @FindBy(Sample.password);
        String valueOfAttribute = "";
        try{
			Settings.LOGGER.info("User gets attribute as "+attributeValue+" for password element");
        	valueOfAttribute = $(Sample.password).getAttribute(attributeValue);
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not get value for attribute " + attributeValue);
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        }
		return valueOfAttribute;
    }

    public void verifyValueFromPassword(String typeText) {
        //This function is for web element @FindBy(Sample.password);
        assertTrue("Actual value: " + $(Sample.password).getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,$(Sample.password).getAttribute("value")));
        Settings.LOGGER.info("User verifies value: " + typeText + "for Password");
    }

    public String verifyAttributeValueForPassword(String attributeName, String attributeValue) {
        //This function is for web element @FindBy(Sample.password);
        try{
			assertTrue(StringUtils.equalsIgnoreCase(attributeValue,$(Sample.password).getAttribute(attributeName)));
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        }
		return $(Sample.password).getAttribute(attributeName);
    }

    public void verifyValueClearedForPassword() {
        //This function is for web element @FindBy(Sample.password);
        assertTrue("Actual value: " + $(Sample.password).getAttribute("value"), StringUtils.equalsIgnoreCase("", $(Sample.password).getAttribute("value")));
        Settings.LOGGER.info("User verifies value is cleared for Password");
    }

    public void hoverOverPassword(String labelText) {
        //This function is for web element @FindBy(Sample.password);
        try{
			getDriver().navigate().forward();
        	Settings.LOGGER.info("User successfully navigated Forward");
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void verifyPasswordExists() {
        //This function is for web element @FindBy(Sample.password);
        try{
			if(getDriver().findElements(Sample.password).size()>0){;
        			Settings.LOGGER.info("password is present on Screen");}	
			 else{
				Assert.fail("password element is not present on Screen");
        			Settings.LOGGER.info("password element is not present on Screen");}	
		}
		catch(Exception e){
			Assert.fail("password element is not present on Screen");
        };
    }

    public void scrollToPasswordElement() {
        //This function is for web element @FindBy(Sample.password);
        try{
			JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();
			js.executeScript("arguments[0].scrollIntoView();", Sample.password);
        			Settings.LOGGER.info(" Successful able to scroll to password element ");}	
			catch(Exception e){;
        			Assert.fail(" Unable to scroll to password element ");
        };
    }

    public void verifyPasswordIsDisplayed() {
        //The below function is for web element @FindBy(Sample.password);
        assertTrue("Password is not visible", $(Sample.password).isDisplayed());
        Settings.LOGGER.info("User verifies password element is displayed");
    }

    public void verifyPasswordContainsText(String typeText) {
        //The below function is for web element @FindBy(Sample.password);
        try{
			assertTrue("Actual text: " + $(Sample.password).getText(), StringUtils.containsIgnoreCase($(Sample.password).getText(), typeText));
        	Settings.LOGGER.info("User verifies password contains " + typeText);
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void verifyPasswordText(String typeText) {
        //The below function is for web element @FindBy(Sample.password);
        try{
			assertTrue("Actual text: " + $(Sample.password).getText(), StringUtils.equalsIgnoreCase(typeText,$(Sample.password).getText()));
        	Settings.LOGGER.info("User gets the text of password element");
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void typeTextIntoLastName(String typeText) {
        //The below function is for web element @FindBy(Sample.lastName);
        try{
			$(Sample.lastName).wait(Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut")));
			typeInto($(Sample.lastName),typeText);
        	Settings.LOGGER.info("User enters "+typeText+" as value");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not enter " + typeText + " into LastName");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void typeTextAndEnterForLastName(String typeText) {
        //The below function is for web element @FindBy(Sample.lastName);
        try{
			typeInto($(Sample.lastName),typeText);
        Actions action = new Actions(DriverManager.getWebDriver());;
        action.sendKeys(Keys.ENTER);;
        	Settings.LOGGER.info("User enters "+typeText+" as value and presses enter");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not enter " + typeText + " into LastName");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void typeTextAndTabForLastName(String typeText) {
        //The below function is for web element @FindBy(Sample.lastName);
        try{
			typeInto($(Sample.lastName),typeText);
        	Settings.LOGGER.info("User enters "+typeText+" as value and presses Tab");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not enter " + typeText + " into LastName");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public String getTextFromLastName() {
        //The below function is for web element @FindBy(Sample.lastName);
        String text = "";
        try{
			text = $(Sample.lastName).getText();
			Settings.LOGGER.info("User gets the text of lastName element");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not get text of LastName");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        }
		return text;
    }

    public void verifyLastNameIsEnabled() {
        //The below function is for web element @FindBy(Sample.lastName);
        assertTrue("LastName is not enabled", $(Sample.lastName).isEnabled());
        Settings.LOGGER.info("User verifies the given lastName element is enabled");
    }

    public void clearLastName() {
        //The below function is for web element @FindBy(Sample.lastName);
        try{
			$(Sample.lastName).clear();
        	Settings.LOGGER.info("User deletes the value for lastName element");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not clear LastName");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public String getAttributeFromLastName(String attributeValue) {
        //This function is for web element @FindBy(Sample.lastName);
        String valueOfAttribute = "";
        try{
			Settings.LOGGER.info("User gets attribute as "+attributeValue+" for lastName element");
        	valueOfAttribute = $(Sample.lastName).getAttribute(attributeValue);
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not get value for attribute " + attributeValue);
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        }
		return valueOfAttribute;
    }

    public void verifyValueFromLastName(String typeText) {
        //This function is for web element @FindBy(Sample.lastName);
        assertTrue("Actual value: " + $(Sample.lastName).getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,$(Sample.lastName).getAttribute("value")));
        Settings.LOGGER.info("User verifies value: " + typeText + "for LastName");
    }

    public String verifyAttributeValueForLastName(String attributeName, String attributeValue) {
        //This function is for web element @FindBy(Sample.lastName);
        try{
			assertTrue(StringUtils.equalsIgnoreCase(attributeValue,$(Sample.lastName).getAttribute(attributeName)));
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        }
		return $(Sample.lastName).getAttribute(attributeName);
    }

    public void verifyValueClearedForLastName() {
        //This function is for web element @FindBy(Sample.lastName);
        assertTrue("Actual value: " + $(Sample.lastName).getAttribute("value"), StringUtils.equalsIgnoreCase("", $(Sample.lastName).getAttribute("value")));
        Settings.LOGGER.info("User verifies value is cleared for LastName");
    }

    public void hoverOverLastName(String labelText) {
        //This function is for web element @FindBy(Sample.lastName);
        try{
			getDriver().navigate().forward();
        	Settings.LOGGER.info("User successfully navigated Forward");
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void verifyLastNameExists() {
        //This function is for web element @FindBy(Sample.lastName);
        try{
			if(getDriver().findElements(Sample.lastName).size()>0){;
        			Settings.LOGGER.info("lastName is present on Screen");}	
			 else{
				Assert.fail("lastName element is not present on Screen");
        			Settings.LOGGER.info("lastName element is not present on Screen");}	
		}
		catch(Exception e){
			Assert.fail("lastName element is not present on Screen");
        };
    }

    public void scrollToLastNameElement() {
        //This function is for web element @FindBy(Sample.lastName);
        try{
			JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();
			js.executeScript("arguments[0].scrollIntoView();", Sample.lastName);
        			Settings.LOGGER.info(" Successful able to scroll to lastName element ");}	
			catch(Exception e){;
        			Assert.fail(" Unable to scroll to lastName element ");
        };
    }

    public void verifyLastNameIsDisplayed() {
        //The below function is for web element @FindBy(Sample.lastName);
        assertTrue("LastName is not visible", $(Sample.lastName).isDisplayed());
        Settings.LOGGER.info("User verifies lastName element is displayed");
    }

    public void verifyLastNameContainsText(String typeText) {
        //The below function is for web element @FindBy(Sample.lastName);
        try{
			assertTrue("Actual text: " + $(Sample.lastName).getText(), StringUtils.containsIgnoreCase($(Sample.lastName).getText(), typeText));
        	Settings.LOGGER.info("User verifies lastName contains " + typeText);
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void verifyLastNameText(String typeText) {
        //The below function is for web element @FindBy(Sample.lastName);
        try{
			assertTrue("Actual text: " + $(Sample.lastName).getText(), StringUtils.equalsIgnoreCase(typeText,$(Sample.lastName).getText()));
        	Settings.LOGGER.info("User gets the text of lastName element");
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void typeTextIntoHeading(String typeText) {
        //The below function is for web element @FindBy(Sample.heading);
        try{
			$(Sample.heading).wait(Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut")));
			typeInto($(Sample.heading),typeText);
        	Settings.LOGGER.info("User enters "+typeText+" as value");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not enter " + typeText + " into Heading");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void typeTextAndEnterForHeading(String typeText) {
        //The below function is for web element @FindBy(Sample.heading);
        try{
			typeInto($(Sample.heading),typeText);
        Actions action = new Actions(DriverManager.getWebDriver());;
        action.sendKeys(Keys.ENTER);;
        	Settings.LOGGER.info("User enters "+typeText+" as value and presses enter");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not enter " + typeText + " into Heading");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void typeTextAndTabForHeading(String typeText) {
        //The below function is for web element @FindBy(Sample.heading);
        try{
			typeInto($(Sample.heading),typeText);
        	Settings.LOGGER.info("User enters "+typeText+" as value and presses Tab");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not enter " + typeText + " into Heading");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public String getTextFromHeading() {
        //The below function is for web element @FindBy(Sample.heading);
        String text = "";
        try{
			text = $(Sample.heading).getText();
			Settings.LOGGER.info("User gets the text of heading element");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not get text of Heading");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        }
		return text;
    }

    public void verifyHeadingIsEnabled() {
        //The below function is for web element @FindBy(Sample.heading);
        assertTrue("Heading is not enabled", $(Sample.heading).isEnabled());
        Settings.LOGGER.info("User verifies the given heading element is enabled");
    }

    public void clearHeading() {
        //The below function is for web element @FindBy(Sample.heading);
        try{
			$(Sample.heading).clear();
        	Settings.LOGGER.info("User deletes the value for heading element");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not clear Heading");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public String getAttributeFromHeading(String attributeValue) {
        //This function is for web element @FindBy(Sample.heading);
        String valueOfAttribute = "";
        try{
			Settings.LOGGER.info("User gets attribute as "+attributeValue+" for heading element");
        	valueOfAttribute = $(Sample.heading).getAttribute(attributeValue);
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not get value for attribute " + attributeValue);
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        }
		return valueOfAttribute;
    }

    public void verifyValueFromHeading(String typeText) {
        //This function is for web element @FindBy(Sample.heading);
        assertTrue("Actual value: " + $(Sample.heading).getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,$(Sample.heading).getAttribute("value")));
        Settings.LOGGER.info("User verifies value: " + typeText + "for Heading");
    }

    public String verifyAttributeValueForHeading(String attributeName, String attributeValue) {
        //This function is for web element @FindBy(Sample.heading);
        try{
			assertTrue(StringUtils.equalsIgnoreCase(attributeValue,$(Sample.heading).getAttribute(attributeName)));
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        }
		return $(Sample.heading).getAttribute(attributeName);
    }

    public void verifyValueClearedForHeading() {
        //This function is for web element @FindBy(Sample.heading);
        assertTrue("Actual value: " + $(Sample.heading).getAttribute("value"), StringUtils.equalsIgnoreCase("", $(Sample.heading).getAttribute("value")));
        Settings.LOGGER.info("User verifies value is cleared for Heading");
    }

    public void hoverOverHeading(String labelText) {
        //This function is for web element @FindBy(Sample.heading);
        try{
			getDriver().navigate().forward();
        	Settings.LOGGER.info("User successfully navigated Forward");
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void verifyHeadingExists() {
        //This function is for web element @FindBy(Sample.heading);
        try{
			if(getDriver().findElements(Sample.heading).size()>0){;
        			Settings.LOGGER.info("heading is present on Screen");}	
			 else{
				Assert.fail("heading element is not present on Screen");
        			Settings.LOGGER.info("heading element is not present on Screen");}	
		}
		catch(Exception e){
			Assert.fail("heading element is not present on Screen");
        };
    }

    public void scrollToHeadingElement() {
        //This function is for web element @FindBy(Sample.heading);
        try{
			JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();
			js.executeScript("arguments[0].scrollIntoView();", Sample.heading);
        			Settings.LOGGER.info(" Successful able to scroll to heading element ");}	
			catch(Exception e){;
        			Assert.fail(" Unable to scroll to heading element ");
        };
    }

    public void verifyHeadingIsDisplayed() {
        //The below function is for web element @FindBy(Sample.heading);
        assertTrue("Heading is not visible", $(Sample.heading).isDisplayed());
        Settings.LOGGER.info("User verifies heading element is displayed");
    }

    public void verifyHeadingContainsText(String typeText) {
        //The below function is for web element @FindBy(Sample.heading);
        try{
			assertTrue("Actual text: " + $(Sample.heading).getText(), StringUtils.containsIgnoreCase($(Sample.heading).getText(), typeText));
        	Settings.LOGGER.info("User verifies heading contains " + typeText);
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void verifyHeadingText(String typeText) {
        //The below function is for web element @FindBy(Sample.heading);
        try{
			assertTrue("Actual text: " + $(Sample.heading).getText(), StringUtils.equalsIgnoreCase(typeText,$(Sample.heading).getText()));
        	Settings.LOGGER.info("User gets the text of heading element");
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void typeTextIntoBirthday(String typeText) {
        //The below function is for web element @FindBy(Sample.birthday);
        try{
			$(Sample.birthday).wait(Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut")));
			typeInto($(Sample.birthday),typeText);
        	Settings.LOGGER.info("User enters "+typeText+" as value");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not enter " + typeText + " into Birthday");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void typeTextAndEnterForBirthday(String typeText) {
        //The below function is for web element @FindBy(Sample.birthday);
        try{
			typeInto($(Sample.birthday),typeText);
        Actions action = new Actions(DriverManager.getWebDriver());;
        action.sendKeys(Keys.ENTER);;
        	Settings.LOGGER.info("User enters "+typeText+" as value and presses enter");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not enter " + typeText + " into Birthday");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void typeTextAndTabForBirthday(String typeText) {
        //The below function is for web element @FindBy(Sample.birthday);
        try{
			typeInto($(Sample.birthday),typeText);
        	Settings.LOGGER.info("User enters "+typeText+" as value and presses Tab");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not enter " + typeText + " into Birthday");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public String getTextFromBirthday() {
        //The below function is for web element @FindBy(Sample.birthday);
        String text = "";
        try{
			text = $(Sample.birthday).getText();
			Settings.LOGGER.info("User gets the text of birthday element");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not get text of Birthday");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        }
		return text;
    }

    public void verifyBirthdayIsEnabled() {
        //The below function is for web element @FindBy(Sample.birthday);
        assertTrue("Birthday is not enabled", $(Sample.birthday).isEnabled());
        Settings.LOGGER.info("User verifies the given birthday element is enabled");
    }

    public void clearBirthday() {
        //The below function is for web element @FindBy(Sample.birthday);
        try{
			$(Sample.birthday).clear();
        	Settings.LOGGER.info("User deletes the value for birthday element");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not clear Birthday");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public String getAttributeFromBirthday(String attributeValue) {
        //This function is for web element @FindBy(Sample.birthday);
        String valueOfAttribute = "";
        try{
			Settings.LOGGER.info("User gets attribute as "+attributeValue+" for birthday element");
        	valueOfAttribute = $(Sample.birthday).getAttribute(attributeValue);
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not get value for attribute " + attributeValue);
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        }
		return valueOfAttribute;
    }

    public void verifyValueFromBirthday(String typeText) {
        //This function is for web element @FindBy(Sample.birthday);
        assertTrue("Actual value: " + $(Sample.birthday).getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,$(Sample.birthday).getAttribute("value")));
        Settings.LOGGER.info("User verifies value: " + typeText + "for Birthday");
    }

    public String verifyAttributeValueForBirthday(String attributeName, String attributeValue) {
        //This function is for web element @FindBy(Sample.birthday);
        try{
			assertTrue(StringUtils.equalsIgnoreCase(attributeValue,$(Sample.birthday).getAttribute(attributeName)));
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        }
		return $(Sample.birthday).getAttribute(attributeName);
    }

    public void verifyValueClearedForBirthday() {
        //This function is for web element @FindBy(Sample.birthday);
        assertTrue("Actual value: " + $(Sample.birthday).getAttribute("value"), StringUtils.equalsIgnoreCase("", $(Sample.birthday).getAttribute("value")));
        Settings.LOGGER.info("User verifies value is cleared for Birthday");
    }

    public void hoverOverBirthday(String labelText) {
        //This function is for web element @FindBy(Sample.birthday);
        try{
			getDriver().navigate().forward();
        	Settings.LOGGER.info("User successfully navigated Forward");
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void verifyBirthdayExists() {
        //This function is for web element @FindBy(Sample.birthday);
        try{
			if(getDriver().findElements(Sample.birthday).size()>0){;
        			Settings.LOGGER.info("birthday is present on Screen");}	
			 else{
				Assert.fail("birthday element is not present on Screen");
        			Settings.LOGGER.info("birthday element is not present on Screen");}	
		}
		catch(Exception e){
			Assert.fail("birthday element is not present on Screen");
        };
    }

    public void scrollToBirthdayElement() {
        //This function is for web element @FindBy(Sample.birthday);
        try{
			JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();
			js.executeScript("arguments[0].scrollIntoView();", Sample.birthday);
        			Settings.LOGGER.info(" Successful able to scroll to birthday element ");}	
			catch(Exception e){;
        			Assert.fail(" Unable to scroll to birthday element ");
        };
    }

    public void verifyBirthdayIsDisplayed() {
        //The below function is for web element @FindBy(Sample.birthday);
        assertTrue("Birthday is not visible", $(Sample.birthday).isDisplayed());
        Settings.LOGGER.info("User verifies birthday element is displayed");
    }

    public void verifyBirthdayContainsText(String typeText) {
        //The below function is for web element @FindBy(Sample.birthday);
        try{
			assertTrue("Actual text: " + $(Sample.birthday).getText(), StringUtils.containsIgnoreCase($(Sample.birthday).getText(), typeText));
        	Settings.LOGGER.info("User verifies birthday contains " + typeText);
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void verifyBirthdayText(String typeText) {
        //The below function is for web element @FindBy(Sample.birthday);
        try{
			assertTrue("Actual text: " + $(Sample.birthday).getText(), StringUtils.equalsIgnoreCase(typeText,$(Sample.birthday).getText()));
        	Settings.LOGGER.info("User gets the text of birthday element");
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void selectCss() {
        //The below function is for web element @FindBy(Sample.css);
        try{
			$(Sample.css).click();
        	Settings.LOGGER.info("User selects the css element");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not select Css");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void verifyCssIsEnabled() {
        //The below function is for web element @FindBy(Sample.css);
        assertTrue("Css is not enabled", $(Sample.css).isEnabled());
        Settings.LOGGER.info("User verifies the given css element is enabled");
    }

    public void verifyCssIsSelected() {
        //This function is for web element @FindBy(Sample.css);
        assertTrue("Css is not selected", $(Sample.css).isSelected());
        Settings.LOGGER.info("User verifies css is selected");
    }

    public String getAttributeFromCss(String attributeValue) {
        //This function is for web element @FindBy(Sample.css);
        String valueOfAttribute = "";
        try{
			Settings.LOGGER.info("User gets attribute as "+attributeValue+" for css element");
        	valueOfAttribute = $(Sample.css).getAttribute(attributeValue);
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not get value for attribute " + attributeValue);
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        }
		return valueOfAttribute;
    }

    public void verifyValueFromCss(String typeText) {
        //This function is for web element @FindBy(Sample.css);
        assertTrue("Actual value: " + $(Sample.css).getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,$(Sample.css).getAttribute("value")));
        Settings.LOGGER.info("User verifies value: " + typeText + "for Css");
    }

    public void hoverOverCss(String labelText) {
        //This function is for web element @FindBy(Sample.css);
        try{
			getDriver().navigate().forward();
        	Settings.LOGGER.info("User successfully navigated Forward");
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void verifyCssExists() {
        //This function is for web element @FindBy(Sample.css);
        try{
			if(getDriver().findElements(Sample.css).size()>0){;
        			Settings.LOGGER.info("css is present on Screen");}	
			 else{
				Assert.fail("css element is not present on Screen");
        			Settings.LOGGER.info("css element is not present on Screen");}	
		}
		catch(Exception e){
			Assert.fail("css element is not present on Screen");
        };
    }

    public void scrollToCssElement() {
        //This function is for web element @FindBy(Sample.css);
        try{
			JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();
			js.executeScript("arguments[0].scrollIntoView();", Sample.css);
        			Settings.LOGGER.info(" Successful able to scroll to css element ");}	
			catch(Exception e){;
        			Assert.fail(" Unable to scroll to css element ");
        };
    }

    public void verifyCssIsDisplayed() {
        //The below function is for web element @FindBy(Sample.css);
        assertTrue("Css is not visible", $(Sample.css).isDisplayed());
        Settings.LOGGER.info("User verifies css element is displayed");
    }

    public void verifyCssContainsText(String typeText) {
        //The below function is for web element @FindBy(Sample.css);
        try{
			assertTrue("Actual text: " + $(Sample.css).getText(), StringUtils.containsIgnoreCase($(Sample.css).getText(), typeText));
        	Settings.LOGGER.info("User verifies css contains " + typeText);
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void verifyCssText(String typeText) {
        //The below function is for web element @FindBy(Sample.css);
        try{
			assertTrue("Actual text: " + $(Sample.css).getText(), StringUtils.equalsIgnoreCase(typeText,$(Sample.css).getText()));
        	Settings.LOGGER.info("User gets the text of css element");
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void selectJavascript() {
        //The below function is for web element @FindBy(Sample.javascript);
        try{
			$(Sample.javascript).click();
        	Settings.LOGGER.info("User selects the javascript element");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not select Javascript");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void verifyJavascriptIsEnabled() {
        //The below function is for web element @FindBy(Sample.javascript);
        assertTrue("Javascript is not enabled", $(Sample.javascript).isEnabled());
        Settings.LOGGER.info("User verifies the given javascript element is enabled");
    }

    public void verifyJavascriptIsSelected() {
        //This function is for web element @FindBy(Sample.javascript);
        assertTrue("Javascript is not selected", $(Sample.javascript).isSelected());
        Settings.LOGGER.info("User verifies javascript is selected");
    }

    public String getAttributeFromJavascript(String attributeValue) {
        //This function is for web element @FindBy(Sample.javascript);
        String valueOfAttribute = "";
        try{
			Settings.LOGGER.info("User gets attribute as "+attributeValue+" for javascript element");
        	valueOfAttribute = $(Sample.javascript).getAttribute(attributeValue);
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not get value for attribute " + attributeValue);
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        }
		return valueOfAttribute;
    }

    public void verifyValueFromJavascript(String typeText) {
        //This function is for web element @FindBy(Sample.javascript);
        assertTrue("Actual value: " + $(Sample.javascript).getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,$(Sample.javascript).getAttribute("value")));
        Settings.LOGGER.info("User verifies value: " + typeText + "for Javascript");
    }

    public void hoverOverJavascript(String labelText) {
        //This function is for web element @FindBy(Sample.javascript);
        try{
			getDriver().navigate().forward();
        	Settings.LOGGER.info("User successfully navigated Forward");
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void verifyJavascriptExists() {
        //This function is for web element @FindBy(Sample.javascript);
        try{
			if(getDriver().findElements(Sample.javascript).size()>0){;
        			Settings.LOGGER.info("javascript is present on Screen");}	
			 else{
				Assert.fail("javascript element is not present on Screen");
        			Settings.LOGGER.info("javascript element is not present on Screen");}	
		}
		catch(Exception e){
			Assert.fail("javascript element is not present on Screen");
        };
    }

    public void scrollToJavascriptElement() {
        //This function is for web element @FindBy(Sample.javascript);
        try{
			JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();
			js.executeScript("arguments[0].scrollIntoView();", Sample.javascript);
        			Settings.LOGGER.info(" Successful able to scroll to javascript element ");}	
			catch(Exception e){;
        			Assert.fail(" Unable to scroll to javascript element ");
        };
    }

    public void verifyJavascriptIsDisplayed() {
        //The below function is for web element @FindBy(Sample.javascript);
        assertTrue("Javascript is not visible", $(Sample.javascript).isDisplayed());
        Settings.LOGGER.info("User verifies javascript element is displayed");
    }

    public void verifyJavascriptContainsText(String typeText) {
        //The below function is for web element @FindBy(Sample.javascript);
        try{
			assertTrue("Actual text: " + $(Sample.javascript).getText(), StringUtils.containsIgnoreCase($(Sample.javascript).getText(), typeText));
        	Settings.LOGGER.info("User verifies javascript contains " + typeText);
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void verifyJavascriptText(String typeText) {
        //The below function is for web element @FindBy(Sample.javascript);
        try{
			assertTrue("Actual text: " + $(Sample.javascript).getText(), StringUtils.equalsIgnoreCase(typeText,$(Sample.javascript).getText()));
        	Settings.LOGGER.info("User gets the text of javascript element");
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void selectBike() {
        //The below function is for web element @FindBy(Sample.Bike);
        try{
			$(Sample.Bike).click();
        	Settings.LOGGER.info("User selects the Bike element");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not select Bike");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void clickOnBike() {
        //The below function is for web element @FindBy(Sample.Bike);
        try{
			$(Sample.Bike).click();
        	Settings.LOGGER.info("User click on the Bike element");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not click Bike");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void elementIsClickableBike() {
        //The below function is for web element @FindBy(Sample.Bike);
        assertTrue("Bike is not clickable", $(Sample.Bike).isClickable());
        Settings.LOGGER.info("User verifies Bike is clickable");
    }

    public String getAttributeFromBike(String attributeValue) {
        //This function is for web element @FindBy(Sample.Bike);
        String valueOfAttribute = "";
        try{
			Settings.LOGGER.info("User gets attribute as "+attributeValue+" for Bike element");
        	valueOfAttribute = $(Sample.Bike).getAttribute(attributeValue);
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not get value for attribute " + attributeValue);
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        }
		return valueOfAttribute;
    }

    public void verifyElementSelectedForBike() {
        //This function is for web element @FindBy(Sample.Bike);
        assertTrue("Bike is not selected", $(Sample.Bike).isSelected());
        Settings.LOGGER.info("User verifies if checkbox is selected");
    }

    public void verifyElementNotSelectedForBike() {
        //This function is for web element @FindBy(Sample.Bike);
        assertFalse("Bike is selected", $(Sample.Bike).isSelected());
        Settings.LOGGER.info("User verifies if checkbox is not selected");
    }

    public void verifyValueFromBike(String typeText) {
        //This function is for web element @FindBy(Sample.Bike);
        assertTrue("Actual value: " + $(Sample.Bike).getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,$(Sample.Bike).getAttribute("value")));
        Settings.LOGGER.info("User verifies value: " + typeText + "for Bike");
    }

    public void verifyBikeExists() {
        //This function is for web element @FindBy(Sample.Bike);
        try{
			if(getDriver().findElements(Sample.Bike).size()>0){;
        			Settings.LOGGER.info("Bike is present on Screen");}	
			 else{
				Assert.fail("Bike element is not present on Screen");
        			Settings.LOGGER.info("Bike element is not present on Screen");}	
		}
		catch(Exception e){
			Assert.fail("Bike element is not present on Screen");
        };
    }

    public void scrollToBikeElement() {
        //This function is for web element @FindBy(Sample.Bike);
        try{
			JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();
			js.executeScript("arguments[0].scrollIntoView();", Sample.Bike);
        			Settings.LOGGER.info(" Successful able to scroll to Bike element ");}	
			catch(Exception e){;
        			Assert.fail(" Unable to scroll to Bike element ");
        };
    }

    public void verifyBikeIsDisplayed() {
        //The below function is for web element @FindBy(Sample.Bike);
        assertTrue("Bike is not visible", $(Sample.Bike).isDisplayed());
        Settings.LOGGER.info("User verifies Bike element is displayed");
    }

    public void verifyBikeContainsText(String typeText) {
        //The below function is for web element @FindBy(Sample.Bike);
        try{
			assertTrue("Actual text: " + $(Sample.Bike).getText(), StringUtils.containsIgnoreCase($(Sample.Bike).getText(), typeText));
        	Settings.LOGGER.info("User verifies Bike contains " + typeText);
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void verifyBikeText(String typeText) {
        //The below function is for web element @FindBy(Sample.Bike);
        try{
			assertTrue("Actual text: " + $(Sample.Bike).getText(), StringUtils.equalsIgnoreCase(typeText,$(Sample.Bike).getText()));
        	Settings.LOGGER.info("User gets the text of Bike element");
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void selectBoat() {
        //The below function is for web element @FindBy(Sample.Boat);
        try{
			$(Sample.Boat).click();
        	Settings.LOGGER.info("User selects the Boat element");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not select Boat");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void clickOnBoat() {
        //The below function is for web element @FindBy(Sample.Boat);
        try{
			$(Sample.Boat).click();
        	Settings.LOGGER.info("User click on the Boat element");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not click Boat");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void elementIsClickableBoat() {
        //The below function is for web element @FindBy(Sample.Boat);
        assertTrue("Boat is not clickable", $(Sample.Boat).isClickable());
        Settings.LOGGER.info("User verifies Boat is clickable");
    }

    public String getAttributeFromBoat(String attributeValue) {
        //This function is for web element @FindBy(Sample.Boat);
        String valueOfAttribute = "";
        try{
			Settings.LOGGER.info("User gets attribute as "+attributeValue+" for Boat element");
        	valueOfAttribute = $(Sample.Boat).getAttribute(attributeValue);
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not get value for attribute " + attributeValue);
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        }
		return valueOfAttribute;
    }

    public void verifyElementSelectedForBoat() {
        //This function is for web element @FindBy(Sample.Boat);
        assertTrue("Boat is not selected", $(Sample.Boat).isSelected());
        Settings.LOGGER.info("User verifies if checkbox is selected");
    }

    public void verifyElementNotSelectedForBoat() {
        //This function is for web element @FindBy(Sample.Boat);
        assertFalse("Boat is selected", $(Sample.Boat).isSelected());
        Settings.LOGGER.info("User verifies if checkbox is not selected");
    }

    public void verifyValueFromBoat(String typeText) {
        //This function is for web element @FindBy(Sample.Boat);
        assertTrue("Actual value: " + $(Sample.Boat).getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,$(Sample.Boat).getAttribute("value")));
        Settings.LOGGER.info("User verifies value: " + typeText + "for Boat");
    }

    public void verifyBoatExists() {
        //This function is for web element @FindBy(Sample.Boat);
        try{
			if(getDriver().findElements(Sample.Boat).size()>0){;
        			Settings.LOGGER.info("Boat is present on Screen");}	
			 else{
				Assert.fail("Boat element is not present on Screen");
        			Settings.LOGGER.info("Boat element is not present on Screen");}	
		}
		catch(Exception e){
			Assert.fail("Boat element is not present on Screen");
        };
    }

    public void scrollToBoatElement() {
        //This function is for web element @FindBy(Sample.Boat);
        try{
			JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();
			js.executeScript("arguments[0].scrollIntoView();", Sample.Boat);
        			Settings.LOGGER.info(" Successful able to scroll to Boat element ");}	
			catch(Exception e){;
        			Assert.fail(" Unable to scroll to Boat element ");
        };
    }

    public void verifyBoatIsDisplayed() {
        //The below function is for web element @FindBy(Sample.Boat);
        assertTrue("Boat is not visible", $(Sample.Boat).isDisplayed());
        Settings.LOGGER.info("User verifies Boat element is displayed");
    }

    public void verifyBoatContainsText(String typeText) {
        //The below function is for web element @FindBy(Sample.Boat);
        try{
			assertTrue("Actual text: " + $(Sample.Boat).getText(), StringUtils.containsIgnoreCase($(Sample.Boat).getText(), typeText));
        	Settings.LOGGER.info("User verifies Boat contains " + typeText);
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void verifyBoatText(String typeText) {
        //The below function is for web element @FindBy(Sample.Boat);
        try{
			assertTrue("Actual text: " + $(Sample.Boat).getText(), StringUtils.equalsIgnoreCase(typeText,$(Sample.Boat).getText()));
        	Settings.LOGGER.info("User gets the text of Boat element");
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void selectCar(String typeText) {
        //The below function is for web element @FindBy(Sample.car);
        try{
			$(Sample.car).selectByVisibleText(typeText);
        	Settings.LOGGER.info("User is able to select " + typeText +" visible text in the dropdown");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not select " + typeText);
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void deselectCar(String typeText) {
        //The below function is for web element @FindBy(Sample.car);
        try{
			$(Sample.car).deselectByVisibleText(typeText);
        	Settings.LOGGER.info("User is able to deselect " + typeText +" visible text in the dropdown");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not deselect " + typeText);
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void verifyCarIsEnabled() {
        //The below function is for web element @FindBy(Sample.car);
        assertTrue("Car is not enabled", $(Sample.car).isEnabled());
        Settings.LOGGER.info("User verifies the given car element is enabled");
    }

    public void elementIsClickableCar() {
        //The below function is for web element @FindBy(Sample.car);
        assertTrue("Car is not clickable", $(Sample.car).isClickable());
        Settings.LOGGER.info("User verifies car is clickable");
    }

    public String getTextFromCar() {
        //The below function is for web element @FindBy(Sample.car);
        String text = "";
        try{
			text = $(Sample.car).getText();
			Settings.LOGGER.info("User gets the text of car element");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not get text of Car");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        }
		return text;
    }

    public String getAttributeFromCar(String attributeValue) {
        //This function is for web element @FindBy(Sample.car);
        String valueOfAttribute = "";
        try{
			Settings.LOGGER.info("User gets attribute as "+attributeValue+" for car element");
        	valueOfAttribute = $(Sample.car).getAttribute(attributeValue);
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not get value for attribute " + attributeValue);
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        }
		return valueOfAttribute;
    }

    public void verifyValueFromCar(String typeText) {
        //This function is for web element @FindBy(Sample.car);
        assertTrue("Actual value: " + $(Sample.car).getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,$(Sample.car).getAttribute("value")));
        Settings.LOGGER.info("User verifies value: " + typeText + "for Car");
    }

    public void hoverOverCar(String labelText) {
        //This function is for web element @FindBy(Sample.car);
        try{
			getDriver().navigate().forward();
        	Settings.LOGGER.info("User successfully navigated Forward");
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void verifyCarExists() {
        //This function is for web element @FindBy(Sample.car);
        try{
			if(getDriver().findElements(Sample.car).size()>0){;
        			Settings.LOGGER.info("car is present on Screen");}	
			 else{
				Assert.fail("car element is not present on Screen");
        			Settings.LOGGER.info("car element is not present on Screen");}	
		}
		catch(Exception e){
			Assert.fail("car element is not present on Screen");
        };
    }

    public void scrollToCarElement() {
        //This function is for web element @FindBy(Sample.car);
        try{
			JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();
			js.executeScript("arguments[0].scrollIntoView();", Sample.car);
        			Settings.LOGGER.info(" Successful able to scroll to car element ");}	
			catch(Exception e){;
        			Assert.fail(" Unable to scroll to car element ");
        };
    }

    public void verifyCarIsDisplayed() {
        //The below function is for web element @FindBy(Sample.car);
        assertTrue("Car is not visible", $(Sample.car).isDisplayed());
        Settings.LOGGER.info("User verifies car element is displayed");
    }

    public void verifyCarContainsText(String typeText) {
        //The below function is for web element @FindBy(Sample.car);
        try{
			assertTrue("Actual text: " + $(Sample.car).getText(), StringUtils.containsIgnoreCase($(Sample.car).getText(), typeText));
        	Settings.LOGGER.info("User verifies car contains " + typeText);
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void verifyCarText(String typeText) {
        //The below function is for web element @FindBy(Sample.car);
        try{
			assertTrue("Actual text: " + $(Sample.car).getText(), StringUtils.equalsIgnoreCase(typeText,$(Sample.car).getText()));
        	Settings.LOGGER.info("User gets the text of car element");
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void clickOnSubmit() {
        //The below function is for web element @FindBy(Sample.Submit);
        try{
			$(Sample.Submit).click();
        	Settings.LOGGER.info("User click on the Submit element");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not click Submit");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void elementIsClickableSubmit() {
        //The below function is for web element @FindBy(Sample.Submit);
        assertTrue("Submit is not clickable", $(Sample.Submit).isClickable());
        Settings.LOGGER.info("User verifies Submit is clickable");
    }

    public String getAttributeFromSubmit(String attributeValue) {
        //This function is for web element @FindBy(Sample.Submit);
        String valueOfAttribute = "";
        try{
			Settings.LOGGER.info("User gets attribute as "+attributeValue+" for Submit element");
        	valueOfAttribute = $(Sample.Submit).getAttribute(attributeValue);
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not get value for attribute " + attributeValue);
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        }
		return valueOfAttribute;
    }

    public void verifyValueFromSubmit(String typeText) {
        //This function is for web element @FindBy(Sample.Submit);
        assertTrue("Actual value: " + $(Sample.Submit).getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,$(Sample.Submit).getAttribute("value")));
        Settings.LOGGER.info("User verifies value: " + typeText + "for Submit");
    }

    public void hoverOverSubmit(String labelText) {
        //This function is for web element @FindBy(Sample.Submit);
        try{
			getDriver().navigate().forward();
        	Settings.LOGGER.info("User successfully navigated Forward");
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void verifySubmitExists() {
        //This function is for web element @FindBy(Sample.Submit);
        try{
			if(getDriver().findElements(Sample.Submit).size()>0){;
        			Settings.LOGGER.info("Submit is present on Screen");}	
			 else{
				Assert.fail("Submit element is not present on Screen");
        			Settings.LOGGER.info("Submit element is not present on Screen");}	
		}
		catch(Exception e){
			Assert.fail("Submit element is not present on Screen");
        };
    }

    public void scrollToSubmitElement() {
        //This function is for web element @FindBy(Sample.Submit);
        try{
			JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();
			js.executeScript("arguments[0].scrollIntoView();", Sample.Submit);
        			Settings.LOGGER.info(" Successful able to scroll to Submit element ");}	
			catch(Exception e){;
        			Assert.fail(" Unable to scroll to Submit element ");
        };
    }

    public void verifySubmitIsDisplayed() {
        //The below function is for web element @FindBy(Sample.Submit);
        assertTrue("Submit is not visible", $(Sample.Submit).isDisplayed());
        Settings.LOGGER.info("User verifies Submit element is displayed");
    }

    public void verifySubmitContainsText(String typeText) {
        //The below function is for web element @FindBy(Sample.Submit);
        try{
			assertTrue("Actual text: " + $(Sample.Submit).getText(), StringUtils.containsIgnoreCase($(Sample.Submit).getText(), typeText));
        	Settings.LOGGER.info("User verifies Submit contains " + typeText);
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void verifySubmitText(String typeText) {
        //The below function is for web element @FindBy(Sample.Submit);
        try{
			assertTrue("Actual text: " + $(Sample.Submit).getText(), StringUtils.equalsIgnoreCase(typeText,$(Sample.Submit).getText()));
        	Settings.LOGGER.info("User gets the text of Submit element");
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void clickOnHtmlAndNavigateBack() {
        //This function is for web element @FindBy(Sample.html);
        Settings.LOGGER.info("User click on html element");
        try{
			$(Sample.html).click();
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        }
		getDriver().navigate().back();
        Settings.LOGGER.info("User navigates back  to previous page");
    }

    public void clickOnHtml() {
        //The below function is for web element @FindBy(Sample.html);
        try{
			$(Sample.html).click();
        	Settings.LOGGER.info("User click on the html element");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not click Html");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void elementIsClickableHtml() {
        //The below function is for web element @FindBy(Sample.html);
        assertTrue("Html is not clickable", $(Sample.html).isClickable());
        Settings.LOGGER.info("User verifies html is clickable");
    }

    public String getAttributeFromHtml(String attributeValue) {
        //This function is for web element @FindBy(Sample.html);
        String valueOfAttribute = "";
        try{
			Settings.LOGGER.info("User gets attribute as "+attributeValue+" for html element");
        	valueOfAttribute = $(Sample.html).getAttribute(attributeValue);
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not get value for attribute " + attributeValue);
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        }
		return valueOfAttribute;
    }

    public void verifyValueFromHtml(String typeText) {
        //This function is for web element @FindBy(Sample.html);
        assertTrue("Actual value: " + $(Sample.html).getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,$(Sample.html).getAttribute("value")));
        Settings.LOGGER.info("User verifies value: " + typeText + "for Html");
    }

    public void hoverOverHtml(String labelText) {
        //This function is for web element @FindBy(Sample.html);
        try{
			getDriver().navigate().forward();
        	Settings.LOGGER.info("User successfully navigated Forward");
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void verifyHtmlExists() {
        //This function is for web element @FindBy(Sample.html);
        try{
			if(getDriver().findElements(Sample.html).size()>0){;
        			Settings.LOGGER.info("html is present on Screen");}	
			 else{
				Assert.fail("html element is not present on Screen");
        			Settings.LOGGER.info("html element is not present on Screen");}	
		}
		catch(Exception e){
			Assert.fail("html element is not present on Screen");
        };
    }

    public void scrollToHtmlElement() {
        //This function is for web element @FindBy(Sample.html);
        try{
			JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();
			js.executeScript("arguments[0].scrollIntoView();", Sample.html);
        			Settings.LOGGER.info(" Successful able to scroll to html element ");}	
			catch(Exception e){;
        			Assert.fail(" Unable to scroll to html element ");
        };
    }

    public void verifyHtmlIsDisplayed() {
        //The below function is for web element @FindBy(Sample.html);
        assertTrue("Html is not visible", $(Sample.html).isDisplayed());
        Settings.LOGGER.info("User verifies html element is displayed");
    }

    public void verifyHtmlContainsText(String typeText) {
        //The below function is for web element @FindBy(Sample.html);
        try{
			assertTrue("Actual text: " + $(Sample.html).getText(), StringUtils.containsIgnoreCase($(Sample.html).getText(), typeText));
        	Settings.LOGGER.info("User verifies html contains " + typeText);
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void verifyHtmlText(String typeText) {
        //The below function is for web element @FindBy(Sample.html);
        try{
			assertTrue("Actual text: " + $(Sample.html).getText(), StringUtils.equalsIgnoreCase(typeText,$(Sample.html).getText()));
        	Settings.LOGGER.info("User gets the text of html element");
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }
}
