package locators;

import annotation.LocatorType;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.List;

public class Google extends PageObject {

    @LocatorType(value = "dropdown")
    public static By searchInput = By.id("numbers");

//    @LocatorType(value = "button")
//    public static By searchButton = By.xpath("//*[@aria-label='Google Search']");
//
//    @LocatorType(value = "a")
//    public static By images = By.xpath("//a[contains(@aria-label,'Images')]");
}