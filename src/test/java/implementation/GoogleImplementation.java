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

public class GoogleImplementation extends PageObject {

    private WebDriver driver =getDriver();

    public void typeTextIntoFirstName(String typeText) {
        //The below function is for web element @FindBy(Google.firstName);
        try{
			typeInto($(Google.firstName),typeText);
        	Settings.LOGGER.info("User enters "+typeText+" as value");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not enter " + typeText + " into FirstName");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public String getTextFromFirstName() {
        //The below function is for web element @FindBy(Google.firstName);
        String text = "";
        try{
			text = $(Google.firstName).getText();
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
        //The below function is for web element @FindBy(Google.firstName);
        assertTrue("FirstName is not enabled", $(Google.firstName).isEnabled());
        Settings.LOGGER.info("User verifies the given firstName element is enabled");
    }

    public void clearFirstName() {
        //The below function is for web element @FindBy(Google.firstName);
        try{
			$(Google.firstName).clear();
        	Settings.LOGGER.info("User deletes the value for firstName element");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not clear FirstName");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
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

    public void verifyValueClearedForFirstName() {
        //This function is for web element @FindBy(Google.firstName);
        assertTrue("Actual value: " + $(Google.firstName).getAttribute("value"), StringUtils.equalsIgnoreCase("", $(Google.firstName).getAttribute("value")));
        Settings.LOGGER.info("User verifies value is cleared for FirstName");
    }

    public void verifyFirstNameIsDisplayed() {
        //The below function is for web element @FindBy(Google.firstName);
        assertTrue("FirstName is not visible", $(Google.firstName).isDisplayed());
        Settings.LOGGER.info("User verifies firstName element is displayed");
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

    public void typeTextIntoPassword(String typeText) {
        //The below function is for web element @FindBy(Google.password);
        try{
			typeInto($(Google.password),typeText);
        	Settings.LOGGER.info("User enters "+typeText+" as value");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not enter " + typeText + " into Password");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public String getTextFromPassword() {
        //The below function is for web element @FindBy(Google.password);
        String text = "";
        try{
			text = $(Google.password).getText();
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
        //The below function is for web element @FindBy(Google.password);
        assertTrue("Password is not enabled", $(Google.password).isEnabled());
        Settings.LOGGER.info("User verifies the given password element is enabled");
    }

    public void clearPassword() {
        //The below function is for web element @FindBy(Google.password);
        try{
			$(Google.password).clear();
        	Settings.LOGGER.info("User deletes the value for password element");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not clear Password");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public String getAttributeFromPassword(String attributeValue) {
        //This function is for web element @FindBy(Google.password);
        String valueOfAttribute = "";
        try{
			Settings.LOGGER.info("User gets attribute as "+attributeValue+" for password element");
        	valueOfAttribute = $(Google.password).getAttribute(attributeValue);
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not get value for attribute " + attributeValue);
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        }
		return valueOfAttribute;
    }

    public void verifyValueFromPassword(String typeText) {
        //This function is for web element @FindBy(Google.password);
        assertTrue("Actual value: " + $(Google.password).getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,$(Google.password).getAttribute("value")));
        Settings.LOGGER.info("User verifies value: " + typeText + "for Password");
    }

    public String verifyAttributeValueForPassword(String attributeName, String attributeValue) {
        //This function is for web element @FindBy(Google.password);
        try{
			assertTrue(StringUtils.equalsIgnoreCase(attributeValue,$(Google.password).getAttribute(attributeName)));
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        }
		return $(Google.password).getAttribute(attributeName);
    }

    public void verifyValueClearedForPassword() {
        //This function is for web element @FindBy(Google.password);
        assertTrue("Actual value: " + $(Google.password).getAttribute("value"), StringUtils.equalsIgnoreCase("", $(Google.password).getAttribute("value")));
        Settings.LOGGER.info("User verifies value is cleared for Password");
    }

    public void verifyPasswordIsDisplayed() {
        //The below function is for web element @FindBy(Google.password);
        assertTrue("Password is not visible", $(Google.password).isDisplayed());
        Settings.LOGGER.info("User verifies password element is displayed");
    }

    public void verifyPasswordText(String typeText) {
        //The below function is for web element @FindBy(Google.password);
        try{
			assertTrue("Actual text: " + $(Google.password).getText(), StringUtils.equalsIgnoreCase(typeText,$(Google.password).getText()));
        	Settings.LOGGER.info("User gets the text of password element");
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void typeTextIntoLastName(String typeText) {
        //The below function is for web element @FindBy(Google.lastName);
        try{
			typeInto($(Google.lastName),typeText);
        	Settings.LOGGER.info("User enters "+typeText+" as value");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not enter " + typeText + " into LastName");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public String getTextFromLastName() {
        //The below function is for web element @FindBy(Google.lastName);
        String text = "";
        try{
			text = $(Google.lastName).getText();
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
        //The below function is for web element @FindBy(Google.lastName);
        assertTrue("LastName is not enabled", $(Google.lastName).isEnabled());
        Settings.LOGGER.info("User verifies the given lastName element is enabled");
    }

    public void clearLastName() {
        //The below function is for web element @FindBy(Google.lastName);
        try{
			$(Google.lastName).clear();
        	Settings.LOGGER.info("User deletes the value for lastName element");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not clear LastName");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public String getAttributeFromLastName(String attributeValue) {
        //This function is for web element @FindBy(Google.lastName);
        String valueOfAttribute = "";
        try{
			Settings.LOGGER.info("User gets attribute as "+attributeValue+" for lastName element");
        	valueOfAttribute = $(Google.lastName).getAttribute(attributeValue);
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not get value for attribute " + attributeValue);
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        }
		return valueOfAttribute;
    }

    public void verifyValueFromLastName(String typeText) {
        //This function is for web element @FindBy(Google.lastName);
        assertTrue("Actual value: " + $(Google.lastName).getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,$(Google.lastName).getAttribute("value")));
        Settings.LOGGER.info("User verifies value: " + typeText + "for LastName");
    }

    public String verifyAttributeValueForLastName(String attributeName, String attributeValue) {
        //This function is for web element @FindBy(Google.lastName);
        try{
			assertTrue(StringUtils.equalsIgnoreCase(attributeValue,$(Google.lastName).getAttribute(attributeName)));
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        }
		return $(Google.lastName).getAttribute(attributeName);
    }

    public void verifyValueClearedForLastName() {
        //This function is for web element @FindBy(Google.lastName);
        assertTrue("Actual value: " + $(Google.lastName).getAttribute("value"), StringUtils.equalsIgnoreCase("", $(Google.lastName).getAttribute("value")));
        Settings.LOGGER.info("User verifies value is cleared for LastName");
    }

    public void verifyLastNameIsDisplayed() {
        //The below function is for web element @FindBy(Google.lastName);
        assertTrue("LastName is not visible", $(Google.lastName).isDisplayed());
        Settings.LOGGER.info("User verifies lastName element is displayed");
    }

    public void verifyLastNameText(String typeText) {
        //The below function is for web element @FindBy(Google.lastName);
        try{
			assertTrue("Actual text: " + $(Google.lastName).getText(), StringUtils.equalsIgnoreCase(typeText,$(Google.lastName).getText()));
        	Settings.LOGGER.info("User gets the text of lastName element");
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void typeTextIntoHeading(String typeText) {
        //The below function is for web element @FindBy(Google.heading);
        try{
			typeInto($(Google.heading),typeText);
        	Settings.LOGGER.info("User enters "+typeText+" as value");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not enter " + typeText + " into Heading");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public String getTextFromHeading() {
        //The below function is for web element @FindBy(Google.heading);
        String text = "";
        try{
			text = $(Google.heading).getText();
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
        //The below function is for web element @FindBy(Google.heading);
        assertTrue("Heading is not enabled", $(Google.heading).isEnabled());
        Settings.LOGGER.info("User verifies the given heading element is enabled");
    }

    public void clearHeading() {
        //The below function is for web element @FindBy(Google.heading);
        try{
			$(Google.heading).clear();
        	Settings.LOGGER.info("User deletes the value for heading element");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not clear Heading");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public String getAttributeFromHeading(String attributeValue) {
        //This function is for web element @FindBy(Google.heading);
        String valueOfAttribute = "";
        try{
			Settings.LOGGER.info("User gets attribute as "+attributeValue+" for heading element");
        	valueOfAttribute = $(Google.heading).getAttribute(attributeValue);
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not get value for attribute " + attributeValue);
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        }
		return valueOfAttribute;
    }

    public void verifyValueFromHeading(String typeText) {
        //This function is for web element @FindBy(Google.heading);
        assertTrue("Actual value: " + $(Google.heading).getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,$(Google.heading).getAttribute("value")));
        Settings.LOGGER.info("User verifies value: " + typeText + "for Heading");
    }

    public String verifyAttributeValueForHeading(String attributeName, String attributeValue) {
        //This function is for web element @FindBy(Google.heading);
        try{
			assertTrue(StringUtils.equalsIgnoreCase(attributeValue,$(Google.heading).getAttribute(attributeName)));
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        }
		return $(Google.heading).getAttribute(attributeName);
    }

    public void verifyValueClearedForHeading() {
        //This function is for web element @FindBy(Google.heading);
        assertTrue("Actual value: " + $(Google.heading).getAttribute("value"), StringUtils.equalsIgnoreCase("", $(Google.heading).getAttribute("value")));
        Settings.LOGGER.info("User verifies value is cleared for Heading");
    }

    public void verifyHeadingIsDisplayed() {
        //The below function is for web element @FindBy(Google.heading);
        assertTrue("Heading is not visible", $(Google.heading).isDisplayed());
        Settings.LOGGER.info("User verifies heading element is displayed");
    }

    public void verifyHeadingText(String typeText) {
        //The below function is for web element @FindBy(Google.heading);
        try{
			assertTrue("Actual text: " + $(Google.heading).getText(), StringUtils.equalsIgnoreCase(typeText,$(Google.heading).getText()));
        	Settings.LOGGER.info("User gets the text of heading element");
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void typeTextIntoBirthday(String typeText) {
        //The below function is for web element @FindBy(Google.birthday);
        try{
			typeInto($(Google.birthday),typeText);
        	Settings.LOGGER.info("User enters "+typeText+" as value");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not enter " + typeText + " into Birthday");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public String getTextFromBirthday() {
        //The below function is for web element @FindBy(Google.birthday);
        String text = "";
        try{
			text = $(Google.birthday).getText();
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
        //The below function is for web element @FindBy(Google.birthday);
        assertTrue("Birthday is not enabled", $(Google.birthday).isEnabled());
        Settings.LOGGER.info("User verifies the given birthday element is enabled");
    }

    public void clearBirthday() {
        //The below function is for web element @FindBy(Google.birthday);
        try{
			$(Google.birthday).clear();
        	Settings.LOGGER.info("User deletes the value for birthday element");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not clear Birthday");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public String getAttributeFromBirthday(String attributeValue) {
        //This function is for web element @FindBy(Google.birthday);
        String valueOfAttribute = "";
        try{
			Settings.LOGGER.info("User gets attribute as "+attributeValue+" for birthday element");
        	valueOfAttribute = $(Google.birthday).getAttribute(attributeValue);
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not get value for attribute " + attributeValue);
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        }
		return valueOfAttribute;
    }

    public void verifyValueFromBirthday(String typeText) {
        //This function is for web element @FindBy(Google.birthday);
        assertTrue("Actual value: " + $(Google.birthday).getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,$(Google.birthday).getAttribute("value")));
        Settings.LOGGER.info("User verifies value: " + typeText + "for Birthday");
    }

    public String verifyAttributeValueForBirthday(String attributeName, String attributeValue) {
        //This function is for web element @FindBy(Google.birthday);
        try{
			assertTrue(StringUtils.equalsIgnoreCase(attributeValue,$(Google.birthday).getAttribute(attributeName)));
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        }
		return $(Google.birthday).getAttribute(attributeName);
    }

    public void verifyValueClearedForBirthday() {
        //This function is for web element @FindBy(Google.birthday);
        assertTrue("Actual value: " + $(Google.birthday).getAttribute("value"), StringUtils.equalsIgnoreCase("", $(Google.birthday).getAttribute("value")));
        Settings.LOGGER.info("User verifies value is cleared for Birthday");
    }

    public void verifyBirthdayIsDisplayed() {
        //The below function is for web element @FindBy(Google.birthday);
        assertTrue("Birthday is not visible", $(Google.birthday).isDisplayed());
        Settings.LOGGER.info("User verifies birthday element is displayed");
    }

    public void verifyBirthdayText(String typeText) {
        //The below function is for web element @FindBy(Google.birthday);
        try{
			assertTrue("Actual text: " + $(Google.birthday).getText(), StringUtils.equalsIgnoreCase(typeText,$(Google.birthday).getText()));
        	Settings.LOGGER.info("User gets the text of birthday element");
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void selectCss() {
        //The below function is for web element @FindBy(Google.css);
        try{
			$(Google.css).click();
        	Settings.LOGGER.info("User selects the css element");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not select Css");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void verifyCssIsEnabled() {
        //The below function is for web element @FindBy(Google.css);
        assertTrue("Css is not enabled", $(Google.css).isEnabled());
        Settings.LOGGER.info("User verifies the given css element is enabled");
    }

    public void verifyCssIsSelected() {
        //This function is for web element @FindBy(Google.css);
        assertTrue("Css is not selected", $(Google.css).isSelected());
        Settings.LOGGER.info("User verifies css is selected");
    }

    public String getAttributeFromCss(String attributeValue) {
        //This function is for web element @FindBy(Google.css);
        String valueOfAttribute = "";
        try{
			Settings.LOGGER.info("User gets attribute as "+attributeValue+" for css element");
        	valueOfAttribute = $(Google.css).getAttribute(attributeValue);
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not get value for attribute " + attributeValue);
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        }
		return valueOfAttribute;
    }

    public void verifyValueFromCss(String typeText) {
        //This function is for web element @FindBy(Google.css);
        assertTrue("Actual value: " + $(Google.css).getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,$(Google.css).getAttribute("value")));
        Settings.LOGGER.info("User verifies value: " + typeText + "for Css");
    }

    public void verifyCssIsDisplayed() {
        //The below function is for web element @FindBy(Google.css);
        assertTrue("Css is not visible", $(Google.css).isDisplayed());
        Settings.LOGGER.info("User verifies css element is displayed");
    }

    public void verifyCssText(String typeText) {
        //The below function is for web element @FindBy(Google.css);
        try{
			assertTrue("Actual text: " + $(Google.css).getText(), StringUtils.equalsIgnoreCase(typeText,$(Google.css).getText()));
        	Settings.LOGGER.info("User gets the text of css element");
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void selectJavascript() {
        //The below function is for web element @FindBy(Google.javascript);
        try{
			$(Google.javascript).click();
        	Settings.LOGGER.info("User selects the javascript element");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not select Javascript");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void verifyJavascriptIsEnabled() {
        //The below function is for web element @FindBy(Google.javascript);
        assertTrue("Javascript is not enabled", $(Google.javascript).isEnabled());
        Settings.LOGGER.info("User verifies the given javascript element is enabled");
    }

    public void verifyJavascriptIsSelected() {
        //This function is for web element @FindBy(Google.javascript);
        assertTrue("Javascript is not selected", $(Google.javascript).isSelected());
        Settings.LOGGER.info("User verifies javascript is selected");
    }

    public String getAttributeFromJavascript(String attributeValue) {
        //This function is for web element @FindBy(Google.javascript);
        String valueOfAttribute = "";
        try{
			Settings.LOGGER.info("User gets attribute as "+attributeValue+" for javascript element");
        	valueOfAttribute = $(Google.javascript).getAttribute(attributeValue);
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not get value for attribute " + attributeValue);
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        }
		return valueOfAttribute;
    }

    public void verifyValueFromJavascript(String typeText) {
        //This function is for web element @FindBy(Google.javascript);
        assertTrue("Actual value: " + $(Google.javascript).getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,$(Google.javascript).getAttribute("value")));
        Settings.LOGGER.info("User verifies value: " + typeText + "for Javascript");
    }

    public void verifyJavascriptIsDisplayed() {
        //The below function is for web element @FindBy(Google.javascript);
        assertTrue("Javascript is not visible", $(Google.javascript).isDisplayed());
        Settings.LOGGER.info("User verifies javascript element is displayed");
    }

    public void verifyJavascriptText(String typeText) {
        //The below function is for web element @FindBy(Google.javascript);
        try{
			assertTrue("Actual text: " + $(Google.javascript).getText(), StringUtils.equalsIgnoreCase(typeText,$(Google.javascript).getText()));
        	Settings.LOGGER.info("User gets the text of javascript element");
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void selectBike() {
        //The below function is for web element @FindBy(Google.Bike);
        try{
			$(Google.Bike).click();
        	Settings.LOGGER.info("User selects the Bike element");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not select Bike");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void clickOnBike() {
        //The below function is for web element @FindBy(Google.Bike);
        try{
			$(Google.Bike).click();
        	Settings.LOGGER.info("User click on the Bike element");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not click Bike");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void elementIsClickableBike() {
        //The below function is for web element @FindBy(Google.Bike);
        assertTrue("Bike is not clickable", $(Google.Bike).isClickable());
        Settings.LOGGER.info("User verifies Bike is clickable");
    }

    public String getAttributeFromBike(String attributeValue) {
        //This function is for web element @FindBy(Google.Bike);
        String valueOfAttribute = "";
        try{
			Settings.LOGGER.info("User gets attribute as "+attributeValue+" for Bike element");
        	valueOfAttribute = $(Google.Bike).getAttribute(attributeValue);
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not get value for attribute " + attributeValue);
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        }
		return valueOfAttribute;
    }

    public void verifyElementSelectedForBike() {
        //This function is for web element @FindBy(Google.Bike);
        assertTrue("Bike is not selected", $(Google.Bike).isSelected());
        Settings.LOGGER.info("User verifies if checkbox is selected");
    }

    public void verifyElementNotSelectedForBike() {
        //This function is for web element @FindBy(Google.Bike);
        assertFalse("Bike is selected", $(Google.Bike).isSelected());
        Settings.LOGGER.info("User verifies if checkbox is not selected");
    }

    public void verifyValueFromBike(String typeText) {
        //This function is for web element @FindBy(Google.Bike);
        assertTrue("Actual value: " + $(Google.Bike).getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,$(Google.Bike).getAttribute("value")));
        Settings.LOGGER.info("User verifies value: " + typeText + "for Bike");
    }

    public void verifyBikeIsDisplayed() {
        //The below function is for web element @FindBy(Google.Bike);
        assertTrue("Bike is not visible", $(Google.Bike).isDisplayed());
        Settings.LOGGER.info("User verifies Bike element is displayed");
    }

    public void verifyBikeText(String typeText) {
        //The below function is for web element @FindBy(Google.Bike);
        try{
			assertTrue("Actual text: " + $(Google.Bike).getText(), StringUtils.equalsIgnoreCase(typeText,$(Google.Bike).getText()));
        	Settings.LOGGER.info("User gets the text of Bike element");
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void selectBoat() {
        //The below function is for web element @FindBy(Google.Boat);
        try{
			$(Google.Boat).click();
        	Settings.LOGGER.info("User selects the Boat element");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not select Boat");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void clickOnBoat() {
        //The below function is for web element @FindBy(Google.Boat);
        try{
			$(Google.Boat).click();
        	Settings.LOGGER.info("User click on the Boat element");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not click Boat");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void elementIsClickableBoat() {
        //The below function is for web element @FindBy(Google.Boat);
        assertTrue("Boat is not clickable", $(Google.Boat).isClickable());
        Settings.LOGGER.info("User verifies Boat is clickable");
    }

    public String getAttributeFromBoat(String attributeValue) {
        //This function is for web element @FindBy(Google.Boat);
        String valueOfAttribute = "";
        try{
			Settings.LOGGER.info("User gets attribute as "+attributeValue+" for Boat element");
        	valueOfAttribute = $(Google.Boat).getAttribute(attributeValue);
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not get value for attribute " + attributeValue);
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        }
		return valueOfAttribute;
    }

    public void verifyElementSelectedForBoat() {
        //This function is for web element @FindBy(Google.Boat);
        assertTrue("Boat is not selected", $(Google.Boat).isSelected());
        Settings.LOGGER.info("User verifies if checkbox is selected");
    }

    public void verifyElementNotSelectedForBoat() {
        //This function is for web element @FindBy(Google.Boat);
        assertFalse("Boat is selected", $(Google.Boat).isSelected());
        Settings.LOGGER.info("User verifies if checkbox is not selected");
    }

    public void verifyValueFromBoat(String typeText) {
        //This function is for web element @FindBy(Google.Boat);
        assertTrue("Actual value: " + $(Google.Boat).getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,$(Google.Boat).getAttribute("value")));
        Settings.LOGGER.info("User verifies value: " + typeText + "for Boat");
    }

    public void verifyBoatIsDisplayed() {
        //The below function is for web element @FindBy(Google.Boat);
        assertTrue("Boat is not visible", $(Google.Boat).isDisplayed());
        Settings.LOGGER.info("User verifies Boat element is displayed");
    }

    public void verifyBoatText(String typeText) {
        //The below function is for web element @FindBy(Google.Boat);
        try{
			assertTrue("Actual text: " + $(Google.Boat).getText(), StringUtils.equalsIgnoreCase(typeText,$(Google.Boat).getText()));
        	Settings.LOGGER.info("User gets the text of Boat element");
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void selectCar(String typeText) {
        //The below function is for web element @FindBy(Google.car);
        try{
			$(Google.car).selectByVisibleText(typeText);
        	Settings.LOGGER.info("User is able to select " + typeText +" visible text in the dropdown");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not select " + typeText);
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void verifyCarIsEnabled() {
        //The below function is for web element @FindBy(Google.car);
        assertTrue("Car is not enabled", $(Google.car).isEnabled());
        Settings.LOGGER.info("User verifies the given car element is enabled");
    }

    public void elementIsClickableCar() {
        //The below function is for web element @FindBy(Google.car);
        assertTrue("Car is not clickable", $(Google.car).isClickable());
        Settings.LOGGER.info("User verifies car is clickable");
    }

    public String getTextFromCar() {
        //The below function is for web element @FindBy(Google.car);
        String text = "";
        try{
			text = $(Google.car).getText();
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
        //This function is for web element @FindBy(Google.car);
        String valueOfAttribute = "";
        try{
			Settings.LOGGER.info("User gets attribute as "+attributeValue+" for car element");
        	valueOfAttribute = $(Google.car).getAttribute(attributeValue);
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not get value for attribute " + attributeValue);
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        }
		return valueOfAttribute;
    }

    public void verifyValueFromCar(String typeText) {
        //This function is for web element @FindBy(Google.car);
        assertTrue("Actual value: " + $(Google.car).getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,$(Google.car).getAttribute("value")));
        Settings.LOGGER.info("User verifies value: " + typeText + "for Car");
    }

    public void verifyCarIsDisplayed() {
        //The below function is for web element @FindBy(Google.car);
        assertTrue("Car is not visible", $(Google.car).isDisplayed());
        Settings.LOGGER.info("User verifies car element is displayed");
    }

    public void verifyCarText(String typeText) {
        //The below function is for web element @FindBy(Google.car);
        try{
			assertTrue("Actual text: " + $(Google.car).getText(), StringUtils.equalsIgnoreCase(typeText,$(Google.car).getText()));
        	Settings.LOGGER.info("User gets the text of car element");
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void clickOnSubmit() {
        //The below function is for web element @FindBy(Google.Submit);
        try{
			$(Google.Submit).click();
        	Settings.LOGGER.info("User click on the Submit element");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not click Submit");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void elementIsClickableSubmit() {
        //The below function is for web element @FindBy(Google.Submit);
        assertTrue("Submit is not clickable", $(Google.Submit).isClickable());
        Settings.LOGGER.info("User verifies Submit is clickable");
    }

    public String getAttributeFromSubmit(String attributeValue) {
        //This function is for web element @FindBy(Google.Submit);
        String valueOfAttribute = "";
        try{
			Settings.LOGGER.info("User gets attribute as "+attributeValue+" for Submit element");
        	valueOfAttribute = $(Google.Submit).getAttribute(attributeValue);
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not get value for attribute " + attributeValue);
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        }
		return valueOfAttribute;
    }

    public void verifyValueFromSubmit(String typeText) {
        //This function is for web element @FindBy(Google.Submit);
        assertTrue("Actual value: " + $(Google.Submit).getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,$(Google.Submit).getAttribute("value")));
        Settings.LOGGER.info("User verifies value: " + typeText + "for Submit");
    }

    public void verifySubmitIsDisplayed() {
        //The below function is for web element @FindBy(Google.Submit);
        assertTrue("Submit is not visible", $(Google.Submit).isDisplayed());
        Settings.LOGGER.info("User verifies Submit element is displayed");
    }

    public void verifySubmitText(String typeText) {
        //The below function is for web element @FindBy(Google.Submit);
        try{
			assertTrue("Actual text: " + $(Google.Submit).getText(), StringUtils.equalsIgnoreCase(typeText,$(Google.Submit).getText()));
        	Settings.LOGGER.info("User gets the text of Submit element");
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void clickOnHtmlAndNavigateBack() {
        //This function is for web element @FindBy(Google.html);
        Settings.LOGGER.info("User click on html element");
        try{
			$(Google.html).click();
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        }
		getDriver().navigate().back();
        Settings.LOGGER.info("User navigates back  to previous page");
    }

    public void clickOnHtml() {
        //The below function is for web element @FindBy(Google.html);
        try{
			$(Google.html).click();
        	Settings.LOGGER.info("User click on the html element");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not click Html");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void elementIsClickableHtml() {
        //The below function is for web element @FindBy(Google.html);
        assertTrue("Html is not clickable", $(Google.html).isClickable());
        Settings.LOGGER.info("User verifies html is clickable");
    }

    public String getAttributeFromHtml(String attributeValue) {
        //This function is for web element @FindBy(Google.html);
        String valueOfAttribute = "";
        try{
			Settings.LOGGER.info("User gets attribute as "+attributeValue+" for html element");
        	valueOfAttribute = $(Google.html).getAttribute(attributeValue);
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not get value for attribute " + attributeValue);
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        }
		return valueOfAttribute;
    }

    public void verifyValueFromHtml(String typeText) {
        //This function is for web element @FindBy(Google.html);
        assertTrue("Actual value: " + $(Google.html).getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,$(Google.html).getAttribute("value")));
        Settings.LOGGER.info("User verifies value: " + typeText + "for Html");
    }

    public void verifyHtmlIsDisplayed() {
        //The below function is for web element @FindBy(Google.html);
        assertTrue("Html is not visible", $(Google.html).isDisplayed());
        Settings.LOGGER.info("User verifies html element is displayed");
    }

    public void verifyHtmlText(String typeText) {
        //The below function is for web element @FindBy(Google.html);
        try{
			assertTrue("Actual text: " + $(Google.html).getText(), StringUtils.equalsIgnoreCase(typeText,$(Google.html).getText()));
        	Settings.LOGGER.info("User gets the text of html element");
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }
}
