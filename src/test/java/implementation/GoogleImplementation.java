package implementation;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebElement;
import pageobjectgenerator.Settings;
import net.serenitybdd.core.Serenity;
import static org.junit.Assert.assertTrue;
import org.junit.Assert;
import static org.junit.Assert.assertFalse;
import locators.Google;

import java.lang.reflect.Field;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class GoogleImplementation extends PageObject {

    private WebDriver driver =getDriver();

    static HashMap<String, By> value = new HashMap<>();



    public void clickOnSubmit(String a) {
        //The below function is for web element @FindBy(Google.submit);
        try{
            String className = a.split("\\.")[0];
            Class<?> clas = Class.forName("locators." + className);
            Field loc = clas.getField(a.split("\\.")[1]);
            By c = (By) loc.get(className);
            $(c).waitUntilClickable().click();
        	Settings.LOGGER.info("User click on the submit element");
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not click Submit");
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void elementIsClickableSubmit() {
        //The below function is for web element @FindBy(Google.submit);
        assertTrue("Submit is not clickable", $(Google.submit).isClickable());
        Settings.LOGGER.info("User verifies submit is clickable");
    }

    public String getAttributeFromSubmit(String attributeValue) {
        //This function is for web element @FindBy(Google.submit);
        String valueOfAttribute = "";
        try{
			Settings.LOGGER.info("User gets attribute as "+attributeValue+" for submit element");
        	valueOfAttribute = $(Google.submit).getAttribute(attributeValue);
        }
		catch(Exception e){
			Serenity.recordReportData().withTitle("Failure").andContents("Could not get value for attribute " + attributeValue);
        	Settings.LOGGER.info("User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        }
		return valueOfAttribute;
    }

    public void verifyValueFromSubmit(String typeText) {
        //This function is for web element @FindBy(Google.submit);
        assertTrue("Actual value: " + $(Google.submit).getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,$(Google.submit).getAttribute("value")));
        Settings.LOGGER.info("User verifies value: " + typeText + "for Submit");
    }

    public void verifySubmitIsDisplayed() {
        //The below function is for web element @FindBy(Google.submit);
        assertTrue("Submit is not visible", $(Google.submit).isDisplayed());
        Settings.LOGGER.info("User verifies submit element is displayed");
    }

    public void verifySubmitContainsText(String typeText) {
        //The below function is for web element @FindBy(Google.submit);
        try{
			assertTrue("Actual text: " + $(Google.submit).getText(), StringUtils.containsIgnoreCase($(Google.submit).getText(), typeText));
        	Settings.LOGGER.info("User verifies submit contains " + typeText);
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void verifySubmitText(String typeText) {
        //The below function is for web element @FindBy(Google.submit);
        try{
			assertTrue("Actual text: " + $(Google.submit).getText(), StringUtils.equalsIgnoreCase(typeText,$(Google.submit).getText()));
        	Settings.LOGGER.info("User gets the text of submit element");
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }
}
