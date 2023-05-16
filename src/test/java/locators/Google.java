package locators;

import annotation.LocatorType;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

public class Google extends PageObject {

    @LocatorType(value="table")
    public static By firstName= By.xpath("//*[@id='fname']");
    @LocatorType(value="image")
    public static By ftName= By.xpath("//*[@id='fname']");




}