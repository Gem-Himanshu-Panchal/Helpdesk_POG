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

public class SampleImplementation extends PageObject {

    private WebDriver driver =getDriver();

    public void typeTextIntoFirstName(String typeText) {
        //The below function is for web element @FindBy(Sample.firstName);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Sample.firstName));
        	typeInto(element,typeText);
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
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Sample.firstName));
        	$(element).typeAndEnter(typeText);
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
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Sample.firstName));
        	typeInto(element,typeText);
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
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Sample.firstName));
        	text = element.getText();
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
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Sample.firstName));
        	assertTrue("FirstName is not enabled", element.isEnabled());
        	Settings.LOGGER.info("User verifies the given firstName element is enabled");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			Serenity.recordReportData().withTitle("Failure").andContents("Element is not enabled");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        };
    }

    public void clearFirstName() {
        //The below function is for web element @FindBy(Sample.firstName);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Sample.firstName));
        	element.clear();
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
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Sample.firstName));
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
        try{
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut"))));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Sample.firstName));
		assertTrue("Actual value: " + $(Sample.firstName).getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,$(Sample.firstName).getAttribute("value")));
        Settings.LOGGER.info("User verifies value: " + typeText + "for FirstName");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not verify value for firstName)");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
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
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			verifyFirstNameIsEnabled();
			serenityActions.moveToElement($(Sample.firstName)).build().perform();
        	Settings.LOGGER.info("User successfully hovers over");
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
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].scrollIntoView();", $(Sample.firstName));
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

    public void verifyAttributeContainsValueForFirstName(String attribute, String typeText) {
        //The below function is for web element @FindBy(Sample.firstName);
        try{
			assertTrue("Actual value: " + $(Sample.firstName).getAttribute(attribute), StringUtils.contains($(Sample.firstName).getAttribute(attribute), typeText));
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
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Sample.password));
        	typeInto(element,typeText);
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
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Sample.password));
        	$(element).typeAndEnter(typeText);
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
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Sample.password));
        	typeInto(element,typeText);
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
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Sample.password));
        	text = element.getText();
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
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Sample.password));
        	assertTrue("Password is not enabled", element.isEnabled());
        	Settings.LOGGER.info("User verifies the given password element is enabled");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			Serenity.recordReportData().withTitle("Failure").andContents("Element is not enabled");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        };
    }

    public void clearPassword() {
        //The below function is for web element @FindBy(Sample.password);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Sample.password));
        	element.clear();
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
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Sample.password));
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
        try{
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut"))));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Sample.password));
		assertTrue("Actual value: " + $(Sample.password).getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,$(Sample.password).getAttribute("value")));
        Settings.LOGGER.info("User verifies value: " + typeText + "for Password");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not verify value for password)");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
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
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			verifyPasswordIsEnabled();
			serenityActions.moveToElement($(Sample.password)).build().perform();
        	Settings.LOGGER.info("User successfully hovers over");
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
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].scrollIntoView();", $(Sample.password));
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

    public void verifyAttributeContainsValueForPassword(String attribute, String typeText) {
        //The below function is for web element @FindBy(Sample.password);
        try{
			assertTrue("Actual value: " + $(Sample.password).getAttribute(attribute), StringUtils.contains($(Sample.password).getAttribute(attribute), typeText));
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
}
