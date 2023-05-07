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

    public void maximizeBrowserToDefault() {
        try{
			STATUS status = maximizeToDefaultBrowserSize();
        Boolean maximizeStatus = Objects.equals(status, "PASS");;
        if(maximizeStatus) 	{
			GemTestReporter.addTestStep("Verify Browser Maximized to Default Size ","Browser Maximization successful.", STATUS.PASS, takeSnapShot());;
        			Settings.LOGGER.info("Verify Browser Maximized to Default Size ","Browser Maximization successful." );	}
			else {
			GemTestReporter.addTestStep("Verify Browser Maximized to Default Size ","Unable to maximize browser.", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Verify Browser Maximized to Default Size ","Unable to maximize browser.");	}
		}
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void minimizeGivenBrowser() {
        try{
			minimizeBrowser();
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public Object browserSize() {
        Object size = getBrowserSize();
        Integer sizeOfBrowser = null;
        if(size!=null){
			GemTestReporter.addTestStep("Get Browser Size ","Browser Size fetched successfully.", STATUS.PASS, takeSnapShot());
        	sizeOfBrowser = Integer.valueOf(size.toString());
		}
		else
		{
			GemTestReporter.addTestStep("Get Browser Size ","Unable to fetch browser size.", STATUS.FAIL, takeSnapShot());
        }
		;
        return sizeOfBrowser;
    }

    public void setSizeOfBrowser(int width, int height) {
        try{
			setBrowserSize(width,height);
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void setPositionOfBrowser(int x, int y) {
        try{
			setBrowserPosition(x,y);
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public Object browserPosition() {
        Object position = getBrowserLocation();
        Integer positionOfBrowser = null;
        if(position!=null){
			GemTestReporter.addTestStep("Get Browser Position ","Browser Position fetched successfully.", STATUS.PASS, takeSnapShot());
        	positionOfBrowser = Integer.valueOf(position.toString());
		}
		else
		{
			GemTestReporter.addTestStep("Get Position Size ","Unable to fetch browser position.", STATUS.FAIL, takeSnapShot());
        }
		;
        return positionOfBrowser;
    }

    public Object windowHandle() {
        String windowHandle = getWindowHandle().toString();
        if(windowHandle!=null){
			GemTestReporter.addTestStep("Get Window Handle ","Window Handle fetched successfully.", STATUS.PASS, takeSnapShot());
        
		}
		else
		{
			GemTestReporter.addTestStep("Get Window Handle ","Unable to fetch Window Handle.", STATUS.FAIL, takeSnapShot());
        }
		;
        return windowHandle;
    }

    public String windowHandles() {
        String windowHandles = getWindowHandles().toString();
        if(windowHandles!=null){
			GemTestReporter.addTestStep("Get Window Handles ","Window Handles fetched successfully.", STATUS.PASS, takeSnapShot());
        
		}
		else
		{
			GemTestReporter.addTestStep("Get Window Handles ","Unable to fetch Window Handles.", STATUS.FAIL, takeSnapShot());
        }
		;
        return windowHandles;
    }

    public String pageSource() {
        String pageSource = getPageSource();
        if(pageSource!=null){
			GemTestReporter.addTestStep("Get Page Source ","Page Source fetched successfully.", STATUS.PASS, takeSnapShot());
        
		}
		else
		{
			GemTestReporter.addTestStep("Get Page Source ","Unable to fetch Page Source.", STATUS.FAIL, takeSnapShot());
        }
		;
        return pageSource;
    }

    public String closeTab() {
        try{
			closeCurrentTab();
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        }
		return getCurrentURL();
    }

    public void alertSwitch() {
        try{
			switchToAlert();
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        }
		;
    }

    public void alertAccept() {
        try{
			acceptAlert();
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        }
		;
    }

    public void alertDismiss() {
        try{
			dismissAlert();
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        }
		;
    }

    public void inputForAlert(String input) {
        try{
			alertInput(input);
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        }
		;
    }

    public void scrollUp() {
        try{
			scrollToTop();
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        }
		;
    }

    public void scrollDown() {
        try{
			scrollToBottom();
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        }
		;
    }

    public void scrollPage(int x, int y) {
        try{
			pageScroll(x,y);
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        }
		;
    }

    public void elementScroll(int x, int y) {
        try{
			scrollAnElementToSpecificPosition(x,y);
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        }
		;
    }

    public void urlNavigation(String url) {
        try{
			navigateToUrl(url);
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        }
		;
    }

    public void refreshPage() {
        try{
			refresh();
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        }
		;
    }

    public void takeScreenshot() {
        try{
			takeSnapShot();
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
        }
		;
    }
}
