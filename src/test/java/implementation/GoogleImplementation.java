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
import static org.junit.Assert.assertTrue;
import locators.Google;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebDriver;

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
        	GemTestReporter.addTestStep("Check if firstName is enabled","firstName is enabled", STATUS.PASS, takeSnapShot());
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Check if firstName is enabled","firstName is not enabled", STATUS.FAIL, takeSnapShot());
        };
    }

    public void clearFirstName() {
        //The below function is for web element @FindBy(Google.firstName);
        try{
			clearText(Google.firstName);
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Check if firstName is cleared","firstName is unable to be cleared", STATUS.FAIL, takeSnapShot());
        };
    }

    public String getAttributeFromFirstName(String attributeValue) {
        //This function is for web element @FindBy(Google.firstName);
        String text = new String();
        try{
			text = getAttributeName(Google.firstName, attributeValue);
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "firstName field","Unable to get attribute value", STATUS.FAIL, takeSnapShot());
        };
        return text;
    }

    public String getValueFromFirstName(String typeText) {
        //This function is for web element @FindBy(Google.firstName);
        String text = new String();
        try{
			text = getAttributeName(Google.firstName,"value");
        	if(typeText.equals(text)){
				GemTestReporter.addTestStep("Verify if value of Value attribute matches" +typeText,"Validation Successfull", STATUS.PASS, takeSnapShot());
        	}
			else{
				GemTestReporter.addTestStep("Verify if value of Value attribute matches" +typeText,"Validation Failed", STATUS.FAIL, takeSnapShot());
        	}
		}
		catch(Exception e){;
        	GemTestReporter.addTestStep("Get value of Value attribute for firstName field","Unable to get attribute value", STATUS.FAIL, takeSnapShot());
        };
        return text;
    }

    public void verifyFirstNameIsDisplayed() {
        //The below function is for web element @FindBy(Google.firstName);
        try{
			getElement(Google.firstName).isDisplayed();
        	GemTestReporter.addTestStep("Check if firstName is visible","firstName is visible", STATUS.PASS, takeSnapShot());
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Check if firstName is visible","firstName is not visible", STATUS.FAIL, takeSnapShot());
        };
    }

    public void verifyFirstNameText(String typeText) {
        //The below function is for web element @FindBy(Google.firstName);
        try{
			if(getElementText(Google.firstName).equals(typeText)){;
        		GemTestReporter.addTestStep("Check if text inside firstName is equal to " + typeText,"Text inside firstName is equal to " + typeText, STATUS.PASS, takeSnapShot());
        	} else{
				GemTestReporter.addTestStep("Check if text inside firstName is equal to " + typeText,"Text inside firstName is not equal", STATUS.FAIL, takeSnapShot());
        	}
		}
		catch(Exception e){
			GemTestReporter.addTestStep("Check if text inside firstName is equal to " + typeText,"Text inside firstName is not equal", STATUS.FAIL, takeSnapShot());
        };
    }

    public void clickOnPassword() {
        //The below function is for web element @FindBy(Google.password);
        try{
			click(Google.password);
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void elementIsClickablePassword() {
        //The below function is for web element @FindBy(Google.password);
        try{
			WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds((long) 10));
        	WebElement element = wait.until(ExpectedConditions.elementToBeClickable(Google.password));
        	GemTestReporter.addTestStep("Check if password is clickable","password is clickable", STATUS.PASS, takeSnapShot());
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Check if password is clickable","password is not clickable", STATUS.FAIL, takeSnapShot());
        };
    }

    public String getAttributeFromPassword(String attributeValue) {
        //This function is for web element @FindBy(Google.password);
        String text = new String();
        try{
			text = getAttributeName(Google.password, attributeValue);
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "password field","Unable to get attribute value", STATUS.FAIL, takeSnapShot());
        };
        return text;
    }

    public String getValueFromPassword(String typeText) {
        //This function is for web element @FindBy(Google.password);
        String text = new String();
        try{
			text = getAttributeName(Google.password,"value");
        	if(typeText.equals(text)){
				GemTestReporter.addTestStep("Verify if value of Value attribute matches" +typeText,"Validation Successfull", STATUS.PASS, takeSnapShot());
        	}
			else{
				GemTestReporter.addTestStep("Verify if value of Value attribute matches" +typeText,"Validation Failed", STATUS.FAIL, takeSnapShot());
        	}
		}
		catch(Exception e){;
        	GemTestReporter.addTestStep("Get value of Value attribute for password field","Unable to get attribute value", STATUS.FAIL, takeSnapShot());
        };
        return text;
    }

    public void verifyPasswordIsDisplayed() {
        //The below function is for web element @FindBy(Google.password);
        try{
			getElement(Google.password).isDisplayed();
        	GemTestReporter.addTestStep("Check if password is visible","password is visible", STATUS.PASS, takeSnapShot());
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Check if password is visible","password is not visible", STATUS.FAIL, takeSnapShot());
        };
    }

    public void verifyPasswordText(String typeText) {
        //The below function is for web element @FindBy(Google.password);
        try{
			if(getElementText(Google.password).equals(typeText)){;
        		GemTestReporter.addTestStep("Check if text inside password is equal to " + typeText,"Text inside password is equal to " + typeText, STATUS.PASS, takeSnapShot());
        	} else{
				GemTestReporter.addTestStep("Check if text inside password is equal to " + typeText,"Text inside password is not equal", STATUS.FAIL, takeSnapShot());
        	}
		}
		catch(Exception e){
			GemTestReporter.addTestStep("Check if text inside password is equal to " + typeText,"Text inside password is not equal", STATUS.FAIL, takeSnapShot());
        };
    }

    public void doubleClickOnClickItem() {
        //The below function is for web element @FindBy(Google.clickItem);
        try{
			doubleClick(Google.clickItem);
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void clickOnClickItem() {
        //The below function is for web element @FindBy(Google.clickItem);
        try{
			click(Google.clickItem);
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void elementIsClickableClickItem() {
        //The below function is for web element @FindBy(Google.clickItem);
        try{
			WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds((long) 10));
        	WebElement element = wait.until(ExpectedConditions.elementToBeClickable(Google.clickItem));
        	GemTestReporter.addTestStep("Check if clickItem is clickable","clickItem is clickable", STATUS.PASS, takeSnapShot());
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Check if clickItem is clickable","clickItem is not clickable", STATUS.FAIL, takeSnapShot());
        };
    }

    public String getTextFromClickItem() {
        //The below function is for web element @FindBy(Google.clickItem);
        String text = new String();
        try{
			text = getElementText(Google.clickItem);
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        } 
		return text;
    }

    public void scrollClickClickItem() {
        //The below function is for web element @FindBy(Google.clickItem);
        try{
			scrollIntoView(Google.clickItem); 
			click(Google.clickItem);
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public String getAttributeFromClickItem(String attributeValue) {
        //This function is for web element @FindBy(Google.clickItem);
        String text = new String();
        try{
			text = getAttributeName(Google.clickItem, attributeValue);
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "clickItem field","Unable to get attribute value", STATUS.FAIL, takeSnapShot());
        };
        return text;
    }

    public void verifyClickItemIsDisplayed() {
        //The below function is for web element @FindBy(Google.clickItem);
        try{
			getElement(Google.clickItem).isDisplayed();
        	GemTestReporter.addTestStep("Check if clickItem is visible","clickItem is visible", STATUS.PASS, takeSnapShot());
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Check if clickItem is visible","clickItem is not visible", STATUS.FAIL, takeSnapShot());
        };
    }

    public void verifyClickItemText(String typeText) {
        //The below function is for web element @FindBy(Google.clickItem);
        try{
			if(getElementText(Google.clickItem).equals(typeText)){;
        		GemTestReporter.addTestStep("Check if text inside clickItem is equal to " + typeText,"Text inside clickItem is equal to " + typeText, STATUS.PASS, takeSnapShot());
        	} else{
				GemTestReporter.addTestStep("Check if text inside clickItem is equal to " + typeText,"Text inside clickItem is not equal", STATUS.FAIL, takeSnapShot());
        	}
		}
		catch(Exception e){
			GemTestReporter.addTestStep("Check if text inside clickItem is equal to " + typeText,"Text inside clickItem is not equal", STATUS.FAIL, takeSnapShot());
        };
    }

    public void selectDropdownItem(String typeText) {
        //The below function is for web element @FindBy(Google.dropdownItem);
        try{
			dropDown(Google.dropdownItem,typeText);
        	Settings.LOGGER.info("User is able to select " + typeText +" visible text in the dropdown");
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public String getAttributeFromDropdownItem(String attributeValue) {
        //This function is for web element @FindBy(Google.dropdownItem);
        String text = new String();
        try{
			text = getAttributeName(Google.dropdownItem, attributeValue);
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "dropdownItem field","Unable to get attribute value", STATUS.FAIL, takeSnapShot());
        };
        return text;
    }

    public void elementIsClickableDropdownItem() {
        //The below function is for web element @FindBy(Google.dropdownItem);
        try{
			WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds((long) 10));
        	WebElement element = wait.until(ExpectedConditions.elementToBeClickable(Google.dropdownItem));
        	GemTestReporter.addTestStep("Check if dropdownItem is clickable","dropdownItem is clickable", STATUS.PASS, takeSnapShot());
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Check if dropdownItem is clickable","dropdownItem is not clickable", STATUS.FAIL, takeSnapShot());
        };
    }

    public String getTextFromDropdownItem() {
        //The below function is for web element @FindBy(Google.dropdownItem);
        String text = new String();
        try{
			text = getElementText(Google.dropdownItem);
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        } 
		return text;
    }

    public String getValueFromDropdownItem(String typeText) {
        //This function is for web element @FindBy(Google.dropdownItem);
        String text = new String();
        try{
			text = getAttributeName(Google.dropdownItem,"value");
        	if(typeText.equals(text)){
				GemTestReporter.addTestStep("Verify if value of Value attribute matches" +typeText,"Validation Successfull", STATUS.PASS, takeSnapShot());
        	}
			else{
				GemTestReporter.addTestStep("Verify if value of Value attribute matches" +typeText,"Validation Failed", STATUS.FAIL, takeSnapShot());
        	}
		}
		catch(Exception e){;
        	GemTestReporter.addTestStep("Get value of Value attribute for dropdownItem field","Unable to get attribute value", STATUS.FAIL, takeSnapShot());
        };
        return text;
    }

    public void verifyDropdownItemIsDisplayed() {
        //The below function is for web element @FindBy(Google.dropdownItem);
        try{
			getElement(Google.dropdownItem).isDisplayed();
        	GemTestReporter.addTestStep("Check if dropdownItem is visible","dropdownItem is visible", STATUS.PASS, takeSnapShot());
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Check if dropdownItem is visible","dropdownItem is not visible", STATUS.FAIL, takeSnapShot());
        };
    }

    public void verifyDropdownItemText(String typeText) {
        //The below function is for web element @FindBy(Google.dropdownItem);
        try{
			if(getElementText(Google.dropdownItem).equals(typeText)){;
        		GemTestReporter.addTestStep("Check if text inside dropdownItem is equal to " + typeText,"Text inside dropdownItem is equal to " + typeText, STATUS.PASS, takeSnapShot());
        	} else{
				GemTestReporter.addTestStep("Check if text inside dropdownItem is equal to " + typeText,"Text inside dropdownItem is not equal", STATUS.FAIL, takeSnapShot());
        	}
		}
		catch(Exception e){
			GemTestReporter.addTestStep("Check if text inside dropdownItem is equal to " + typeText,"Text inside dropdownItem is not equal", STATUS.FAIL, takeSnapShot());
        };
    }

    public void clickOnImageItem() {
        //The below function is for web element @FindBy(Google.imageItem);
        try{
			click(Google.imageItem);
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void elementIsClickableImageItem() {
        //The below function is for web element @FindBy(Google.imageItem);
        try{
			WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds((long) 10));
        	WebElement element = wait.until(ExpectedConditions.elementToBeClickable(Google.imageItem));
        	GemTestReporter.addTestStep("Check if imageItem is clickable","imageItem is clickable", STATUS.PASS, takeSnapShot());
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Check if imageItem is clickable","imageItem is not clickable", STATUS.FAIL, takeSnapShot());
        };
    }

    public void verifyImageItemIsEnabled() {
        //The below function is for web element @FindBy(Google.imageItem);
        try{
			getElement(Google.imageItem).isEnabled();
        	GemTestReporter.addTestStep("Check if imageItem is enabled","imageItem is enabled", STATUS.PASS, takeSnapShot());
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Check if imageItem is enabled","imageItem is not enabled", STATUS.FAIL, takeSnapShot());
        };
    }

    public void uploadFileToImageItem(String fileName) {
        //The below function is for web element @FindBy(Google.imageItem);
        try{
			typeText(Google.imageItem, "C:\\Users\\ayush.garg\\Downloads\\MethodGenerator\\MethodGenerator-main\\src\\main\\resources\\data\\ " + fileName);
        	Settings.LOGGER.info("User uploads the "+fileName+" file");
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Upload the" + fileName + "file","Unable to upload" + fileName + "field", STATUS.FAIL, takeSnapShot());
        	Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void verifyImageItemIsSelected() {
        //This function is for web element @FindBy(Google.imageItem);
        try{
			getElement(Google.imageItem).isSelected();
        	Settings.LOGGER.info("User verifies the given imageItem element is selected");
        	GemTestReporter.addTestStep("Verify if the given imageItem is selected","The given imageItem is selected", STATUS.PASS, takeSnapShot());
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Verify if the given imageItem is selected","The given imageItem is not selected", STATUS.FAIL, takeSnapShot());
        	Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public String getAttributeFromImageItem(String attributeValue) {
        //This function is for web element @FindBy(Google.imageItem);
        String text = new String();
        try{
			text = getAttributeName(Google.imageItem, attributeValue);
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "imageItem field","Unable to get attribute value", STATUS.FAIL, takeSnapShot());
        };
        return text;
    }

    public String getValueFromImageItem(String typeText) {
        //This function is for web element @FindBy(Google.imageItem);
        String text = new String();
        try{
			text = getAttributeName(Google.imageItem,"value");
        	if(typeText.equals(text)){
				GemTestReporter.addTestStep("Verify if value of Value attribute matches" +typeText,"Validation Successfull", STATUS.PASS, takeSnapShot());
        	}
			else{
				GemTestReporter.addTestStep("Verify if value of Value attribute matches" +typeText,"Validation Failed", STATUS.FAIL, takeSnapShot());
        	}
		}
		catch(Exception e){;
        	GemTestReporter.addTestStep("Get value of Value attribute for imageItem field","Unable to get attribute value", STATUS.FAIL, takeSnapShot());
        };
        return text;
    }

    public void verifyImageItemIsDisplayed() {
        //The below function is for web element @FindBy(Google.imageItem);
        try{
			getElement(Google.imageItem).isDisplayed();
        	GemTestReporter.addTestStep("Check if imageItem is visible","imageItem is visible", STATUS.PASS, takeSnapShot());
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Check if imageItem is visible","imageItem is not visible", STATUS.FAIL, takeSnapShot());
        };
    }

    public void verifyImageItemText(String typeText) {
        //The below function is for web element @FindBy(Google.imageItem);
        try{
			if(getElementText(Google.imageItem).equals(typeText)){;
        		GemTestReporter.addTestStep("Check if text inside imageItem is equal to " + typeText,"Text inside imageItem is equal to " + typeText, STATUS.PASS, takeSnapShot());
        	} else{
				GemTestReporter.addTestStep("Check if text inside imageItem is equal to " + typeText,"Text inside imageItem is not equal", STATUS.FAIL, takeSnapShot());
        	}
		}
		catch(Exception e){
			GemTestReporter.addTestStep("Check if text inside imageItem is equal to " + typeText,"Text inside imageItem is not equal", STATUS.FAIL, takeSnapShot());
        };
    }

    public void clickOnAItem() {
        //The below function is for web element @FindBy(Google.aItem);
        try{
			click(Google.aItem);
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void elementIsClickableAItem() {
        //The below function is for web element @FindBy(Google.aItem);
        try{
			WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds((long) 10));
        	WebElement element = wait.until(ExpectedConditions.elementToBeClickable(Google.aItem));
        	GemTestReporter.addTestStep("Check if aItem is clickable","aItem is clickable", STATUS.PASS, takeSnapShot());
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Check if aItem is clickable","aItem is not clickable", STATUS.FAIL, takeSnapShot());
        };
    }

    public void clickOnAItemAndNavigateBack() {
        //This function is for web element @FindBy(Google.aItem);
        try{
			click(Google.aItem);
        	navigateBack();
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void verifyAItemIsDisplayed() {
        //The below function is for web element @FindBy(Google.aItem);
        try{
			getElement(Google.aItem).isDisplayed();
        	GemTestReporter.addTestStep("Check if aItem is visible","aItem is visible", STATUS.PASS, takeSnapShot());
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Check if aItem is visible","aItem is not visible", STATUS.FAIL, takeSnapShot());
        };
    }

    public void verifyAItemText(String typeText) {
        //The below function is for web element @FindBy(Google.aItem);
        try{
			if(getElementText(Google.aItem).equals(typeText)){;
        		GemTestReporter.addTestStep("Check if text inside aItem is equal to " + typeText,"Text inside aItem is equal to " + typeText, STATUS.PASS, takeSnapShot());
        	} else{
				GemTestReporter.addTestStep("Check if text inside aItem is equal to " + typeText,"Text inside aItem is not equal", STATUS.FAIL, takeSnapShot());
        	}
		}
		catch(Exception e){
			GemTestReporter.addTestStep("Check if text inside aItem is equal to " + typeText,"Text inside aItem is not equal", STATUS.FAIL, takeSnapShot());
        };
    }

    public void typeTextIntoSerachbar(String typeText) {
        //The below function is for web element @FindBy(Google.serachbar);
        try{
			typeText(Google.serachbar,typeText);
        	Settings.LOGGER.info("User enters "+typeText+" as value");
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Enter the text in serachbar field","Unable to Enter Text in serachbar field", STATUS.FAIL, takeSnapShot());
        	Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public String getTextFromSerachbar() {
        //The below function is for web element @FindBy(Google.serachbar);
        String text = new String();
        try{
			text = getElementText(Google.serachbar);
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        } 
		return text;
    }

    public void verifySerachbarIsEnabled() {
        //The below function is for web element @FindBy(Google.serachbar);
        try{
			getElement(Google.serachbar).isEnabled();
        	GemTestReporter.addTestStep("Check if serachbar is enabled","serachbar is enabled", STATUS.PASS, takeSnapShot());
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Check if serachbar is enabled","serachbar is not enabled", STATUS.FAIL, takeSnapShot());
        };
    }

    public void clearSerachbar() {
        //The below function is for web element @FindBy(Google.serachbar);
        try{
			clearText(Google.serachbar);
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Check if serachbar is cleared","serachbar is unable to be cleared", STATUS.FAIL, takeSnapShot());
        };
    }

    public String getAttributeFromSerachbar(String attributeValue) {
        //This function is for web element @FindBy(Google.serachbar);
        String text = new String();
        try{
			text = getAttributeName(Google.serachbar, attributeValue);
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "serachbar field","Unable to get attribute value", STATUS.FAIL, takeSnapShot());
        };
        return text;
    }

    public String getValueFromSerachbar(String typeText) {
        //This function is for web element @FindBy(Google.serachbar);
        String text = new String();
        try{
			text = getAttributeName(Google.serachbar,"value");
        	if(typeText.equals(text)){
				GemTestReporter.addTestStep("Verify if value of Value attribute matches" +typeText,"Validation Successfull", STATUS.PASS, takeSnapShot());
        	}
			else{
				GemTestReporter.addTestStep("Verify if value of Value attribute matches" +typeText,"Validation Failed", STATUS.FAIL, takeSnapShot());
        	}
		}
		catch(Exception e){;
        	GemTestReporter.addTestStep("Get value of Value attribute for serachbar field","Unable to get attribute value", STATUS.FAIL, takeSnapShot());
        };
        return text;
    }

    public void verifySerachbarIsDisplayed() {
        //The below function is for web element @FindBy(Google.serachbar);
        try{
			getElement(Google.serachbar).isDisplayed();
        	GemTestReporter.addTestStep("Check if serachbar is visible","serachbar is visible", STATUS.PASS, takeSnapShot());
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Check if serachbar is visible","serachbar is not visible", STATUS.FAIL, takeSnapShot());
        };
    }

    public void verifySerachbarText(String typeText) {
        //The below function is for web element @FindBy(Google.serachbar);
        try{
			if(getElementText(Google.serachbar).equals(typeText)){;
        		GemTestReporter.addTestStep("Check if text inside serachbar is equal to " + typeText,"Text inside serachbar is equal to " + typeText, STATUS.PASS, takeSnapShot());
        	} else{
				GemTestReporter.addTestStep("Check if text inside serachbar is equal to " + typeText,"Text inside serachbar is not equal", STATUS.FAIL, takeSnapShot());
        	}
		}
		catch(Exception e){
			GemTestReporter.addTestStep("Check if text inside serachbar is equal to " + typeText,"Text inside serachbar is not equal", STATUS.FAIL, takeSnapShot());
        };
    }

    public void clickOnSerachButton() {
        //The below function is for web element @FindBy(Google.serachButton);
        try{
			click(Google.serachButton);
        }
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void elementIsClickableSerachButton() {
        //The below function is for web element @FindBy(Google.serachButton);
        try{
			WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds((long) 10));
        	WebElement element = wait.until(ExpectedConditions.elementToBeClickable(Google.serachButton));
        	GemTestReporter.addTestStep("Check if serachButton is clickable","serachButton is clickable", STATUS.PASS, takeSnapShot());
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Check if serachButton is clickable","serachButton is not clickable", STATUS.FAIL, takeSnapShot());
        };
    }

    public String getAttributeFromSerachButton(String attributeValue) {
        //This function is for web element @FindBy(Google.serachButton);
        String text = new String();
        try{
			text = getAttributeName(Google.serachButton, attributeValue);
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Get value of " + attributeValue + " attribute for " + "serachButton field","Unable to get attribute value", STATUS.FAIL, takeSnapShot());
        };
        return text;
    }

    public String getValueFromSerachButton(String typeText) {
        //This function is for web element @FindBy(Google.serachButton);
        String text = new String();
        try{
			text = getAttributeName(Google.serachButton,"value");
        	if(typeText.equals(text)){
				GemTestReporter.addTestStep("Verify if value of Value attribute matches" +typeText,"Validation Successfull", STATUS.PASS, takeSnapShot());
        	}
			else{
				GemTestReporter.addTestStep("Verify if value of Value attribute matches" +typeText,"Validation Failed", STATUS.FAIL, takeSnapShot());
        	}
		}
		catch(Exception e){;
        	GemTestReporter.addTestStep("Get value of Value attribute for serachButton field","Unable to get attribute value", STATUS.FAIL, takeSnapShot());
        };
        return text;
    }

    public void verifySerachButtonIsDisplayed() {
        //The below function is for web element @FindBy(Google.serachButton);
        try{
			getElement(Google.serachButton).isDisplayed();
        	GemTestReporter.addTestStep("Check if serachButton is visible","serachButton is visible", STATUS.PASS, takeSnapShot());
        }
		catch(Exception e){
			GemTestReporter.addTestStep("Check if serachButton is visible","serachButton is not visible", STATUS.FAIL, takeSnapShot());
        };
    }

    public void verifySerachButtonText(String typeText) {
        //The below function is for web element @FindBy(Google.serachButton);
        try{
			if(getElementText(Google.serachButton).equals(typeText)){;
        		GemTestReporter.addTestStep("Check if text inside serachButton is equal to " + typeText,"Text inside serachButton is equal to " + typeText, STATUS.PASS, takeSnapShot());
        	} else{
				GemTestReporter.addTestStep("Check if text inside serachButton is equal to " + typeText,"Text inside serachButton is not equal", STATUS.FAIL, takeSnapShot());
        	}
		}
		catch(Exception e){
			GemTestReporter.addTestStep("Check if text inside serachButton is equal to " + typeText,"Text inside serachButton is not equal", STATUS.FAIL, takeSnapShot());
        };
    }

    public void navigateTo(String url) {
        try{
			DriverManager.getWebDriver().navigate().to(url);
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void forwardNavigation() {
        try{
			navigateForward();
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void backwardNavigation() {
        try{
			navigateBack();
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void openApplication() {
        if (GemJarUtils.getGemJarConfigData("chromeOptions") != null && !GemJarUtils.getGemJarConfigData("chromeOptions").isEmpty()) {
			ChromeOptions options = new ChromeOptions();
        	options.addArguments("--remote-allow-origins=*");
        	options.addArguments("--" + GemJarUtils.getGemJarConfigData("chromeOptions"));
        	DriverManager.initializeChrome(options);
        }
		else{
			if(GemJarUtils.getGemJarConfigData("browserName").equals("firefox")){
				WebDriverManager.firefoxdriver().clearDriverCache().setup();
        		DriverManager.setWebDriver(new FirefoxDriver());
        	}
			else{
			ChromeOptions options = new ChromeOptions();
        	options.addArguments("--remote-allow-origins=*");
        	DriverManager.initializeChrome(options);
        		}
	}
		maximizeBrowser();
        launchUrl(GemJarUtils.getGemJarConfigData("launchUrl"));
        setImplicitTimeOut(Long.parseLong(GemJarGlobalVar.implicitTime));
        setPageLoadTimeOut(Long.parseLong(GemJarGlobalVar.pageTimeout));
        setScriptTimeOut(Long.parseLong(GemJarGlobalVar.scriptTimeout));
    }
}
