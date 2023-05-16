package locators;

import annotation.LocatorType;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.List;

public class Sample extends PageObject {

    @LocatorType(value = "input")
    public static By firstName = By.xpath("//*[@id='fname']");

    @LocatorType(value = "input")
    public static By password = By.id("password");

    @LocatorType(value = "input")
    public static By lastName = By.id("lname");

    @LocatorType(value = "input")
    public static By heading = By.id("h1");

    @LocatorType(value = "input")
    public static By birthday = By.id("birthday");

    @LocatorType(value = "radio button")
    public static By css = By.id("css");

    @LocatorType(value = "radio button")
    public static By javascript = By.id("javascript");

    @LocatorType(value = "checkbox")
    public static By Bike = By.id("vehicle1");

    @LocatorType(value = "checkbox")
    public static By Boat = By.id("vehicle3");

    @LocatorType(value = "dropdown")
    public static By car = By.id("cars");

    @LocatorType(value = "button")
    public static By Submit = By.xpath("//input[@type='button']");

    @LocatorType(value = "a")
    public static By html = By.xpath("//a[contains(text(),'HTML')]");


}