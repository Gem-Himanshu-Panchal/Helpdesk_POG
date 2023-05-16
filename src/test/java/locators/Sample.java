package locators;

import annotation.LocatorType;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.List;

public class Sample extends PageObject {

    @LocatorType(value = "input")
    public static By firstName = By.xpath("//*[@id='fname']");

    @LocatorType(value = "image")
    public static By lastName = By.id("lname");

    @LocatorType(value = "table")
    public static By heading = By.id("h1");

    @LocatorType(value = "radio button")
    public static By css = By.id("css");

    @LocatorType(value = "checkbox")
    public static By Bike = By.id("vehicle1");

    @LocatorType(value = "dropdown")
    public static By car = By.id("cars");

    @LocatorType(value = "button")
    public static By Submit = By.xpath("//input[@type='submit']");

    @LocatorType(value = "a")
    public static By html = By.xpath("//a[contains(text(),'HTML')]");
}