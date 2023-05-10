package locators;

import annotation.LocatorType;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

public class Google extends PageObject {

    @LocatorType(value="input")
    public static By firstName= By.xpath("//*[@id='fname']");
//    @LocatorType(value="input")
//    public static By password=By.id("password");



}