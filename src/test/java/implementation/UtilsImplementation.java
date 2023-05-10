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

public class UtilsImplementation extends DriverAction {

    public String getURL() {
        
			String url = getCurrentURL();
			try{
			GemTestReporter.addTestStep("Verify user is able to fetch url","User is able to fetch url successfully", STATUS.PASS, takeSnapShot());
        } 
		catch(Exception e){GemTestReporter.addTestStep("Verify user is able to fetch url","User is unable to fetch url", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("User is unable to fetch url");
			Settings.LOGGER.info("User gets an exception: "+e);
        }
		return url;
    }

    public void verifyURL(String expectedURL) {
        try{
			String actualURL = getCurrentURL();
        	if(actualURL.equals(expectedURL)) {
				GemTestReporter.addTestStep("Verify if current URL matches <a href ='" +expectedURL+"'>"+expectedURL+"</a>","Validation Successful", STATUS.PASS, takeSnapShot());
        	}
			else {
			GemTestReporter.addTestStep("Verify if current URL matches <a href ='" +expectedURL+"'>"+expectedURL+"</a>","Validation Failed", STATUS.FAIL, takeSnapShot());
        }
			}
		catch(Exception e){GemTestReporter.addTestStep("Verify if current URL matches <a href ='" +expectedURL+"'>"+expectedURL+"</a>","Validation Failed", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("User is unable to verify url");
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public String getTitle() {
        String title = getTitle();
			try{
			wait(Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut")));
			GemTestReporter.addTestStep("Verify user is able to fetch title","User is able to fetch title successfully", STATUS.PASS, takeSnapShot());
        } 
		catch(Exception e){
			GemTestReporter.addTestStep("Verify user is able to fetch title","User is unable to fetch title", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("User is unable to fetch title");
			Settings.LOGGER.info("User gets an exception: "+e);
        }
		return title;
    }

    public void verifyTitle(String expectedTitle) {
        
			String actualTitle = getTitle(getCurrentURL());
			try{
			wait(Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut")));
        	if(actualTitle.equals(expectedTitle)) {
				GemTestReporter.addTestStep("Verify page title ","Page title verified successfully. Expected: '" +expectedTitle+"' Actual: '" +actualTitle+"'", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("Page title verified successfully. Expected: '" +expectedTitle+"' Actual: '" +actualTitle+"'" );	}
			else {
			GemTestReporter.addTestStep("Verify page title ","Unable to verify page title. Expected: '" +expectedTitle+"' Actual: '" +actualTitle+"'", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Unable to verify page title. Expected: '" +expectedTitle+"' Actual: '" +actualTitle+"'" );	}
		}
		catch(Exception e){GemTestReporter.addTestStep("Verify page title ","Unable to verify page title. Expected: '" +expectedTitle+"' Actual: '" +actualTitle+"'", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("User is unable to verify title");
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void navigateTo(String url) {
        try{
			wait(Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut")));
			DriverManager.getWebDriver().navigate().to(url);GemTestReporter.addTestStep("Verify user is able to navigate to "+url,"User is able to navigate to "+url+" successfully", STATUS.PASS, takeSnapShot());
        			Settings.LOGGER.info("User is able to navigate to "+url+" successfully");
        }
		catch(Exception e){GemTestReporter.addTestStep("Verify user is able to navigate to "+url,"User is unable to navigate to "+url, STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("User is unable to navigate to "+url);
			Settings.LOGGER.info("User gets an exception: " + e);
        };
    }

    public void forwardNavigation() {
        try{
			wait(Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut")));
			navigateForward();
        } 
		catch(Exception e){GemTestReporter.addTestStep("Verify user is able to navigate forward","Unable to navigate forward", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("User is unable to navigate forward");
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void backwardNavigation() {
        try{
			wait(Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut")));
			navigateBack();
        } 
		catch(Exception e){GemTestReporter.addTestStep("Verify user is able to navigate backward","Unable to navigate backward", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("User is unable to navigate backward");
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void switchActiveElement() {
        try{
			wait(Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut")));
			switchToActiveElement();
        			GemTestReporter.addTestStep("Verify user is able to switch to element","User is able to switch to element", STATUS.PASS, takeSnapShot());
			Settings.LOGGER.info("User is able to switch to element");
        } 
		catch(Exception e){GemTestReporter.addTestStep("Verify user is able to switch to element","Unable to switch to element", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("User is unable to switch to element");
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void switchDefaultContent() {
        try{
			wait(Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut")));
			switchToDefaultContent();
        			GemTestReporter.addTestStep("Verify user is able to switch to default content","User is able to switch to default content", STATUS.PASS, takeSnapShot());
			Settings.LOGGER.info("User is able to switch to default content");
        } 
		catch(Exception e){GemTestReporter.addTestStep("Verify user is able to switch to default content","Unable to switch to default content", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("User is unable to switch to default content");
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void switchParentFrame() {
        try{
			wait(Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut")));
			switchToParentFrame();
        			GemTestReporter.addTestStep("Verify user is able to switch to Parent Frame","User is able to switch to Parent Frame", STATUS.PASS, takeSnapShot());
			Settings.LOGGER.info("User is able to switch to Parent Frame");
        } 
		catch(Exception e){GemTestReporter.addTestStep("Verify user is able to switch to element","Unable to switch to Parent Frame", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("User is unable to switch to Parent Frame");
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void switchFrame(String nameOrId) {
        try{
			wait(Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut")));
			switchToFrame(nameOrId);
        			GemTestReporter.addTestStep("Verify user is able to switch to Frame","User is able to switch to Frame", STATUS.PASS, takeSnapShot());
			Settings.LOGGER.info("User is able to switch to Frame");
        } 
		catch(Exception e){GemTestReporter.addTestStep("Verify user is able to switch to Frame","Unable to switch to Frame", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("User is unable to switch to Frame");
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void switchFrame(int index) {
        try{
			wait(Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut")));
			switchToFrame(index);
        			GemTestReporter.addTestStep("Verify user is able to switch to Frame","User is able to switch to Frame", STATUS.PASS, takeSnapShot());
			Settings.LOGGER.info("User is able to switch to Frame");
        } 
		catch(Exception e){GemTestReporter.addTestStep("Verify user is able to switch to Frame","Unable to switch to Frame", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("User is unable to switch to Frame");
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void switchWindow(String nameOrHandle) {
        try{
			wait(Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut")));
			DriverManager.getWebDriver().switchTo().window(nameOrHandle);
        			GemTestReporter.addTestStep("Verify user is able to switch to Window","User is able to switch to Window", STATUS.PASS, takeSnapShot());
			Settings.LOGGER.info("User is able to switch to Window");
        } 
		catch(Exception e){GemTestReporter.addTestStep("Verify user is able to switch to Window","Unable to switch to Window", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("User is unable to switch to Window");
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void wait(int duration) {
        try{
			DriverManager.getWebDriver().wait(duration);
        } 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+ e);
        };
    }

    public void clicksAndHold(String locatorName) {
        try{
			wait(Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut")));
			By locator = getLocator(locatorName);
			new Actions(DriverManager.getWebDriver()).moveToElement((WebElement) locator).clickAndHold().build().perform();
        			GemTestReporter.addTestStep("Verify user is able to click and hold "+locatorName+" element","User is able to click and hold "+locatorName+" element", STATUS.PASS, takeSnapShot());
			Settings.LOGGER.info("User is able to click and hold "+locatorName+" element");
        } 
		catch(Exception e){GemTestReporter.addTestStep("Verify user is able to click and hold "+locatorName+" element","Unable to click and hold "+locatorName+" element", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("User is unable to click and hold "+locatorName+" element");
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void tearDown() {
        try{
			wait(Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut")));
			DriverManager.quitDriver();
        			GemTestReporter.addTestStep("Verify user is able to close driver","User is able to close driver successfully", STATUS.PASS, takeSnapShot());
			Settings.LOGGER.info("User is able to close driver successfully");
        } 
		catch(Exception e){GemTestReporter.addTestStep("Verify user is able to close driver","Unable to close driver", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("Unable to close driver");
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
			wait(Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut")));
			STATUS status = maximizeToDefaultBrowserSize();
        Boolean maximizeStatus = Objects.equals(status, "PASS");;
        if(maximizeStatus) 	{
			GemTestReporter.addTestStep("Verify Browser Maximized to Default Size ","Browser Maximization successful.", STATUS.PASS, takeSnapShot());;
        			Settings.LOGGER.info("Verify Browser Maximized to Default Size ","Browser Maximization successful." );	}
			else {
			GemTestReporter.addTestStep("Verify Browser Maximized to Default Size ","Unable to maximize browser.", STATUS.FAIL, takeSnapShot());
        			Settings.LOGGER.info("Verify Browser Maximized to Default Size ","Unable to maximize browser.");	}
		}
		catch(Exception e){GemTestReporter.addTestStep("Verify Browser Maximized to Default Size ","Unable to maximize browser.", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("Unable to maximize browser.");
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void minimizeGivenBrowser() {
        try{
			wait(Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut")));
			minimizeBrowser();
        } 
		catch(Exception e){
			GemTestReporter.addTestStep("Verify Browser Minimized","Unable to minimize browser.", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("Unable to minimize browser.");
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public Object browserSize() {
        Integer sizeOfBrowser = null;
        try{
		Object size = getBrowserSize();
        if(size!=null){
			GemTestReporter.addTestStep("Get Browser Size ","Browser Size fetched successfully.", STATUS.PASS, takeSnapShot());
			Settings.LOGGER.info("Browser Size fetched successfully.");
        	sizeOfBrowser = Integer.valueOf(size.toString());
		}}		
		catch(Exception e){GemTestReporter.addTestStep("Get Browser Size ","Unable to fetch Browser Size.", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("Unable to fetch browser size");
			Settings.LOGGER.info("User gets an exception: " + e);;
        }
		;
        return sizeOfBrowser;
    }

    public void setSizeOfBrowser(int width, int height) {
        try{
			setBrowserSize(width,height);
        } 
		catch(Exception e){GemTestReporter.addTestStep("Verify User is able to set browser size","User is unable to set browser size to width: "+width+" and height: "+height+" coordinates.", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("User is unable to set browser position to width: "+width+" and height: "+height+" coordinates.");
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public void setPositionOfBrowser(int x, int y) {
        try{
			setBrowserPosition(x,y);
        } 
		catch(Exception e){GemTestReporter.addTestStep("Verify User is able to set browser position","User is unable to set browser position to x: "+x+" and y: "+y+" coordinates.", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("User is unable to set browser position to x: "+x+" and y: "+y+" coordinates.");
			Settings.LOGGER.info("User gets an exception: "+e);
        };
    }

    public Object browserPosition() {
        Integer positionOfBrowser = null;
        try{
		Object position = getBrowserLocation();
        if(position!=null){
			GemTestReporter.addTestStep("Get Browser Position ","Browser Position fetched successfully.", STATUS.PASS, takeSnapShot());
			Settings.LOGGER.info("Browser Position fetched successfully.");
        	positionOfBrowser = Integer.valueOf(position.toString());
		}}		
		catch(Exception e){GemTestReporter.addTestStep("Get Browser Position ","Unable to fetch Browser position.", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("Unable to fetch browser position");
			Settings.LOGGER.info("User gets an exception: " + e);;
        }
		;
        return positionOfBrowser;
    }

    public Object windowHandle() {
        String windowHandle =null;
		try{
		wait(Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut")));
		windowHandle = getWindowHandle();
        if(windowHandle!=null){
			GemTestReporter.addTestStep("Get Window Handle","Window Handle fetched successfully.", STATUS.PASS, takeSnapShot());
			Settings.LOGGER.info("Window Handle fetched successfully.");
        
		}}		
		catch(Exception e){GemTestReporter.addTestStep("Get Window Handle","Unable to fetch Window Handle", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("Unable to fetch Window Handle");
			Settings.LOGGER.info("User gets an exception: " + e);;
        }
		;
        return windowHandle;
    }

    public String windowHandles() {
        String windowHandles =null;
		try{
		wait(Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut")));
		windowHandles = getWindowHandles().toString();
        if(windowHandles!=null){
			GemTestReporter.addTestStep("Get Window Handles","Window Handles fetched successfully.", STATUS.PASS, takeSnapShot());
			Settings.LOGGER.info("Window Handles fetched successfully.");
        
		}}		
		catch(Exception e){GemTestReporter.addTestStep("Get Window Handles","Unable to fetch Window Handles", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("Unable to fetch Window Handles");
			Settings.LOGGER.info("User gets an exception: " + e);;
        }
		;
        return windowHandles;
    }

    public String pageSource() {
        String pageSource = getPageSource();
		try
		{
		;
        if(pageSource!=null){
			GemTestReporter.addTestStep("Get Page Source","Page Source fetched successfully.", STATUS.PASS, takeSnapShot());
			Settings.LOGGER.info("Page Source fetched successfully.");
        
		}}		
		catch(Exception e){GemTestReporter.addTestStep("Get Page Source","Unable to fetch Page Source", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("Unable to fetch Page Source");
			Settings.LOGGER.info("User gets an exception: " + e);;
        }
		;
        return pageSource;
    }

    public void closeTab() {
        try{
			closeCurrentTab();
        
			GemTestReporter.addTestStep("Verify user is able to close current tab","User successfully closes current tab.", STATUS.PASS, takeSnapShot());
			Settings.LOGGER.info("User successfully closes current tab.");
        
		}		
		catch(Exception e){GemTestReporter.addTestStep("Verify user is able to close current tab","User is unable to close current tab.", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("User is unable to close current tab.");
			Settings.LOGGER.info("User gets an exception: " + e);;
        }
		;
    }

    public void alertSwitch() {
        try{
			switchToAlert();
        
		}		
		catch(Exception e){GemTestReporter.addTestStep("Verify user is able to switch to alert","User is unable to switch to alert.", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("User is unable to switch to alert.");
			Settings.LOGGER.info("User gets an exception: " + e);;
        }
		;
    }

    public void alertAccept() {
        try{
			wait(Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut")));
			acceptAlert();
        
		}		
		catch(Exception e){GemTestReporter.addTestStep("Verify user is able to accept alert","User is unable to accept alert.", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("User is unable to accept alert.");
			Settings.LOGGER.info("User gets an exception: " + e);;
        }
		;
    }

    public void alertDismiss() {
        try{
			wait(Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut")));
			dismissAlert();
        
		}		
		catch(Exception e){GemTestReporter.addTestStep("Verify user is able to dismiss alert","User is unable to dismiss alert.", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("User is unable to dismiss alert.");
			Settings.LOGGER.info("User gets an exception: " + e);;
        }
		;
    }

    public void inputForAlert(String input) {
        try{
			wait(Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut")));
			alertInput(input);
        
		}		
		catch(Exception e){GemTestReporter.addTestStep("Verify user is able to input for an alert","User is unable to enter input for an alert.", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("User is unable to enter input for an alert.");
			Settings.LOGGER.info("User gets an exception: " + e);;
        }
		;
    }

    public void scrollUp() {
        try{
			scrollToTop();
			GemTestReporter.addTestStep("Verify user is able to scroll up","User is able to scroll up successfully.", STATUS.PASS, takeSnapShot());
			Settings.LOGGER.info("User is able to scroll up successfully.");
        
		}		
		catch(Exception e){GemTestReporter.addTestStep("Verify user is able to scroll up","Unable to scroll up", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("Unable to scroll up");
			Settings.LOGGER.info("User gets an exception: " + e);;
        }
		;
    }

    public void scrollDown() {
        try{
			scrollToBottom();
			GemTestReporter.addTestStep("Verify user is able to scroll down","User is able to scroll down successfully.", STATUS.PASS, takeSnapShot());
			Settings.LOGGER.info("User is able to scroll down successfully.");
        
		}		
		catch(Exception e){GemTestReporter.addTestStep("Verify user is able to scroll down","Unable to scroll down", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("Unable to scroll down");
			Settings.LOGGER.info("User gets an exception: " + e);;
        }
		;
    }

    public void scrollPage(int x, int y) {
        try{
			pageScroll(x,y);
			GemTestReporter.addTestStep("Verify user is able to scroll page to x: "+x+" and y: "+y+" coordinates","User is able to scroll page to x: "+x+" and y: "+y+" coordinates", STATUS.PASS, takeSnapShot());
			Settings.LOGGER.info("User is able to scroll page to x: "+x+" and y: "+y+" coordinates");
        
		}		
		catch(Exception e){GemTestReporter.addTestStep("Verify user is able to scroll page to x: "+x+" and y: "+y+" coordinates","Unable to scroll page to x: "+x+" and y: "+y+" coordinates", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("Unable to scroll page to x: "+x+" and y: "+y+" coordinates");
			Settings.LOGGER.info("User gets an exception: " + e);;
        }
		;
    }

    public void elementScroll(int x, int y) {
        try{
			scrollAnElementToSpecificPosition(x,y);
			GemTestReporter.addTestStep("Verify user is able to scroll element to x: "+x+" and y: "+y+" coordinates","User is able to scroll element to x: "+x+" and y: "+y+" coordinates", STATUS.PASS, takeSnapShot());
			Settings.LOGGER.info("User is able to scroll element to x: "+x+" and y: "+y+" coordinates");
        
		}		
		catch(Exception e){GemTestReporter.addTestStep("Verify user is able to scroll element to x: "+x+" and y: "+y+" coordinates","Unable to scroll element to x: "+x+" and y: "+y+" coordinates", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("Unable to scroll element to x: "+x+" and y: "+y+" coordinates");
			Settings.LOGGER.info("User gets an exception: " + e);;
        }
		;
    }

    public void refreshPage() {
        try{
			refresh();
        
		}		
		catch(Exception e){GemTestReporter.addTestStep("Verify user is able to refresh page","Unable to refresh page", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("Unable to refresh page");
			Settings.LOGGER.info("User gets an exception: " + e);;
        }
		;
    }

    public void takeScreenshot() {
        try{
			wait(Integer.parseInt(UtilsMethodCodeGenerator.readProperties("timeOut")));
			takeSnapShot();
        
		}		
		catch(Exception e){GemTestReporter.addTestStep("Verify user is able to take screenshot","Unable to take screenshot", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("Unable to take screenshot");
			Settings.LOGGER.info("User gets an exception: " + e);;
        }
		;
    }

    public By getLocator(String locatorName) {
        By locator = null;
        try{
			String className = locatorName.split("\\.")[0];
			Class<?> clazz = Class.forName("locators." + className);
			Field loc = clazz.getField(locatorName.split("\\.")[1]);
			locator = (By) loc.get(className);
        	} 
		catch(Exception e){
			Settings.LOGGER.info("User gets an exception: "+e);
			Settings.LOGGER.info("Unable to get locator");
			Serenity.recordReportData().withTitle("Failure").andContents("Unable to get locator");
			Assert.fail(e.getMessage());
        } 
		return locator;
    }

    public void clickUsingJS(String locatorName) {
        try{
				By locator = getLocator(locatorName);
			JavascriptExecutor executor = (JavascriptExecutor)DriverManager.getWebDriver();
			executor.executeScript("arguments[0].click();", locator);
			GemTestReporter.addTestStep("Verify user is able to force click on javascript element","User is able to click element : Password", STATUS.PASS, takeSnapShot());
			Settings.LOGGER.info("User is able to click element : Password");
        
		}		
		catch(Exception e){GemTestReporter.addTestStep("Verify user is able to force click","User is unable to click element : Password", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("User is unable to click element : Password");
			Settings.LOGGER.info("User gets an exception: " + e);;
        }
		;
    }

    public void dragAndDrop(String from, String to) {
        try{
			By fromLocator = getLocator(from);
			By toLocator = getLocator(to);
			new SerenityActions(DriverManager.getWebDriver()).dragAndDrop((WebElement)fromLocator, (WebElement)toLocator).build().perform();
			GemTestReporter.addTestStep("Verify user is able to scroll drag to drop element to x: Password","User is able to drag and drop element : Password", STATUS.PASS, takeSnapShot());
			Settings.LOGGER.info("User is able to drag and drop element : Password");
        
		}		
		catch(Exception e){GemTestReporter.addTestStep("Verify user is able to scroll drag to drop element to x: Password","User is able to drag and drop element : Password", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("User is able to drag and drop element : Password");
			Settings.LOGGER.info("User gets an exception: " + e);;
        }
		;
    }

    public void fileUpload(String filePath, String locatorName) {
        try{
			By locator = getLocator(locatorName);
			typeText((WebElement)locator, "filePath " + filePath);
			GemTestReporter.addTestStep("Verify user is able to upload file to drop element : Password","User is able to upload file to element : Password", STATUS.PASS, takeSnapShot());
			Settings.LOGGER.info("User is able to upload file to element : Password");
        
		}		
		catch(Exception e){GemTestReporter.addTestStep("Verify user is able to upload file to element: Password","User is unable to upload file to element : Password", STATUS.FAIL, takeSnapShot());
			Settings.LOGGER.info("User is unable to upload file to element : Password");
			Settings.LOGGER.info("User gets an exception: " + e);;
        }
		;
    }
}
