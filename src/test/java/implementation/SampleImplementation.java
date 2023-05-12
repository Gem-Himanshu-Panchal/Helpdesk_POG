package implementation;

import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.utils.GemJarUtils;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.gemini.generic.utils.GemJarGlobalVar;
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

public class SampleImplementation extends DriverAction {

    public void typeTextIntoFirstName(String typeText) {
        //The below function is for web element @FindBy(Sample.firstName);
        try{
			waitUntilElementAppear(Sample.firstName,Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut")));
			typeText(Sample.firstName,typeText);
        	Settings.LOGGER.info("User enters "+typeText+" as value");
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Enter the text in firstName field","Unable to Enter Text in firstName field", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Unable to Enter Text in firstName field");
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void typeTextAndEnterForFirstName(String typeText) {
        //The below function is for web element @FindBy(Sample.firstName);
        try{
			verifyFirstNameIsDisplayed();
			typeText(Sample.firstName,typeText);
        	 Actions action = new Actions(DriverManager.getWebDriver());;
        	 action.sendKeys(Keys.ENTER);;
        	Settings.LOGGER.info("User enters "+typeText+" as value and presses enter");
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Enter the text in firstName field and press enter","Unable to Enter Text in firstName field and perform enter action", STATUS.FAIL, takeSnapShot());
        				Settings.LOGGER.info("Unable to Enter Text in firstName field and perform enter action");
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void typeTextAndTabForFirstName(String typeText) {
        //The below function is for web element @FindBy(Sample.firstName);
        try{
			verifyFirstNameIsDisplayed();
			typeText(Sample.firstName,typeText);
        	Actions action = new Actions(DriverManager.getWebDriver());;
        	action.sendKeys(Keys.TAB);;
        	Settings.LOGGER.info("User enters "+typeText+" as value and presses Tab");
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Enter the text in firstName field and presses tab","Unable to Enter Text in firstName field and perform tab action", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Unable to Enter Text in firstName field and perform tab action");
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public String getTextFromFirstName() {
        //The below function is for web element @FindBy(Sample.firstName);
        String text = new String();
        try{
			verifyFirstNameIsEnabled();
			text = getElementText(Sample.firstName);
			GemTestReporter.addTestStep("Get text of firstName element","Successful able to fetch text of firstName element", STATUS.PASS, takeSnapShot());
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Get text of firstName element","Unable to fetch text of firstName element", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("Unable to fetch text of firstName, User gets an exception: "+e);
        } 
		return text;
    }

    public void verifyFirstNameIsEnabled() {
        //The below function is for web element @FindBy(Sample.firstName);
        try{
			WebDriverWait webDriverWait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut"))));
			webDriverWait.until(ExpectedConditions.presenceOfElementLocated(Sample.firstName));
			getElement(Sample.firstName).isEnabled();
        	GemTestReporter.addTestStep("Verify firstName field is enabled","firstName field is enabled", STATUS.PASS, takeSnapShot());
        	Settings.LOGGER.info("firstName field is enabled");}
		catch(Exception e){
			GemTestReporter.addTestStep("Verify firstName field is enabled","firstName field is not enabled", STATUS.FAIL, takeSnapShot());
        	Settings.LOGGER.info("firstName field is not enabled");
			Settings.LOGGER.info("User gets an exception: "+e);};
    }

    public void clearFirstName() {
        //The below function is for web element @FindBy(Sample.firstName);
        try{
			verifyFirstNameIsEnabled();
			clearText(Sample.firstName);
        	GemTestReporter.addTestStep("Clear text for firstName field","Input for firstName field cleared successfully", STATUS.PASS, takeSnapShot());
        	Settings.LOGGER.info("firstName field cleared successfully");}
		catch(Exception e){
			GemTestReporter.addTestStep("Clear text for firstName field","Unable to clear text for firstName field", STATUS.FAIL, takeSnapShot());
        	Settings.LOGGER.info("Unable to clear text for firstName field");
			Settings.LOGGER.info("User gets an exception: "+e);};
    }

    public String getAttributeFromFirstName(String attributeValue) {
        //This function is for web element @FindBy(Sample.firstName);
        String text = new String();
        try{
			waitUntilElementAppear(Sample.firstName,Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut")));
			text = getAttributeName(Sample.firstName, attributeValue);
        	if(!text.equals(""))
			{
				GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "firstName field","Successfully fetched " + attributeValue + " value for firstName", STATUS.PASS, takeSnapShot());
        		Settings.LOGGER.info("Successfully fetched " + attributeValue + " value for firstName");
			}
			 else 
			{
				GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "firstName field","Unable to get " + attributeValue + " value for firstName as attribute does not exist", STATUS.FAIL, takeSnapShot());
        		Settings.LOGGER.info("Unable to get " + attributeValue + " value for firstName as attribute does not exist");
			};
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "firstName field","Unable to get " + attributeValue + " value for firstName as attribute does not exist", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("Unable to get " + attributeValue + " value for firstName as attribute does not exist");
			Settings.LOGGER.info("User gets an exception: "+e);
        };
        return text;
    }

    public void verifyValueFromFirstName(String typeText) {
        //This function is for web element @FindBy(Sample.firstName);
        String text = new String();
        try{
			waitUntilElementAppear(Sample.firstName,Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut")));
			text = getAttributeName(Sample.firstName,"value");
        	if(typeText.equals(text)){
				GemTestReporter.addTestStep("Verify value of firstName matches " +typeText,"Value for firstName verified successfully", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Value for firstName verified successfully" );	}
			else{
				GemTestReporter.addTestStep("Verify value of firstName matches " +typeText,"Unable to verify value for firstName. Actual: "+text+" Expected: "+typeText, STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Unable to verify value for firstName. Actual: "+text+" Expected: "+typeText );	}
		}
		catch(Exception e){;
        	GemTestReporter.addTestStep("Verify value of firstName field","Unable to get attribute value", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("Unable to get attribute value");
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public String verifyAttributeValueForFirstName(String attributeName, String attributeValue) {
        //This function is for web element @FindBy(Sample.firstName);
        String text = new String();
        try{
			waitUntilElementAppear(Sample.firstName,Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut")));
			text = getAttributeName(Sample.firstName,attributeName);
        	if(attributeValue.equals(text)){
				GemTestReporter.addTestStep("Verify if value of '+attributeName+' matches" +attributeValue,"Validation Successful", STATUS.PASS, takeSnapShot());
        	}
			else{
				GemTestReporter.addTestStep("Verify if value of '+attributeName+' matches" +attributeValue,"Validation Failed", STATUS.FAIL, takeSnapShot());
        	}
		}
		catch(Exception e){;
        	GemTestReporter.addTestStep("Get value of Value attribute for firstName field","Unable to get attribute value", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("Unable to get attribute value");
			Settings.LOGGER.info("User gets an exception: "+e);
        };
        return text;
    }

    public void verifyValueClearedForFirstName() {
        //This function is for web element @FindBy(Sample.firstName);
        String text = new String();
        try{
			waitUntilElementAppear(Sample.firstName,Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut")));
			text = getAttributeName(Sample.firstName,"value");
        	if(text.equals("")){
				GemTestReporter.addTestStep("Verify value cleared for firstName field","Verified input value for firstName is successfully cleared", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Verified input value for firstName is successfully cleared");}
			else{
				GemTestReporter.addTestStep("Verify value cleared for firstName field","Input value for firstName not cleared successfully ", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Input value for firstName not cleared successfully ");}
		}
		catch(Exception e){;
        	GemTestReporter.addTestStep("Verify value gets cleared for firstName field","Unable to clear value", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("Unable to clear value");
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void hoverOverFirstName(String labelText) {
        //This function is for web element @FindBy(Sample.firstName);
        try{
			verifyFirstNameIsEnabled();
			hoverOver(Sample.firstName,labelText);
        } 
		catch(Exception e){
			GemTestReporter.addTestStep("Hover on "+labelText," Hovering on " + labelText + " Failed", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("User gets an exception: "+e);
			Settings.LOGGER.info(" Hovering on " + labelText + " Failed");
        };
    }

    public void verifyFirstNameExists() {
        //This function is for web element @FindBy(Sample.firstName);
        try{
			waitUntilElementAppear(Sample.firstName,Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut")));
			if(isExist(Sample.firstName)){;
        		GemTestReporter.addTestStep("Verify element firstName is present on Screen","firstName element is present on Screen", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("firstName is present on Screen");}	
			 else{
				GemTestReporter.addTestStep("Verify element firstName is present on Screen","firstName element is not present on Screen", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("firstName element is not present on Screen");}	
		}
		catch(Exception e){
			GemTestReporter.addTestStep("Verify element firstName is present on Screen","firstName element is not present on Screen", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("firstName element is not present on Screen");
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void scrollToFirstNameElement() {
        //This function is for web element @FindBy(Sample.firstName);
        try{
			scrollIntoView(Sample.firstName);
			waitUntilElementAppear(Sample.firstName,Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut")));
        	GemTestReporter.addTestStep("Scroll to firstName element","Successful able to scroll to firstName element", STATUS.PASS, takeSnapShot());
        	Settings.LOGGER.info("Successful able to scroll to firstName element");
			}			catch(Exception e){;
        	GemTestReporter.addTestStep("Scroll to firstName element","Unable to scroll to firstName element", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("Unable to scroll to firstName element");
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void verifyFirstNameIsDisplayed() {
        //The below function is for web element @FindBy(Sample.firstName);
        try{
			waitUntilElementAppear(Sample.firstName,Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut")));
			getElement(Sample.firstName).isDisplayed();
        	GemTestReporter.addTestStep("Verify firstName field is visible","firstName field is visible", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("firstName field is visible");}
		catch(Exception e){
			GemTestReporter.addTestStep("Verify firstName field is visible","firstName field is not visible", STATUS.FAIL, takeSnapShot());
        	Settings.LOGGER.info("firstName field is not visible");
			Settings.LOGGER.info("User gets an exception: "+e);};
    }

    public void verifyFirstNameContainsText(String typeText) {
        //The below function is for web element @FindBy(Sample.firstName);
        try{
			verifyFirstNameIsDisplayed();
			if(getElementText(Sample.firstName).contains(typeText)){;
        		GemTestReporter.addTestStep("Verify firstName field contains " + typeText,"Text of firstName field contains " + typeText, STATUS.PASS, takeSnapShot());
        		Settings.LOGGER.info("Text of firstName field contains" + typeText);
			}
			else{
				GemTestReporter.addTestStep("Verify firstName field contains " + typeText,"Text" + typeText + "is not present in firstName field. Expected: "+typeText+" Actual: "+ getElementText(Sample.firstName), STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Text of firstName field does not contain" + typeText + ". Expected: "+typeText+" Actual: "+getElementText(Sample.firstName));}	
		}
		catch(Exception e){
			GemTestReporter.addTestStep("Verify firstName field contains " + typeText,"Text of firstName field does not contain" + typeText + ". Expected: "+typeText+" Actual: "+getElementText(Sample.firstName), STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("Text of firstName field does not contain" + typeText + ". Expected: "+typeText+" Actual: "+getElementText(Sample.firstName));
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void verifyAttributeContainsValueForFirstName(String attribute, String typeText) {
        //The below function is for web element @FindBy(Sample.firstName);
        try{
			verifyFirstNameIsDisplayed();
			if(getElement(Sample.firstName).getAttribute(attribute).contains(typeText)){;
        		GemTestReporter.addTestStep("Verify attribute " + attribute + " of firstName field contains " + typeText,"Attribute " + attribute + " of firstName field contains " + typeText, STATUS.PASS, takeSnapShot());
        		Settings.LOGGER.info("Attribute " + attribute + " of firstName field contains" + typeText);
			}
			else{
				GemTestReporter.addTestStep("Verify attribute " + attribute + " of firstName field contains " + typeText, typeText + "is not present in firstName" + attribute + ". Expected: "+typeText+" Actual: "+ getElement(Sample.firstName).getAttribute(attribute), STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Attribute " + attribute + " of firstName field does not contain" + typeText + ". Expected: "+typeText+" Actual: "+getElement(Sample.firstName).getAttribute(attribute));}	
		}
		catch(Exception e){
			GemTestReporter.addTestStep("Verify attribute " + attribute + " of firstName field contains " + typeText, typeText + "is not present in firstName" + attribute + ". Expected: "+typeText+" Actual: "+ getElement(Sample.firstName), STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info( typeText + "is not present in firstName" + attribute + ". Expected: "+typeText+" Actual: "+ getElement(Sample.firstName));
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void verifyFirstNameText(String typeText) {
        //The below function is for web element @FindBy(Sample.firstName);
        try{
			verifyFirstNameIsDisplayed();
			if(getElementText(Sample.firstName).equals(typeText)){;
        		GemTestReporter.addTestStep("Verify text of firstName field is equal to " + typeText,"Text of firstName field is equal to " + typeText, STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Text of firstName field is equal to " + typeText);}	
			else{
				GemTestReporter.addTestStep("Verify text of firstName field is equal to " + typeText,"Text of firstName field is not equal. Expected: "+typeText+" Actual: "+getElementText(Sample.firstName), STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Text of firstName field is not equal. Expected: "+typeText+" Actual: "+getElementText(Sample.firstName));}	
		}
		catch(Exception e){
			GemTestReporter.addTestStep("Verify text of firstName field is equal to " + typeText,"Text of firstName field is not equal. Expected: "+typeText+" Actual: "+getElementText(Sample.firstName), STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("Text of firstName field is not equal. Expected: "+typeText+" Actual: "+getElementText(Sample.firstName));
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void typeTextIntoPassword(String typeText) {
        //The below function is for web element @FindBy(Sample.password);
        try{
			waitUntilElementAppear(Sample.password,Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut")));
			typeText(Sample.password,typeText);
        	Settings.LOGGER.info("User enters "+typeText+" as value");
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Enter the text in password field","Unable to Enter Text in password field", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Unable to Enter Text in password field");
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void typeTextAndEnterForPassword(String typeText) {
        //The below function is for web element @FindBy(Sample.password);
        try{
			verifyPasswordIsDisplayed();
			typeText(Sample.password,typeText);
        	 Actions action = new Actions(DriverManager.getWebDriver());;
        	 action.sendKeys(Keys.ENTER);;
        	Settings.LOGGER.info("User enters "+typeText+" as value and presses enter");
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Enter the text in password field and press enter","Unable to Enter Text in password field and perform enter action", STATUS.FAIL, takeSnapShot());
        				Settings.LOGGER.info("Unable to Enter Text in password field and perform enter action");
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void typeTextAndTabForPassword(String typeText) {
        //The below function is for web element @FindBy(Sample.password);
        try{
			verifyPasswordIsDisplayed();
			typeText(Sample.password,typeText);
        	Actions action = new Actions(DriverManager.getWebDriver());;
        	action.sendKeys(Keys.TAB);;
        	Settings.LOGGER.info("User enters "+typeText+" as value and presses Tab");
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Enter the text in password field and presses tab","Unable to Enter Text in password field and perform tab action", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Unable to Enter Text in password field and perform tab action");
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public String getTextFromPassword() {
        //The below function is for web element @FindBy(Sample.password);
        String text = new String();
        try{
			verifyPasswordIsEnabled();
			text = getElementText(Sample.password);
			GemTestReporter.addTestStep("Get text of password element","Successful able to fetch text of password element", STATUS.PASS, takeSnapShot());
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Get text of password element","Unable to fetch text of password element", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("Unable to fetch text of password, User gets an exception: "+e);
        } 
		return text;
    }

    public void verifyPasswordIsEnabled() {
        //The below function is for web element @FindBy(Sample.password);
        try{
			WebDriverWait webDriverWait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut"))));
			webDriverWait.until(ExpectedConditions.presenceOfElementLocated(Sample.password));
			getElement(Sample.password).isEnabled();
        	GemTestReporter.addTestStep("Verify password field is enabled","password field is enabled", STATUS.PASS, takeSnapShot());
        	Settings.LOGGER.info("password field is enabled");}
		catch(Exception e){
			GemTestReporter.addTestStep("Verify password field is enabled","password field is not enabled", STATUS.FAIL, takeSnapShot());
        	Settings.LOGGER.info("password field is not enabled");
			Settings.LOGGER.info("User gets an exception: "+e);};
    }

    public void clearPassword() {
        //The below function is for web element @FindBy(Sample.password);
        try{
			verifyPasswordIsEnabled();
			clearText(Sample.password);
        	GemTestReporter.addTestStep("Clear text for password field","Input for password field cleared successfully", STATUS.PASS, takeSnapShot());
        	Settings.LOGGER.info("password field cleared successfully");}
		catch(Exception e){
			GemTestReporter.addTestStep("Clear text for password field","Unable to clear text for password field", STATUS.FAIL, takeSnapShot());
        	Settings.LOGGER.info("Unable to clear text for password field");
			Settings.LOGGER.info("User gets an exception: "+e);};
    }

    public String getAttributeFromPassword(String attributeValue) {
        //This function is for web element @FindBy(Sample.password);
        String text = new String();
        try{
			waitUntilElementAppear(Sample.password,Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut")));
			text = getAttributeName(Sample.password, attributeValue);
        	if(!text.equals(""))
			{
				GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "password field","Successfully fetched " + attributeValue + " value for password", STATUS.PASS, takeSnapShot());
        		Settings.LOGGER.info("Successfully fetched " + attributeValue + " value for password");
			}
			 else 
			{
				GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "password field","Unable to get " + attributeValue + " value for password as attribute does not exist", STATUS.FAIL, takeSnapShot());
        		Settings.LOGGER.info("Unable to get " + attributeValue + " value for password as attribute does not exist");
			};
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "password field","Unable to get " + attributeValue + " value for password as attribute does not exist", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("Unable to get " + attributeValue + " value for password as attribute does not exist");
			Settings.LOGGER.info("User gets an exception: "+e);
        };
        return text;
    }

    public void verifyValueFromPassword(String typeText) {
        //This function is for web element @FindBy(Sample.password);
        String text = new String();
        try{
			waitUntilElementAppear(Sample.password,Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut")));
			text = getAttributeName(Sample.password,"value");
        	if(typeText.equals(text)){
				GemTestReporter.addTestStep("Verify value of password matches " +typeText,"Value for password verified successfully", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Value for password verified successfully" );	}
			else{
				GemTestReporter.addTestStep("Verify value of password matches " +typeText,"Unable to verify value for password. Actual: "+text+" Expected: "+typeText, STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Unable to verify value for password. Actual: "+text+" Expected: "+typeText );	}
		}
		catch(Exception e){;
        	GemTestReporter.addTestStep("Verify value of password field","Unable to get attribute value", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("Unable to get attribute value");
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public String verifyAttributeValueForPassword(String attributeName, String attributeValue) {
        //This function is for web element @FindBy(Sample.password);
        String text = new String();
        try{
			waitUntilElementAppear(Sample.password,Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut")));
			text = getAttributeName(Sample.password,attributeName);
        	if(attributeValue.equals(text)){
				GemTestReporter.addTestStep("Verify if value of '+attributeName+' matches" +attributeValue,"Validation Successful", STATUS.PASS, takeSnapShot());
        	}
			else{
				GemTestReporter.addTestStep("Verify if value of '+attributeName+' matches" +attributeValue,"Validation Failed", STATUS.FAIL, takeSnapShot());
        	}
		}
		catch(Exception e){;
        	GemTestReporter.addTestStep("Get value of Value attribute for password field","Unable to get attribute value", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("Unable to get attribute value");
			Settings.LOGGER.info("User gets an exception: "+e);
        };
        return text;
    }

    public void verifyValueClearedForPassword() {
        //This function is for web element @FindBy(Sample.password);
        String text = new String();
        try{
			waitUntilElementAppear(Sample.password,Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut")));
			text = getAttributeName(Sample.password,"value");
        	if(text.equals("")){
				GemTestReporter.addTestStep("Verify value cleared for password field","Verified input value for password is successfully cleared", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Verified input value for password is successfully cleared");}
			else{
				GemTestReporter.addTestStep("Verify value cleared for password field","Input value for password not cleared successfully ", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Input value for password not cleared successfully ");}
		}
		catch(Exception e){;
        	GemTestReporter.addTestStep("Verify value gets cleared for password field","Unable to clear value", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("Unable to clear value");
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void hoverOverPassword(String labelText) {
        //This function is for web element @FindBy(Sample.password);
        try{
			verifyPasswordIsEnabled();
			hoverOver(Sample.password,labelText);
        } 
		catch(Exception e){
			GemTestReporter.addTestStep("Hover on "+labelText," Hovering on " + labelText + " Failed", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("User gets an exception: "+e);
			Settings.LOGGER.info(" Hovering on " + labelText + " Failed");
        };
    }

    public void verifyPasswordExists() {
        //This function is for web element @FindBy(Sample.password);
        try{
			waitUntilElementAppear(Sample.password,Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut")));
			if(isExist(Sample.password)){;
        		GemTestReporter.addTestStep("Verify element password is present on Screen","password element is present on Screen", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("password is present on Screen");}	
			 else{
				GemTestReporter.addTestStep("Verify element password is present on Screen","password element is not present on Screen", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("password element is not present on Screen");}	
		}
		catch(Exception e){
			GemTestReporter.addTestStep("Verify element password is present on Screen","password element is not present on Screen", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("password element is not present on Screen");
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void scrollToPasswordElement() {
        //This function is for web element @FindBy(Sample.password);
        try{
			scrollIntoView(Sample.password);
			waitUntilElementAppear(Sample.password,Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut")));
        	GemTestReporter.addTestStep("Scroll to password element","Successful able to scroll to password element", STATUS.PASS, takeSnapShot());
        	Settings.LOGGER.info("Successful able to scroll to password element");
			}			catch(Exception e){;
        	GemTestReporter.addTestStep("Scroll to password element","Unable to scroll to password element", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("Unable to scroll to password element");
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void verifyPasswordIsDisplayed() {
        //The below function is for web element @FindBy(Sample.password);
        try{
			waitUntilElementAppear(Sample.password,Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut")));
			getElement(Sample.password).isDisplayed();
        	GemTestReporter.addTestStep("Verify password field is visible","password field is visible", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("password field is visible");}
		catch(Exception e){
			GemTestReporter.addTestStep("Verify password field is visible","password field is not visible", STATUS.FAIL, takeSnapShot());
        	Settings.LOGGER.info("password field is not visible");
			Settings.LOGGER.info("User gets an exception: "+e);};
    }

    public void verifyPasswordContainsText(String typeText) {
        //The below function is for web element @FindBy(Sample.password);
        try{
			verifyPasswordIsDisplayed();
			if(getElementText(Sample.password).contains(typeText)){;
        		GemTestReporter.addTestStep("Verify password field contains " + typeText,"Text of password field contains " + typeText, STATUS.PASS, takeSnapShot());
        		Settings.LOGGER.info("Text of password field contains" + typeText);
			}
			else{
				GemTestReporter.addTestStep("Verify password field contains " + typeText,"Text" + typeText + "is not present in password field. Expected: "+typeText+" Actual: "+ getElementText(Sample.password), STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Text of password field does not contain" + typeText + ". Expected: "+typeText+" Actual: "+getElementText(Sample.password));}	
		}
		catch(Exception e){
			GemTestReporter.addTestStep("Verify password field contains " + typeText,"Text of password field does not contain" + typeText + ". Expected: "+typeText+" Actual: "+getElementText(Sample.password), STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("Text of password field does not contain" + typeText + ". Expected: "+typeText+" Actual: "+getElementText(Sample.password));
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void verifyAttributeContainsValueForPassword(String attribute, String typeText) {
        //The below function is for web element @FindBy(Sample.password);
        try{
			verifyPasswordIsDisplayed();
			if(getElement(Sample.password).getAttribute(attribute).contains(typeText)){;
        		GemTestReporter.addTestStep("Verify attribute " + attribute + " of password field contains " + typeText,"Attribute " + attribute + " of password field contains " + typeText, STATUS.PASS, takeSnapShot());
        		Settings.LOGGER.info("Attribute " + attribute + " of password field contains" + typeText);
			}
			else{
				GemTestReporter.addTestStep("Verify attribute " + attribute + " of password field contains " + typeText, typeText + "is not present in password" + attribute + ". Expected: "+typeText+" Actual: "+ getElement(Sample.password).getAttribute(attribute), STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Attribute " + attribute + " of password field does not contain" + typeText + ". Expected: "+typeText+" Actual: "+getElement(Sample.password).getAttribute(attribute));}	
		}
		catch(Exception e){
			GemTestReporter.addTestStep("Verify attribute " + attribute + " of password field contains " + typeText, typeText + "is not present in password" + attribute + ". Expected: "+typeText+" Actual: "+ getElement(Sample.password), STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info( typeText + "is not present in password" + attribute + ". Expected: "+typeText+" Actual: "+ getElement(Sample.password));
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void verifyPasswordText(String typeText) {
        //The below function is for web element @FindBy(Sample.password);
        try{
			verifyPasswordIsDisplayed();
			if(getElementText(Sample.password).equals(typeText)){;
        		GemTestReporter.addTestStep("Verify text of password field is equal to " + typeText,"Text of password field is equal to " + typeText, STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Text of password field is equal to " + typeText);}	
			else{
				GemTestReporter.addTestStep("Verify text of password field is equal to " + typeText,"Text of password field is not equal. Expected: "+typeText+" Actual: "+getElementText(Sample.password), STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Text of password field is not equal. Expected: "+typeText+" Actual: "+getElementText(Sample.password));}	
		}
		catch(Exception e){
			GemTestReporter.addTestStep("Verify text of password field is equal to " + typeText,"Text of password field is not equal. Expected: "+typeText+" Actual: "+getElementText(Sample.password), STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("Text of password field is not equal. Expected: "+typeText+" Actual: "+getElementText(Sample.password));
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }
}
