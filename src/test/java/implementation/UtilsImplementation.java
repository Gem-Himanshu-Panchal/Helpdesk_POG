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

public class UtilsImplementation extends DriverAction {

    public String getURL() {
        try{
			String text = getCurrentURL();
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        }
		return getCurrentURL();
    }

    public void verifyURL(String typeText) {
        try{
			String text = getCurrentURL();
        	if(text.equals(typeText)) {
				GemTestReporter.addTestStep("Verify if current URL matches <a href ='" +typeText+"'>"+typeText+"</a>","Validation Successfull", STATUS.PASS, takeSnapShot());
        	}
			else {
			GemTestReporter.addTestStep("Verify if current URL matches <a href ='" +typeText+"'>"+typeText+"</a>","Validation Failed", STATUS.FAIL, takeSnapShot());
        	}
		}
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public String getTitle() {
        try{
			String text = getTitle();
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        }
		return getCurrentURL();
    }

    public void verifyTitle(String typeText) {
        try{
			String text = getTitle(getCurrentURL());
        	if(text.equals(typeText)) {
				GemTestReporter.addTestStep("Verify page title ","Page title verified successfully. Expected: '" +typeText+"' Actual: '" +text+"'", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Page title verified successfully. Expected: '" +typeText+"' Actual: '" +text+"'" );	}
			else {
			GemTestReporter.addTestStep("Verify page title ","Unable to verify page title. Expected: '" +typeText+"' Actual: '" +text+"'", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Unable to verify page title. Expected: '" +typeText+"' Actual: '" +text+"'" );	}
		}
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
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
