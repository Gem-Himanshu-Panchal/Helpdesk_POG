package locators;

import annotation.LocatorType;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Google extends PageObject {

    @LocatorType(value = "input")
    public static By firstName = By.xpath("//*[@id='fname']");

    @LocatorType(value = "button")
    public static By password = By.id("password");

    @LocatorType(value = "click")
    public static By clickItem = By.id("password");

    @LocatorType(value = "dropdown")
    public static By dropdownItem = By.id("password");

    @LocatorType(value = "image")
    public static By imageItem = By.id("password");

    @LocatorType(value = "a")
    public static By aItem = By.id("password");
    

	@LocatorType(value = "input")
	public static By serachbar= By.xpath("/html/body/div[5]/ul/li/span/svg");

	@LocatorType(value = "button")
	public static By serachButton= By.xpath("/html/body/div[5]/ul/li/span/svg");
}