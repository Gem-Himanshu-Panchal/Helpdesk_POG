package implementation;

import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverManager;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.utils.GemJarUtils;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.gemini.generic.utils.GemJarGlobalVar;
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

public class GoogleImplementation extends DriverAction {

    public void typeTextIntoFirstName(String typeText) {
        //The below function is for web element @FindBy(Google.firstName);
        try{
			typeText(Google.firstName,typeText);
        	Settings.LOGGER.info("User enters "+typeText+" as value");
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Enter the text in firstName field","Unable to Enter Text in firstName field", STATUS.FAIL, takeSnapShot());
        	Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void typeTextAndEnterForFirstName(String typeText) {
        //The below function is for web element @FindBy(Google.firstName);
        try{
			typeText(Google.firstName,typeText);
        			 Actions action = new Actions(DriverManager.getWebDriver());;
        			 action.sendKeys(Keys.ENTER);;
        	Settings.LOGGER.info("User enters "+typeText+" as value and presses enter");
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Enter the text in firstName field and press enter","Unable to Enter Text in firstName field", STATUS.FAIL, takeSnapShot());
        	Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void typeTextAndTabForFirstName(String typeText) {
        //The below function is for web element @FindBy(Google.firstName);
        try{
			typeText(Google.firstName,typeText);
        Actions action = new Actions(DriverManager.getWebDriver());;
        action.sendKeys(Keys.TAB);;
        	Settings.LOGGER.info("User enters "+typeText+" as value and presses Tab");
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Enter the text in firstName field and presses tab","Unable to Enter Text in firstName field", STATUS.FAIL, takeSnapShot());
        	Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public String getTextFromFirstName() {
        //The below function is for web element @FindBy(Google.firstName);
        String text = new String();
        try{
			text = getElementText(Google.firstName);
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        } 
		return text;
    }

    public void verifyFirstNameIsEnabled() {
        //The below function is for web element @FindBy(Google.firstName);
        try{
			getElement(Google.firstName).isEnabled();
        	GemTestReporter.addTestStep("Verify firstName field is enabled","firstName field is enabled", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("firstName field is enabled");}
		catch(Exception e){
			GemTestReporter.addTestStep("Verify firstName field is enabled","F field is not enabled", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("firstName field is not enabled");};
    }

    public void clearFirstName() {
        //The below function is for web element @FindBy(Google.firstName);
        try{
			clearText(Google.firstName);
        	GemTestReporter.addTestStep("Clear text for firstName field","Input for firstName field cleared successfully", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("firstName field cleared successfully");}
		catch(Exception e){
			GemTestReporter.addTestStep("Clear text for firstName field","firstNameUnable to clear text for firstName field", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Unable to clear text for firstName field");};
    }

    public String getAttributeFromFirstName(String attributeValue) {
        //This function is for web element @FindBy(Google.firstName);
        String text = new String();
        try{
			text = getAttributeName(Google.firstName, attributeValue);
        	if(!text.equals("")){GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "firstName field","Successfully fetched " + attributeValue + " value for firstName", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Successfully fetched " + attributeValue + " value for firstName");}
			 else {GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "firstName field","Unable to get " + attributeValue + " value for firstName as attribute does not exist", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Unable to get " + attributeValue + " value for firstName as attribute does not exist");};
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "firstName field","Unable to get " + attributeValue + " value for firstName as attribute does not exist", STATUS.FAIL, takeSnapShot());
        };
        return text;
    }

    public void verifyValueFromFirstName(String typeText) {
        //This function is for web element @FindBy(Google.firstName);
        String text = new String();
        try{
			text = getAttributeName(Google.firstName,"value");
        	if(typeText.equals(text)){
				GemTestReporter.addTestStep("Verify value of firstName matches " +typeText,"Value for firstName verified successfully", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Value for firstName verified successfully" );	}
			else{
				GemTestReporter.addTestStep("Verify value of firstName matches " +typeText,"Unable to verify value for firstName. Actual: "+text+" Expected: "+typeText, STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Unable to verify value for firstName. Actual: "+text+" Expected: "+typeText );	}
		}
		catch(Exception e){;
        	GemTestReporter.addTestStep("Verify value of firstName field","Unable to get attribute value", STATUS.FAIL, takeSnapShot());
        };
    }

    public String verifyAttributeValueForFirstName(String attributeName, String attributeValue) {
        //This function is for web element @FindBy(Google.firstName);
        String text = new String();
        try{
			text = getAttributeName(Google.firstName,attributeName);
        	if(attributeValue.equals(text)){
				GemTestReporter.addTestStep("Verify if value of '+attributeName+' matches" +attributeValue,"Validation Successful", STATUS.PASS, takeSnapShot());
        	}
			else{
				GemTestReporter.addTestStep("Verify if value of '+attributeName+' matches" +attributeValue,"Validation Failed", STATUS.FAIL, takeSnapShot());
        	}
		}
		catch(Exception e){;
        	GemTestReporter.addTestStep("Get value of Value attribute for firstName field","Unable to get attribute value", STATUS.FAIL, takeSnapShot());
        };
        return text;
    }

    public void verifyValueClearedForFirstName() {
        //This function is for web element @FindBy(Google.firstName);
        String text = new String();
        try{
			text = getAttributeName(Google.firstName,"value");
        	if(text.equals("")){
				GemTestReporter.addTestStep("Verify value cleared for firstName field","Verified input value for firstName is successfully cleared", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Verified input value for firstName is successfully cleared");}
			else{
				GemTestReporter.addTestStep("Verify value cleared for firstName field","Input value for firstName not cleared successfully ", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Input value for firstName not cleared successfully ");}
		}
		catch(Exception e){;
        	GemTestReporter.addTestStep("Verify value gets cleared for firstName field","Unable to clear value", STATUS.FAIL, takeSnapShot());
        };
    }

    public void verifyFirstNameIsDisplayed() {
        //The below function is for web element @FindBy(Google.firstName);
        try{
			getElement(Google.firstName).isDisplayed();
        	GemTestReporter.addTestStep("Verify firstName field is visible","firstName field is visible", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("firstName field is visible");}
		catch(Exception e){
			GemTestReporter.addTestStep("Verify firstName field is visible","firstName field is not visible", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("firstName field is not visible");};
    }

    public void verifyFirstNameText(String typeText) {
        //The below function is for web element @FindBy(Google.firstName);
        try{
			if(getElementText(Google.firstName).equals(typeText)){;
        		GemTestReporter.addTestStep("Verify text of firstName field is equal to " + typeText,"Text of firstName field is equal to " + typeText, STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Text of firstName field is equal to " + typeText);}	
 else{
				GemTestReporter.addTestStep("Verify text of firstName field is equal to " + typeText,"Text of firstName field is not equal. Expected: "+typeText+" Actual: "+getElementText(Google.firstName), STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Text of firstName field is not equal. Expected: "+typeText+" Actual: "+getElementText(Google.firstName));}	
		}
		catch(Exception e){
			GemTestReporter.addTestStep("Check if text inside firstName is equal to " + typeText,"Text inside firstName is not equal", STATUS.FAIL, takeSnapShot());
        };
    }

    public void typeTextIntoPassword(String typeText) {
        //The below function is for web element @FindBy(Google.password);
        try{
			typeText(Google.password,typeText);
        	Settings.LOGGER.info("User enters "+typeText+" as value");
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Enter the text in password field","Unable to Enter Text in password field", STATUS.FAIL, takeSnapShot());
        	Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void typeTextAndEnterForPassword(String typeText) {
        //The below function is for web element @FindBy(Google.password);
        try{
			typeText(Google.password,typeText);
        			 Actions action = new Actions(DriverManager.getWebDriver());;
        			 action.sendKeys(Keys.ENTER);;
        	Settings.LOGGER.info("User enters "+typeText+" as value and presses enter");
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Enter the text in password field and press enter","Unable to Enter Text in password field", STATUS.FAIL, takeSnapShot());
        	Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void typeTextAndTabForPassword(String typeText) {
        //The below function is for web element @FindBy(Google.password);
        try{
			typeText(Google.password,typeText);
        Actions action = new Actions(DriverManager.getWebDriver());;
        action.sendKeys(Keys.TAB);;
        	Settings.LOGGER.info("User enters "+typeText+" as value and presses Tab");
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Enter the text in password field and presses tab","Unable to Enter Text in password field", STATUS.FAIL, takeSnapShot());
        	Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public String getTextFromPassword() {
        //The below function is for web element @FindBy(Google.password);
        String text = new String();
        try{
			text = getElementText(Google.password);
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        } 
		return text;
    }

    public void verifyPasswordIsEnabled() {
        //The below function is for web element @FindBy(Google.password);
        try{
			getElement(Google.password).isEnabled();
        	GemTestReporter.addTestStep("Verify password field is enabled","password field is enabled", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("password field is enabled");}
		catch(Exception e){
			GemTestReporter.addTestStep("Verify password field is enabled","P field is not enabled", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("password field is not enabled");};
    }

    public void clearPassword() {
        //The below function is for web element @FindBy(Google.password);
        try{
			clearText(Google.password);
        	GemTestReporter.addTestStep("Clear text for password field","Input for password field cleared successfully", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("password field cleared successfully");}
		catch(Exception e){
			GemTestReporter.addTestStep("Clear text for password field","passwordUnable to clear text for password field", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Unable to clear text for password field");};
    }

    public String getAttributeFromPassword(String attributeValue) {
        //This function is for web element @FindBy(Google.password);
        String text = new String();
        try{
			text = getAttributeName(Google.password, attributeValue);
        	if(!text.equals("")){GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "password field","Successfully fetched " + attributeValue + " value for password", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Successfully fetched " + attributeValue + " value for password");}
			 else {GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "password field","Unable to get " + attributeValue + " value for password as attribute does not exist", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Unable to get " + attributeValue + " value for password as attribute does not exist");};
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "password field","Unable to get " + attributeValue + " value for password as attribute does not exist", STATUS.FAIL, takeSnapShot());
        };
        return text;
    }

    public void verifyValueFromPassword(String typeText) {
        //This function is for web element @FindBy(Google.password);
        String text = new String();
        try{
			text = getAttributeName(Google.password,"value");
        	if(typeText.equals(text)){
				GemTestReporter.addTestStep("Verify value of password matches " +typeText,"Value for password verified successfully", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Value for password verified successfully" );	}
			else{
				GemTestReporter.addTestStep("Verify value of password matches " +typeText,"Unable to verify value for password. Actual: "+text+" Expected: "+typeText, STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Unable to verify value for password. Actual: "+text+" Expected: "+typeText );	}
		}
		catch(Exception e){;
        	GemTestReporter.addTestStep("Verify value of password field","Unable to get attribute value", STATUS.FAIL, takeSnapShot());
        };
    }

    public String verifyAttributeValueForPassword(String attributeName, String attributeValue) {
        //This function is for web element @FindBy(Google.password);
        String text = new String();
        try{
			text = getAttributeName(Google.password,attributeName);
        	if(attributeValue.equals(text)){
				GemTestReporter.addTestStep("Verify if value of '+attributeName+' matches" +attributeValue,"Validation Successful", STATUS.PASS, takeSnapShot());
        	}
			else{
				GemTestReporter.addTestStep("Verify if value of '+attributeName+' matches" +attributeValue,"Validation Failed", STATUS.FAIL, takeSnapShot());
        	}
		}
		catch(Exception e){;
        	GemTestReporter.addTestStep("Get value of Value attribute for password field","Unable to get attribute value", STATUS.FAIL, takeSnapShot());
        };
        return text;
    }

    public void verifyValueClearedForPassword() {
        //This function is for web element @FindBy(Google.password);
        String text = new String();
        try{
			text = getAttributeName(Google.password,"value");
        	if(text.equals("")){
				GemTestReporter.addTestStep("Verify value cleared for password field","Verified input value for password is successfully cleared", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Verified input value for password is successfully cleared");}
			else{
				GemTestReporter.addTestStep("Verify value cleared for password field","Input value for password not cleared successfully ", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Input value for password not cleared successfully ");}
		}
		catch(Exception e){;
        	GemTestReporter.addTestStep("Verify value gets cleared for password field","Unable to clear value", STATUS.FAIL, takeSnapShot());
        };
    }

    public void verifyPasswordIsDisplayed() {
        //The below function is for web element @FindBy(Google.password);
        try{
			getElement(Google.password).isDisplayed();
        	GemTestReporter.addTestStep("Verify password field is visible","password field is visible", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("password field is visible");}
		catch(Exception e){
			GemTestReporter.addTestStep("Verify password field is visible","password field is not visible", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("password field is not visible");};
    }

    public void verifyPasswordText(String typeText) {
        //The below function is for web element @FindBy(Google.password);
        try{
			if(getElementText(Google.password).equals(typeText)){;
        		GemTestReporter.addTestStep("Verify text of password field is equal to " + typeText,"Text of password field is equal to " + typeText, STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Text of password field is equal to " + typeText);}	
 else{
				GemTestReporter.addTestStep("Verify text of password field is equal to " + typeText,"Text of password field is not equal. Expected: "+typeText+" Actual: "+getElementText(Google.password), STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Text of password field is not equal. Expected: "+typeText+" Actual: "+getElementText(Google.password));}	
		}
		catch(Exception e){
			GemTestReporter.addTestStep("Check if text inside password is equal to " + typeText,"Text inside password is not equal", STATUS.FAIL, takeSnapShot());
        };
    }

    public void typeTextIntoLastName(String typeText) {
        //The below function is for web element @FindBy(Google.lastName);
        try{
			typeText(Google.lastName,typeText);
        	Settings.LOGGER.info("User enters "+typeText+" as value");
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Enter the text in lastName field","Unable to Enter Text in lastName field", STATUS.FAIL, takeSnapShot());
        	Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void typeTextAndEnterForLastName(String typeText) {
        //The below function is for web element @FindBy(Google.lastName);
        try{
			typeText(Google.lastName,typeText);
        			 Actions action = new Actions(DriverManager.getWebDriver());;
        			 action.sendKeys(Keys.ENTER);;
        	Settings.LOGGER.info("User enters "+typeText+" as value and presses enter");
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Enter the text in lastName field and press enter","Unable to Enter Text in lastName field", STATUS.FAIL, takeSnapShot());
        	Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void typeTextAndTabForLastName(String typeText) {
        //The below function is for web element @FindBy(Google.lastName);
        try{
			typeText(Google.lastName,typeText);
        Actions action = new Actions(DriverManager.getWebDriver());;
        action.sendKeys(Keys.TAB);;
        	Settings.LOGGER.info("User enters "+typeText+" as value and presses Tab");
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Enter the text in lastName field and presses tab","Unable to Enter Text in lastName field", STATUS.FAIL, takeSnapShot());
        	Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public String getTextFromLastName() {
        //The below function is for web element @FindBy(Google.lastName);
        String text = new String();
        try{
			text = getElementText(Google.lastName);
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        } 
		return text;
    }

    public void verifyLastNameIsEnabled() {
        //The below function is for web element @FindBy(Google.lastName);
        try{
			getElement(Google.lastName).isEnabled();
        	GemTestReporter.addTestStep("Verify lastName field is enabled","lastName field is enabled", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("lastName field is enabled");}
		catch(Exception e){
			GemTestReporter.addTestStep("Verify lastName field is enabled","L field is not enabled", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("lastName field is not enabled");};
    }

    public void clearLastName() {
        //The below function is for web element @FindBy(Google.lastName);
        try{
			clearText(Google.lastName);
        	GemTestReporter.addTestStep("Clear text for lastName field","Input for lastName field cleared successfully", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("lastName field cleared successfully");}
		catch(Exception e){
			GemTestReporter.addTestStep("Clear text for lastName field","lastNameUnable to clear text for lastName field", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Unable to clear text for lastName field");};
    }

    public String getAttributeFromLastName(String attributeValue) {
        //This function is for web element @FindBy(Google.lastName);
        String text = new String();
        try{
			text = getAttributeName(Google.lastName, attributeValue);
        	if(!text.equals("")){GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "lastName field","Successfully fetched " + attributeValue + " value for lastName", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Successfully fetched " + attributeValue + " value for lastName");}
			 else {GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "lastName field","Unable to get " + attributeValue + " value for lastName as attribute does not exist", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Unable to get " + attributeValue + " value for lastName as attribute does not exist");};
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "lastName field","Unable to get " + attributeValue + " value for lastName as attribute does not exist", STATUS.FAIL, takeSnapShot());
        };
        return text;
    }

    public void verifyValueFromLastName(String typeText) {
        //This function is for web element @FindBy(Google.lastName);
        String text = new String();
        try{
			text = getAttributeName(Google.lastName,"value");
        	if(typeText.equals(text)){
				GemTestReporter.addTestStep("Verify value of lastName matches " +typeText,"Value for lastName verified successfully", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Value for lastName verified successfully" );	}
			else{
				GemTestReporter.addTestStep("Verify value of lastName matches " +typeText,"Unable to verify value for lastName. Actual: "+text+" Expected: "+typeText, STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Unable to verify value for lastName. Actual: "+text+" Expected: "+typeText );	}
		}
		catch(Exception e){;
        	GemTestReporter.addTestStep("Verify value of lastName field","Unable to get attribute value", STATUS.FAIL, takeSnapShot());
        };
    }

    public String verifyAttributeValueForLastName(String attributeName, String attributeValue) {
        //This function is for web element @FindBy(Google.lastName);
        String text = new String();
        try{
			text = getAttributeName(Google.lastName,attributeName);
        	if(attributeValue.equals(text)){
				GemTestReporter.addTestStep("Verify if value of '+attributeName+' matches" +attributeValue,"Validation Successful", STATUS.PASS, takeSnapShot());
        	}
			else{
				GemTestReporter.addTestStep("Verify if value of '+attributeName+' matches" +attributeValue,"Validation Failed", STATUS.FAIL, takeSnapShot());
        	}
		}
		catch(Exception e){;
        	GemTestReporter.addTestStep("Get value of Value attribute for lastName field","Unable to get attribute value", STATUS.FAIL, takeSnapShot());
        };
        return text;
    }

    public void verifyValueClearedForLastName() {
        //This function is for web element @FindBy(Google.lastName);
        String text = new String();
        try{
			text = getAttributeName(Google.lastName,"value");
        	if(text.equals("")){
				GemTestReporter.addTestStep("Verify value cleared for lastName field","Verified input value for lastName is successfully cleared", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Verified input value for lastName is successfully cleared");}
			else{
				GemTestReporter.addTestStep("Verify value cleared for lastName field","Input value for lastName not cleared successfully ", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Input value for lastName not cleared successfully ");}
		}
		catch(Exception e){;
        	GemTestReporter.addTestStep("Verify value gets cleared for lastName field","Unable to clear value", STATUS.FAIL, takeSnapShot());
        };
    }

    public void verifyLastNameIsDisplayed() {
        //The below function is for web element @FindBy(Google.lastName);
        try{
			getElement(Google.lastName).isDisplayed();
        	GemTestReporter.addTestStep("Verify lastName field is visible","lastName field is visible", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("lastName field is visible");}
		catch(Exception e){
			GemTestReporter.addTestStep("Verify lastName field is visible","lastName field is not visible", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("lastName field is not visible");};
    }

    public void verifyLastNameText(String typeText) {
        //The below function is for web element @FindBy(Google.lastName);
        try{
			if(getElementText(Google.lastName).equals(typeText)){;
        		GemTestReporter.addTestStep("Verify text of lastName field is equal to " + typeText,"Text of lastName field is equal to " + typeText, STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Text of lastName field is equal to " + typeText);}	
 else{
				GemTestReporter.addTestStep("Verify text of lastName field is equal to " + typeText,"Text of lastName field is not equal. Expected: "+typeText+" Actual: "+getElementText(Google.lastName), STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Text of lastName field is not equal. Expected: "+typeText+" Actual: "+getElementText(Google.lastName));}	
		}
		catch(Exception e){
			GemTestReporter.addTestStep("Check if text inside lastName is equal to " + typeText,"Text inside lastName is not equal", STATUS.FAIL, takeSnapShot());
        };
    }

    public void typeTextIntoHeading(String typeText) {
        //The below function is for web element @FindBy(Google.heading);
        try{
			typeText(Google.heading,typeText);
        	Settings.LOGGER.info("User enters "+typeText+" as value");
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Enter the text in heading field","Unable to Enter Text in heading field", STATUS.FAIL, takeSnapShot());
        	Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void typeTextAndEnterForHeading(String typeText) {
        //The below function is for web element @FindBy(Google.heading);
        try{
			typeText(Google.heading,typeText);
        			 Actions action = new Actions(DriverManager.getWebDriver());;
        			 action.sendKeys(Keys.ENTER);;
        	Settings.LOGGER.info("User enters "+typeText+" as value and presses enter");
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Enter the text in heading field and press enter","Unable to Enter Text in heading field", STATUS.FAIL, takeSnapShot());
        	Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void typeTextAndTabForHeading(String typeText) {
        //The below function is for web element @FindBy(Google.heading);
        try{
			typeText(Google.heading,typeText);
        Actions action = new Actions(DriverManager.getWebDriver());;
        action.sendKeys(Keys.TAB);;
        	Settings.LOGGER.info("User enters "+typeText+" as value and presses Tab");
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Enter the text in heading field and presses tab","Unable to Enter Text in heading field", STATUS.FAIL, takeSnapShot());
        	Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public String getTextFromHeading() {
        //The below function is for web element @FindBy(Google.heading);
        String text = new String();
        try{
			text = getElementText(Google.heading);
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        } 
		return text;
    }

    public void verifyHeadingIsEnabled() {
        //The below function is for web element @FindBy(Google.heading);
        try{
			getElement(Google.heading).isEnabled();
        	GemTestReporter.addTestStep("Verify heading field is enabled","heading field is enabled", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("heading field is enabled");}
		catch(Exception e){
			GemTestReporter.addTestStep("Verify heading field is enabled","H field is not enabled", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("heading field is not enabled");};
    }

    public void clearHeading() {
        //The below function is for web element @FindBy(Google.heading);
        try{
			clearText(Google.heading);
        	GemTestReporter.addTestStep("Clear text for heading field","Input for heading field cleared successfully", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("heading field cleared successfully");}
		catch(Exception e){
			GemTestReporter.addTestStep("Clear text for heading field","headingUnable to clear text for heading field", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Unable to clear text for heading field");};
    }

    public String getAttributeFromHeading(String attributeValue) {
        //This function is for web element @FindBy(Google.heading);
        String text = new String();
        try{
			text = getAttributeName(Google.heading, attributeValue);
        	if(!text.equals("")){GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "heading field","Successfully fetched " + attributeValue + " value for heading", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Successfully fetched " + attributeValue + " value for heading");}
			 else {GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "heading field","Unable to get " + attributeValue + " value for heading as attribute does not exist", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Unable to get " + attributeValue + " value for heading as attribute does not exist");};
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "heading field","Unable to get " + attributeValue + " value for heading as attribute does not exist", STATUS.FAIL, takeSnapShot());
        };
        return text;
    }

    public void verifyValueFromHeading(String typeText) {
        //This function is for web element @FindBy(Google.heading);
        String text = new String();
        try{
			text = getAttributeName(Google.heading,"value");
        	if(typeText.equals(text)){
				GemTestReporter.addTestStep("Verify value of heading matches " +typeText,"Value for heading verified successfully", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Value for heading verified successfully" );	}
			else{
				GemTestReporter.addTestStep("Verify value of heading matches " +typeText,"Unable to verify value for heading. Actual: "+text+" Expected: "+typeText, STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Unable to verify value for heading. Actual: "+text+" Expected: "+typeText );	}
		}
		catch(Exception e){;
        	GemTestReporter.addTestStep("Verify value of heading field","Unable to get attribute value", STATUS.FAIL, takeSnapShot());
        };
    }

    public String verifyAttributeValueForHeading(String attributeName, String attributeValue) {
        //This function is for web element @FindBy(Google.heading);
        String text = new String();
        try{
			text = getAttributeName(Google.heading,attributeName);
        	if(attributeValue.equals(text)){
				GemTestReporter.addTestStep("Verify if value of '+attributeName+' matches" +attributeValue,"Validation Successful", STATUS.PASS, takeSnapShot());
        	}
			else{
				GemTestReporter.addTestStep("Verify if value of '+attributeName+' matches" +attributeValue,"Validation Failed", STATUS.FAIL, takeSnapShot());
        	}
		}
		catch(Exception e){;
        	GemTestReporter.addTestStep("Get value of Value attribute for heading field","Unable to get attribute value", STATUS.FAIL, takeSnapShot());
        };
        return text;
    }

    public void verifyValueClearedForHeading() {
        //This function is for web element @FindBy(Google.heading);
        String text = new String();
        try{
			text = getAttributeName(Google.heading,"value");
        	if(text.equals("")){
				GemTestReporter.addTestStep("Verify value cleared for heading field","Verified input value for heading is successfully cleared", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Verified input value for heading is successfully cleared");}
			else{
				GemTestReporter.addTestStep("Verify value cleared for heading field","Input value for heading not cleared successfully ", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Input value for heading not cleared successfully ");}
		}
		catch(Exception e){;
        	GemTestReporter.addTestStep("Verify value gets cleared for heading field","Unable to clear value", STATUS.FAIL, takeSnapShot());
        };
    }

    public void verifyHeadingIsDisplayed() {
        //The below function is for web element @FindBy(Google.heading);
        try{
			getElement(Google.heading).isDisplayed();
        	GemTestReporter.addTestStep("Verify heading field is visible","heading field is visible", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("heading field is visible");}
		catch(Exception e){
			GemTestReporter.addTestStep("Verify heading field is visible","heading field is not visible", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("heading field is not visible");};
    }

    public void verifyHeadingText(String typeText) {
        //The below function is for web element @FindBy(Google.heading);
        try{
			if(getElementText(Google.heading).equals(typeText)){;
        		GemTestReporter.addTestStep("Verify text of heading field is equal to " + typeText,"Text of heading field is equal to " + typeText, STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Text of heading field is equal to " + typeText);}	
 else{
				GemTestReporter.addTestStep("Verify text of heading field is equal to " + typeText,"Text of heading field is not equal. Expected: "+typeText+" Actual: "+getElementText(Google.heading), STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Text of heading field is not equal. Expected: "+typeText+" Actual: "+getElementText(Google.heading));}	
		}
		catch(Exception e){
			GemTestReporter.addTestStep("Check if text inside heading is equal to " + typeText,"Text inside heading is not equal", STATUS.FAIL, takeSnapShot());
        };
    }

    public void typeTextIntoBirthday(String typeText) {
        //The below function is for web element @FindBy(Google.birthday);
        try{
			typeText(Google.birthday,typeText);
        	Settings.LOGGER.info("User enters "+typeText+" as value");
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Enter the text in birthday field","Unable to Enter Text in birthday field", STATUS.FAIL, takeSnapShot());
        	Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void typeTextAndEnterForBirthday(String typeText) {
        //The below function is for web element @FindBy(Google.birthday);
        try{
			typeText(Google.birthday,typeText);
        			 Actions action = new Actions(DriverManager.getWebDriver());;
        			 action.sendKeys(Keys.ENTER);;
        	Settings.LOGGER.info("User enters "+typeText+" as value and presses enter");
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Enter the text in birthday field and press enter","Unable to Enter Text in birthday field", STATUS.FAIL, takeSnapShot());
        	Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void typeTextAndTabForBirthday(String typeText) {
        //The below function is for web element @FindBy(Google.birthday);
        try{
			typeText(Google.birthday,typeText);
        Actions action = new Actions(DriverManager.getWebDriver());;
        action.sendKeys(Keys.TAB);;
        	Settings.LOGGER.info("User enters "+typeText+" as value and presses Tab");
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Enter the text in birthday field and presses tab","Unable to Enter Text in birthday field", STATUS.FAIL, takeSnapShot());
        	Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public String getTextFromBirthday() {
        //The below function is for web element @FindBy(Google.birthday);
        String text = new String();
        try{
			text = getElementText(Google.birthday);
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        } 
		return text;
    }

    public void verifyBirthdayIsEnabled() {
        //The below function is for web element @FindBy(Google.birthday);
        try{
			getElement(Google.birthday).isEnabled();
        	GemTestReporter.addTestStep("Verify birthday field is enabled","birthday field is enabled", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("birthday field is enabled");}
		catch(Exception e){
			GemTestReporter.addTestStep("Verify birthday field is enabled","B field is not enabled", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("birthday field is not enabled");};
    }

    public void clearBirthday() {
        //The below function is for web element @FindBy(Google.birthday);
        try{
			clearText(Google.birthday);
        	GemTestReporter.addTestStep("Clear text for birthday field","Input for birthday field cleared successfully", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("birthday field cleared successfully");}
		catch(Exception e){
			GemTestReporter.addTestStep("Clear text for birthday field","birthdayUnable to clear text for birthday field", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Unable to clear text for birthday field");};
    }

    public String getAttributeFromBirthday(String attributeValue) {
        //This function is for web element @FindBy(Google.birthday);
        String text = new String();
        try{
			text = getAttributeName(Google.birthday, attributeValue);
        	if(!text.equals("")){GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "birthday field","Successfully fetched " + attributeValue + " value for birthday", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Successfully fetched " + attributeValue + " value for birthday");}
			 else {GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "birthday field","Unable to get " + attributeValue + " value for birthday as attribute does not exist", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Unable to get " + attributeValue + " value for birthday as attribute does not exist");};
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "birthday field","Unable to get " + attributeValue + " value for birthday as attribute does not exist", STATUS.FAIL, takeSnapShot());
        };
        return text;
    }

    public void verifyValueFromBirthday(String typeText) {
        //This function is for web element @FindBy(Google.birthday);
        String text = new String();
        try{
			text = getAttributeName(Google.birthday,"value");
        	if(typeText.equals(text)){
				GemTestReporter.addTestStep("Verify value of birthday matches " +typeText,"Value for birthday verified successfully", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Value for birthday verified successfully" );	}
			else{
				GemTestReporter.addTestStep("Verify value of birthday matches " +typeText,"Unable to verify value for birthday. Actual: "+text+" Expected: "+typeText, STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Unable to verify value for birthday. Actual: "+text+" Expected: "+typeText );	}
		}
		catch(Exception e){;
        	GemTestReporter.addTestStep("Verify value of birthday field","Unable to get attribute value", STATUS.FAIL, takeSnapShot());
        };
    }

    public String verifyAttributeValueForBirthday(String attributeName, String attributeValue) {
        //This function is for web element @FindBy(Google.birthday);
        String text = new String();
        try{
			text = getAttributeName(Google.birthday,attributeName);
        	if(attributeValue.equals(text)){
				GemTestReporter.addTestStep("Verify if value of '+attributeName+' matches" +attributeValue,"Validation Successful", STATUS.PASS, takeSnapShot());
        	}
			else{
				GemTestReporter.addTestStep("Verify if value of '+attributeName+' matches" +attributeValue,"Validation Failed", STATUS.FAIL, takeSnapShot());
        	}
		}
		catch(Exception e){;
        	GemTestReporter.addTestStep("Get value of Value attribute for birthday field","Unable to get attribute value", STATUS.FAIL, takeSnapShot());
        };
        return text;
    }

    public void verifyValueClearedForBirthday() {
        //This function is for web element @FindBy(Google.birthday);
        String text = new String();
        try{
			text = getAttributeName(Google.birthday,"value");
        	if(text.equals("")){
				GemTestReporter.addTestStep("Verify value cleared for birthday field","Verified input value for birthday is successfully cleared", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Verified input value for birthday is successfully cleared");}
			else{
				GemTestReporter.addTestStep("Verify value cleared for birthday field","Input value for birthday not cleared successfully ", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Input value for birthday not cleared successfully ");}
		}
		catch(Exception e){;
        	GemTestReporter.addTestStep("Verify value gets cleared for birthday field","Unable to clear value", STATUS.FAIL, takeSnapShot());
        };
    }

    public void verifyBirthdayIsDisplayed() {
        //The below function is for web element @FindBy(Google.birthday);
        try{
			getElement(Google.birthday).isDisplayed();
        	GemTestReporter.addTestStep("Verify birthday field is visible","birthday field is visible", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("birthday field is visible");}
		catch(Exception e){
			GemTestReporter.addTestStep("Verify birthday field is visible","birthday field is not visible", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("birthday field is not visible");};
    }

    public void verifyBirthdayText(String typeText) {
        //The below function is for web element @FindBy(Google.birthday);
        try{
			if(getElementText(Google.birthday).equals(typeText)){;
        		GemTestReporter.addTestStep("Verify text of birthday field is equal to " + typeText,"Text of birthday field is equal to " + typeText, STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Text of birthday field is equal to " + typeText);}	
 else{
				GemTestReporter.addTestStep("Verify text of birthday field is equal to " + typeText,"Text of birthday field is not equal. Expected: "+typeText+" Actual: "+getElementText(Google.birthday), STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Text of birthday field is not equal. Expected: "+typeText+" Actual: "+getElementText(Google.birthday));}	
		}
		catch(Exception e){
			GemTestReporter.addTestStep("Check if text inside birthday is equal to " + typeText,"Text inside birthday is not equal", STATUS.FAIL, takeSnapShot());
        };
    }

    public void selectCss() {
        //The below function is for web element @FindBy(Google.css);
        try{
			click(Google.css);
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void verifyCssIsEnabled() {
        //The below function is for web element @FindBy(Google.css);
        try{
			getElement(Google.css).isEnabled();
        	GemTestReporter.addTestStep("Verify css field is enabled","css field is enabled", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("css field is enabled");}
		catch(Exception e){
			GemTestReporter.addTestStep("Verify css field is enabled","C field is not enabled", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("css field is not enabled");};
    }

    public void verifyCssIsSelected() {
        //This function is for web element @FindBy(Google.css);
        try{
			getElement(Google.css).isSelected();
        	Settings.LOGGER.info("User verifies css element is selected");
        	GemTestReporter.addTestStep("Verify css element is selected","css is selected successfully", STATUS.PASS, takeSnapShot());
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Verify if css element is selected","Unable to select css element", STATUS.FAIL, takeSnapShot());
        	Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public String getAttributeFromCss(String attributeValue) {
        //This function is for web element @FindBy(Google.css);
        String text = new String();
        try{
			text = getAttributeName(Google.css, attributeValue);
        	if(!text.equals("")){GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "css field","Successfully fetched " + attributeValue + " value for css", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Successfully fetched " + attributeValue + " value for css");}
			 else {GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "css field","Unable to get " + attributeValue + " value for css as attribute does not exist", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Unable to get " + attributeValue + " value for css as attribute does not exist");};
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "css field","Unable to get " + attributeValue + " value for css as attribute does not exist", STATUS.FAIL, takeSnapShot());
        };
        return text;
    }

    public void verifyValueFromCss(String typeText) {
        //This function is for web element @FindBy(Google.css);
        String text = new String();
        try{
			text = getAttributeName(Google.css,"value");
        	if(typeText.equals(text)){
				GemTestReporter.addTestStep("Verify value of css matches " +typeText,"Value for css verified successfully", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Value for css verified successfully" );	}
			else{
				GemTestReporter.addTestStep("Verify value of css matches " +typeText,"Unable to verify value for css. Actual: "+text+" Expected: "+typeText, STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Unable to verify value for css. Actual: "+text+" Expected: "+typeText );	}
		}
		catch(Exception e){;
        	GemTestReporter.addTestStep("Verify value of css field","Unable to get attribute value", STATUS.FAIL, takeSnapShot());
        };
    }

    public void verifyCssIsDisplayed() {
        //The below function is for web element @FindBy(Google.css);
        try{
			getElement(Google.css).isDisplayed();
        	GemTestReporter.addTestStep("Verify css field is visible","css field is visible", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("css field is visible");}
		catch(Exception e){
			GemTestReporter.addTestStep("Verify css field is visible","css field is not visible", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("css field is not visible");};
    }

    public void verifyCssText(String typeText) {
        //The below function is for web element @FindBy(Google.css);
        try{
			if(getElementText(Google.css).equals(typeText)){;
        		GemTestReporter.addTestStep("Verify text of css field is equal to " + typeText,"Text of css field is equal to " + typeText, STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Text of css field is equal to " + typeText);}	
 else{
				GemTestReporter.addTestStep("Verify text of css field is equal to " + typeText,"Text of css field is not equal. Expected: "+typeText+" Actual: "+getElementText(Google.css), STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Text of css field is not equal. Expected: "+typeText+" Actual: "+getElementText(Google.css));}	
		}
		catch(Exception e){
			GemTestReporter.addTestStep("Check if text inside css is equal to " + typeText,"Text inside css is not equal", STATUS.FAIL, takeSnapShot());
        };
    }

    public void selectJavascript() {
        //The below function is for web element @FindBy(Google.javascript);
        try{
			click(Google.javascript);
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void verifyJavascriptIsEnabled() {
        //The below function is for web element @FindBy(Google.javascript);
        try{
			getElement(Google.javascript).isEnabled();
        	GemTestReporter.addTestStep("Verify javascript field is enabled","javascript field is enabled", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("javascript field is enabled");}
		catch(Exception e){
			GemTestReporter.addTestStep("Verify javascript field is enabled","J field is not enabled", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("javascript field is not enabled");};
    }

    public void verifyJavascriptIsSelected() {
        //This function is for web element @FindBy(Google.javascript);
        try{
			getElement(Google.javascript).isSelected();
        	Settings.LOGGER.info("User verifies javascript element is selected");
        	GemTestReporter.addTestStep("Verify javascript element is selected","javascript is selected successfully", STATUS.PASS, takeSnapShot());
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Verify if javascript element is selected","Unable to select javascript element", STATUS.FAIL, takeSnapShot());
        	Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public String getAttributeFromJavascript(String attributeValue) {
        //This function is for web element @FindBy(Google.javascript);
        String text = new String();
        try{
			text = getAttributeName(Google.javascript, attributeValue);
        	if(!text.equals("")){GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "javascript field","Successfully fetched " + attributeValue + " value for javascript", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Successfully fetched " + attributeValue + " value for javascript");}
			 else {GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "javascript field","Unable to get " + attributeValue + " value for javascript as attribute does not exist", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Unable to get " + attributeValue + " value for javascript as attribute does not exist");};
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "javascript field","Unable to get " + attributeValue + " value for javascript as attribute does not exist", STATUS.FAIL, takeSnapShot());
        };
        return text;
    }

    public void verifyValueFromJavascript(String typeText) {
        //This function is for web element @FindBy(Google.javascript);
        String text = new String();
        try{
			text = getAttributeName(Google.javascript,"value");
        	if(typeText.equals(text)){
				GemTestReporter.addTestStep("Verify value of javascript matches " +typeText,"Value for javascript verified successfully", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Value for javascript verified successfully" );	}
			else{
				GemTestReporter.addTestStep("Verify value of javascript matches " +typeText,"Unable to verify value for javascript. Actual: "+text+" Expected: "+typeText, STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Unable to verify value for javascript. Actual: "+text+" Expected: "+typeText );	}
		}
		catch(Exception e){;
        	GemTestReporter.addTestStep("Verify value of javascript field","Unable to get attribute value", STATUS.FAIL, takeSnapShot());
        };
    }

    public void verifyJavascriptIsDisplayed() {
        //The below function is for web element @FindBy(Google.javascript);
        try{
			getElement(Google.javascript).isDisplayed();
        	GemTestReporter.addTestStep("Verify javascript field is visible","javascript field is visible", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("javascript field is visible");}
		catch(Exception e){
			GemTestReporter.addTestStep("Verify javascript field is visible","javascript field is not visible", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("javascript field is not visible");};
    }

    public void verifyJavascriptText(String typeText) {
        //The below function is for web element @FindBy(Google.javascript);
        try{
			if(getElementText(Google.javascript).equals(typeText)){;
        		GemTestReporter.addTestStep("Verify text of javascript field is equal to " + typeText,"Text of javascript field is equal to " + typeText, STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Text of javascript field is equal to " + typeText);}	
 else{
				GemTestReporter.addTestStep("Verify text of javascript field is equal to " + typeText,"Text of javascript field is not equal. Expected: "+typeText+" Actual: "+getElementText(Google.javascript), STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Text of javascript field is not equal. Expected: "+typeText+" Actual: "+getElementText(Google.javascript));}	
		}
		catch(Exception e){
			GemTestReporter.addTestStep("Check if text inside javascript is equal to " + typeText,"Text inside javascript is not equal", STATUS.FAIL, takeSnapShot());
        };
    }

    public void selectBike() {
        //The below function is for web element @FindBy(Google.Bike);
        try{
			click(Google.Bike);
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void clickOnBike() {
        //The below function is for web element @FindBy(Google.Bike);
        try{
			click(Google.Bike);
        	Settings.LOGGER.info("User clicks on Bike successfully");}
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        	Settings.LOGGER.info("User is unable to click on Bike");};
    }

    public void elementIsClickableBike() {
        //The below function is for web element @FindBy(Google.Bike);
        try{
			WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds((long) 10));
        	WebElement element = wait.until(ExpectedConditions.elementToBeClickable(Google.Bike));
        	GemTestReporter.addTestStep("Check if Bike is clickable","Bike is clickable", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Bike is clickable");}
		catch(Exception e){
			GemTestReporter.addTestStep("Check if Bike is clickable","Bike is not clickable", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Bike is not clickable");};
    }

    public String getAttributeFromBike(String attributeValue) {
        //This function is for web element @FindBy(Google.Bike);
        String text = new String();
        try{
			text = getAttributeName(Google.Bike, attributeValue);
        	if(!text.equals("")){GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "Bike field","Successfully fetched " + attributeValue + " value for Bike", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Successfully fetched " + attributeValue + " value for Bike");}
			 else {GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "Bike field","Unable to get " + attributeValue + " value for Bike as attribute does not exist", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Unable to get " + attributeValue + " value for Bike as attribute does not exist");};
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "Bike field","Unable to get " + attributeValue + " value for Bike as attribute does not exist", STATUS.FAIL, takeSnapShot());
        };
        return text;
    }

    public void verifyElementSelectedForBike() {
        //This function is for web element @FindBy(Google.Bike);
        String text = new String();
        try{
			text = getAttributeName(Google.Bike, "checked");
        	if(text.length()>0){
				GemTestReporter.addTestStep("Verify Bike checkbox is selected","Checkbox Bike selected Successfully", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Checkbox Bike selected Successfully" );	};
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Verify Bike checkbox is selected","Unable to select Bike checkbox", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Unable to select Bike checkbox" );	};
    }

    public void verifyElementNotSelectedForBike() {
        //This function is for web element @FindBy(Google.Bike);
        String text = new String();
        try{
			text = getAttributeName(Google.Bike, "checked");
        	if(text==null){
				GemTestReporter.addTestStep("Verify Bike checkbox is not selected","Checkbox Bike is deselected Successfully", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Checkbox Bike is deselected Successfully" );	}
			 else {GemTestReporter.addTestStep("Verify Bike checkbox is not selected","Unable to deselect Bike checkbox", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Unable to deselect Bike checkbox" );	};
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Verify Bike checkbox is not selected","Unable to deselect Bike checkbox", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Unable to deselect Bike checkbox" );	};
    }

    public void verifyValueFromBike(String typeText) {
        //This function is for web element @FindBy(Google.Bike);
        String text = new String();
        try{
			text = getAttributeName(Google.Bike,"value");
        	if(typeText.equals(text)){
				GemTestReporter.addTestStep("Verify value of Bike matches " +typeText,"Value for Bike verified successfully", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Value for Bike verified successfully" );	}
			else{
				GemTestReporter.addTestStep("Verify value of Bike matches " +typeText,"Unable to verify value for Bike. Actual: "+text+" Expected: "+typeText, STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Unable to verify value for Bike. Actual: "+text+" Expected: "+typeText );	}
		}
		catch(Exception e){;
        	GemTestReporter.addTestStep("Verify value of Bike field","Unable to get attribute value", STATUS.FAIL, takeSnapShot());
        };
    }

    public void verifyBikeIsDisplayed() {
        //The below function is for web element @FindBy(Google.Bike);
        try{
			getElement(Google.Bike).isDisplayed();
        	GemTestReporter.addTestStep("Verify Bike field is visible","Bike field is visible", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Bike field is visible");}
		catch(Exception e){
			GemTestReporter.addTestStep("Verify Bike field is visible","Bike field is not visible", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Bike field is not visible");};
    }

    public void verifyBikeText(String typeText) {
        //The below function is for web element @FindBy(Google.Bike);
        try{
			if(getElementText(Google.Bike).equals(typeText)){;
        		GemTestReporter.addTestStep("Verify text of Bike field is equal to " + typeText,"Text of Bike field is equal to " + typeText, STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Text of Bike field is equal to " + typeText);}	
 else{
				GemTestReporter.addTestStep("Verify text of Bike field is equal to " + typeText,"Text of Bike field is not equal. Expected: "+typeText+" Actual: "+getElementText(Google.Bike), STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Text of Bike field is not equal. Expected: "+typeText+" Actual: "+getElementText(Google.Bike));}	
		}
		catch(Exception e){
			GemTestReporter.addTestStep("Check if text inside Bike is equal to " + typeText,"Text inside Bike is not equal", STATUS.FAIL, takeSnapShot());
        };
    }

    public void selectBoat() {
        //The below function is for web element @FindBy(Google.Boat);
        try{
			click(Google.Boat);
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void clickOnBoat() {
        //The below function is for web element @FindBy(Google.Boat);
        try{
			click(Google.Boat);
        	Settings.LOGGER.info("User clicks on Boat successfully");}
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        	Settings.LOGGER.info("User is unable to click on Boat");};
    }

    public void elementIsClickableBoat() {
        //The below function is for web element @FindBy(Google.Boat);
        try{
			WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds((long) 10));
        	WebElement element = wait.until(ExpectedConditions.elementToBeClickable(Google.Boat));
        	GemTestReporter.addTestStep("Check if Boat is clickable","Boat is clickable", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Boat is clickable");}
		catch(Exception e){
			GemTestReporter.addTestStep("Check if Boat is clickable","Boat is not clickable", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Boat is not clickable");};
    }

    public String getAttributeFromBoat(String attributeValue) {
        //This function is for web element @FindBy(Google.Boat);
        String text = new String();
        try{
			text = getAttributeName(Google.Boat, attributeValue);
        	if(!text.equals("")){GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "Boat field","Successfully fetched " + attributeValue + " value for Boat", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Successfully fetched " + attributeValue + " value for Boat");}
			 else {GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "Boat field","Unable to get " + attributeValue + " value for Boat as attribute does not exist", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Unable to get " + attributeValue + " value for Boat as attribute does not exist");};
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "Boat field","Unable to get " + attributeValue + " value for Boat as attribute does not exist", STATUS.FAIL, takeSnapShot());
        };
        return text;
    }

    public void verifyElementSelectedForBoat() {
        //This function is for web element @FindBy(Google.Boat);
        String text = new String();
        try{
			text = getAttributeName(Google.Boat, "checked");
        	if(text.length()>0){
				GemTestReporter.addTestStep("Verify Boat checkbox is selected","Checkbox Boat selected Successfully", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Checkbox Boat selected Successfully" );	};
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Verify Boat checkbox is selected","Unable to select Boat checkbox", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Unable to select Boat checkbox" );	};
    }

    public void verifyElementNotSelectedForBoat() {
        //This function is for web element @FindBy(Google.Boat);
        String text = new String();
        try{
			text = getAttributeName(Google.Boat, "checked");
        	if(text==null){
				GemTestReporter.addTestStep("Verify Boat checkbox is not selected","Checkbox Boat is deselected Successfully", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Checkbox Boat is deselected Successfully" );	}
			 else {GemTestReporter.addTestStep("Verify Boat checkbox is not selected","Unable to deselect Boat checkbox", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Unable to deselect Boat checkbox" );	};
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Verify Boat checkbox is not selected","Unable to deselect Boat checkbox", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Unable to deselect Boat checkbox" );	};
    }

    public void verifyValueFromBoat(String typeText) {
        //This function is for web element @FindBy(Google.Boat);
        String text = new String();
        try{
			text = getAttributeName(Google.Boat,"value");
        	if(typeText.equals(text)){
				GemTestReporter.addTestStep("Verify value of Boat matches " +typeText,"Value for Boat verified successfully", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Value for Boat verified successfully" );	}
			else{
				GemTestReporter.addTestStep("Verify value of Boat matches " +typeText,"Unable to verify value for Boat. Actual: "+text+" Expected: "+typeText, STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Unable to verify value for Boat. Actual: "+text+" Expected: "+typeText );	}
		}
		catch(Exception e){;
        	GemTestReporter.addTestStep("Verify value of Boat field","Unable to get attribute value", STATUS.FAIL, takeSnapShot());
        };
    }

    public void verifyBoatIsDisplayed() {
        //The below function is for web element @FindBy(Google.Boat);
        try{
			getElement(Google.Boat).isDisplayed();
        	GemTestReporter.addTestStep("Verify Boat field is visible","Boat field is visible", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Boat field is visible");}
		catch(Exception e){
			GemTestReporter.addTestStep("Verify Boat field is visible","Boat field is not visible", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Boat field is not visible");};
    }

    public void verifyBoatText(String typeText) {
        //The below function is for web element @FindBy(Google.Boat);
        try{
			if(getElementText(Google.Boat).equals(typeText)){;
        		GemTestReporter.addTestStep("Verify text of Boat field is equal to " + typeText,"Text of Boat field is equal to " + typeText, STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Text of Boat field is equal to " + typeText);}	
 else{
				GemTestReporter.addTestStep("Verify text of Boat field is equal to " + typeText,"Text of Boat field is not equal. Expected: "+typeText+" Actual: "+getElementText(Google.Boat), STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Text of Boat field is not equal. Expected: "+typeText+" Actual: "+getElementText(Google.Boat));}	
		}
		catch(Exception e){
			GemTestReporter.addTestStep("Check if text inside Boat is equal to " + typeText,"Text inside Boat is not equal", STATUS.FAIL, takeSnapShot());
        };
    }

    public void selectCar(String typeText) {
        //The below function is for web element @FindBy(Google.car);
        try{
			dropDown(Google.car,typeText);
        	Settings.LOGGER.info("User is able to select " + typeText +" visible text in the dropdown");
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void verifyCarIsEnabled() {
        //The below function is for web element @FindBy(Google.car);
        try{
			getElement(Google.car).isEnabled();
        	GemTestReporter.addTestStep("Verify car field is enabled","car field is enabled", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("car field is enabled");}
		catch(Exception e){
			GemTestReporter.addTestStep("Verify car field is enabled","C field is not enabled", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("car field is not enabled");};
    }

    public void elementIsClickableCar() {
        //The below function is for web element @FindBy(Google.car);
        try{
			WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds((long) 10));
        	WebElement element = wait.until(ExpectedConditions.elementToBeClickable(Google.car));
        	GemTestReporter.addTestStep("Check if car is clickable","car is clickable", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("car is clickable");}
		catch(Exception e){
			GemTestReporter.addTestStep("Check if car is clickable","car is not clickable", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("car is not clickable");};
    }

    public String getTextFromCar() {
        //The below function is for web element @FindBy(Google.car);
        String text = new String();
        try{
			text = getElementText(Google.car);
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        } 
		return text;
    }

    public String getAttributeFromCar(String attributeValue) {
        //This function is for web element @FindBy(Google.car);
        String text = new String();
        try{
			text = getAttributeName(Google.car, attributeValue);
        	if(!text.equals("")){GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "car field","Successfully fetched " + attributeValue + " value for car", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Successfully fetched " + attributeValue + " value for car");}
			 else {GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "car field","Unable to get " + attributeValue + " value for car as attribute does not exist", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Unable to get " + attributeValue + " value for car as attribute does not exist");};
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "car field","Unable to get " + attributeValue + " value for car as attribute does not exist", STATUS.FAIL, takeSnapShot());
        };
        return text;
    }

    public void verifyValueFromCar(String typeText) {
        //This function is for web element @FindBy(Google.car);
        String text = new String();
        try{
			text = getAttributeName(Google.car,"value");
        	if(typeText.equals(text)){
				GemTestReporter.addTestStep("Verify value of car matches " +typeText,"Value for car verified successfully", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Value for car verified successfully" );	}
			else{
				GemTestReporter.addTestStep("Verify value of car matches " +typeText,"Unable to verify value for car. Actual: "+text+" Expected: "+typeText, STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Unable to verify value for car. Actual: "+text+" Expected: "+typeText );	}
		}
		catch(Exception e){;
        	GemTestReporter.addTestStep("Verify value of car field","Unable to get attribute value", STATUS.FAIL, takeSnapShot());
        };
    }

    public void verifyCarIsDisplayed() {
        //The below function is for web element @FindBy(Google.car);
        try{
			getElement(Google.car).isDisplayed();
        	GemTestReporter.addTestStep("Verify car field is visible","car field is visible", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("car field is visible");}
		catch(Exception e){
			GemTestReporter.addTestStep("Verify car field is visible","car field is not visible", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("car field is not visible");};
    }

    public void verifyCarText(String typeText) {
        //The below function is for web element @FindBy(Google.car);
        try{
			if(getElementText(Google.car).equals(typeText)){;
        		GemTestReporter.addTestStep("Verify text of car field is equal to " + typeText,"Text of car field is equal to " + typeText, STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Text of car field is equal to " + typeText);}	
 else{
				GemTestReporter.addTestStep("Verify text of car field is equal to " + typeText,"Text of car field is not equal. Expected: "+typeText+" Actual: "+getElementText(Google.car), STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Text of car field is not equal. Expected: "+typeText+" Actual: "+getElementText(Google.car));}	
		}
		catch(Exception e){
			GemTestReporter.addTestStep("Check if text inside car is equal to " + typeText,"Text inside car is not equal", STATUS.FAIL, takeSnapShot());
        };
    }

    public void clickOnSubmit() {
        //The below function is for web element @FindBy(Google.Submit);
        try{
			click(Google.Submit);
        	Settings.LOGGER.info("User clicks on Submit successfully");}
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        	Settings.LOGGER.info("User is unable to click on Submit");};
    }

    public void elementIsClickableSubmit() {
        //The below function is for web element @FindBy(Google.Submit);
        try{
			WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds((long) 10));
        	WebElement element = wait.until(ExpectedConditions.elementToBeClickable(Google.Submit));
        	GemTestReporter.addTestStep("Check if Submit is clickable","Submit is clickable", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Submit is clickable");}
		catch(Exception e){
			GemTestReporter.addTestStep("Check if Submit is clickable","Submit is not clickable", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Submit is not clickable");};
    }

    public String getAttributeFromSubmit(String attributeValue) {
        //This function is for web element @FindBy(Google.Submit);
        String text = new String();
        try{
			text = getAttributeName(Google.Submit, attributeValue);
        	if(!text.equals("")){GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "Submit field","Successfully fetched " + attributeValue + " value for Submit", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Successfully fetched " + attributeValue + " value for Submit");}
			 else {GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "Submit field","Unable to get " + attributeValue + " value for Submit as attribute does not exist", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Unable to get " + attributeValue + " value for Submit as attribute does not exist");};
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "Submit field","Unable to get " + attributeValue + " value for Submit as attribute does not exist", STATUS.FAIL, takeSnapShot());
        };
        return text;
    }

    public void verifyValueFromSubmit(String typeText) {
        //This function is for web element @FindBy(Google.Submit);
        String text = new String();
        try{
			text = getAttributeName(Google.Submit,"value");
        	if(typeText.equals(text)){
				GemTestReporter.addTestStep("Verify value of Submit matches " +typeText,"Value for Submit verified successfully", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Value for Submit verified successfully" );	}
			else{
				GemTestReporter.addTestStep("Verify value of Submit matches " +typeText,"Unable to verify value for Submit. Actual: "+text+" Expected: "+typeText, STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Unable to verify value for Submit. Actual: "+text+" Expected: "+typeText );	}
		}
		catch(Exception e){;
        	GemTestReporter.addTestStep("Verify value of Submit field","Unable to get attribute value", STATUS.FAIL, takeSnapShot());
        };
    }

    public void verifySubmitIsDisplayed() {
        //The below function is for web element @FindBy(Google.Submit);
        try{
			getElement(Google.Submit).isDisplayed();
        	GemTestReporter.addTestStep("Verify Submit field is visible","Submit field is visible", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Submit field is visible");}
		catch(Exception e){
			GemTestReporter.addTestStep("Verify Submit field is visible","Submit field is not visible", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Submit field is not visible");};
    }

    public void verifySubmitText(String typeText) {
        //The below function is for web element @FindBy(Google.Submit);
        try{
			if(getElementText(Google.Submit).equals(typeText)){;
        		GemTestReporter.addTestStep("Verify text of Submit field is equal to " + typeText,"Text of Submit field is equal to " + typeText, STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Text of Submit field is equal to " + typeText);}	
 else{
				GemTestReporter.addTestStep("Verify text of Submit field is equal to " + typeText,"Text of Submit field is not equal. Expected: "+typeText+" Actual: "+getElementText(Google.Submit), STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Text of Submit field is not equal. Expected: "+typeText+" Actual: "+getElementText(Google.Submit));}	
		}
		catch(Exception e){
			GemTestReporter.addTestStep("Check if text inside Submit is equal to " + typeText,"Text inside Submit is not equal", STATUS.FAIL, takeSnapShot());
        };
    }

    public void clickOnHtmlAndNavigateBack() {
        //This function is for web element @FindBy(Google.html);
        try{
			click(Google.html);
        	navigateBack();
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void clickOnHtml() {
        //The below function is for web element @FindBy(Google.html);
        try{
			click(Google.html);
        	Settings.LOGGER.info("User clicks on html successfully");}
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        	Settings.LOGGER.info("User is unable to click on html");};
    }

    public void elementIsClickableHtml() {
        //The below function is for web element @FindBy(Google.html);
        try{
			WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds((long) 10));
        	WebElement element = wait.until(ExpectedConditions.elementToBeClickable(Google.html));
        	GemTestReporter.addTestStep("Check if html is clickable","html is clickable", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("html is clickable");}
		catch(Exception e){
			GemTestReporter.addTestStep("Check if html is clickable","html is not clickable", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("html is not clickable");};
    }

    public String getAttributeFromHtml(String attributeValue) {
        //This function is for web element @FindBy(Google.html);
        String text = new String();
        try{
			text = getAttributeName(Google.html, attributeValue);
        	if(!text.equals("")){GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "html field","Successfully fetched " + attributeValue + " value for html", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Successfully fetched " + attributeValue + " value for html");}
			 else {GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "html field","Unable to get " + attributeValue + " value for html as attribute does not exist", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Unable to get " + attributeValue + " value for html as attribute does not exist");};
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "html field","Unable to get " + attributeValue + " value for html as attribute does not exist", STATUS.FAIL, takeSnapShot());
        };
        return text;
    }

    public void verifyValueFromHtml(String typeText) {
        //This function is for web element @FindBy(Google.html);
        String text = new String();
        try{
			text = getAttributeName(Google.html,"value");
        	if(typeText.equals(text)){
				GemTestReporter.addTestStep("Verify value of html matches " +typeText,"Value for html verified successfully", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Value for html verified successfully" );	}
			else{
				GemTestReporter.addTestStep("Verify value of html matches " +typeText,"Unable to verify value for html. Actual: "+text+" Expected: "+typeText, STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Unable to verify value for html. Actual: "+text+" Expected: "+typeText );	}
		}
		catch(Exception e){;
        	GemTestReporter.addTestStep("Verify value of html field","Unable to get attribute value", STATUS.FAIL, takeSnapShot());
        };
    }

    public void verifyHtmlIsDisplayed() {
        //The below function is for web element @FindBy(Google.html);
        try{
			getElement(Google.html).isDisplayed();
        	GemTestReporter.addTestStep("Verify html field is visible","html field is visible", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("html field is visible");}
		catch(Exception e){
			GemTestReporter.addTestStep("Verify html field is visible","html field is not visible", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("html field is not visible");};
    }

    public void verifyHtmlText(String typeText) {
        //The below function is for web element @FindBy(Google.html);
        try{
			if(getElementText(Google.html).equals(typeText)){;
        		GemTestReporter.addTestStep("Verify text of html field is equal to " + typeText,"Text of html field is equal to " + typeText, STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Text of html field is equal to " + typeText);}	
 else{
				GemTestReporter.addTestStep("Verify text of html field is equal to " + typeText,"Text of html field is not equal. Expected: "+typeText+" Actual: "+getElementText(Google.html), STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Text of html field is not equal. Expected: "+typeText+" Actual: "+getElementText(Google.html));}	
		}
		catch(Exception e){
			GemTestReporter.addTestStep("Check if text inside html is equal to " + typeText,"Text inside html is not equal", STATUS.FAIL, takeSnapShot());
        };
    }
}
