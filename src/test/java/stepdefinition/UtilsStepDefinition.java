package stepdefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import java.util.concurrent.TimeUnit;
import implementation.SampleImplementation;
import locators.Sample;
import implementation.UtilsImplementation;
import utils.UtilFunctions;
import org.openqa.selenium.*;

public class UtilsStepDefinition {

    private UtilsImplementation utils = new UtilsImplementation();

    private UtilFunctions utilFunctions = new UtilFunctions();

    @Then("^User gets current url of the page$")
    public void getUrl() {
        utils.getURL();
    }

    @And("^User presses Enter key$")
    public void pressEnter() {
        utilFunctions.pressEnter();
    }

    @When("^User performs Select All Action$")
    public void selectAll() {
        utilFunctions.selectAll();
    }

    @And("^User performs Copy Action$")
    public void copy() {
        utilFunctions.copy();
    }

    @Then("^User performs Paste Action$")
    public void paste() {
        utilFunctions.paste();
    }

    @Then("^User shifts focus to \"(.*)\" window$")
    public void windowFocus(String windowHandle) {
        utilFunctions.getWindowFocus(windowHandle);
    }

    @And("^User verifies the current URL with \"(.*)\"$")
    public void verifyUrl(String url) {
        utils.verifyURL(url);
    }

    @Then("^User gets title of the page$")
    public void getTitle() {
        utils.getTitle();
    }

    @And("^User verifies \"(.*)\" is the title of the page$")
    public void verifyTitle(String title) {
        utils.verifyTitle(title);
    }

    @Given("^User navigates to \"(.*)\"$")
    public void navigateTo(String url) {
        utils.navigateTo(url);
    }

    @When("User navigates Forward to Next Page$")
    public void navigateForward() {
        utils.forwardNavigation();
    }

    @When(" ^User navigates Back to Previous Page$")
    public void navigateBack() {
        utils.backwardNavigation();
    }

    @When("^User maximizes window to default")
    public void maximizeBrowserToDefault() {
        utils.maximizeBrowserToDefault();
    }

    @When("^User minimizes window to default")
    public void minimizeGivenBrowser() {
        utils.minimizeGivenBrowser();
    }

    @When("^User gets browser size")
    public void browserSize() {
        utils.browserSize();
    }

    @When("^User sets browser size to \"(.*)\" width and \"(.*)\" height")
    public void setSizeOfBrowser(Integer width, Integer height) {
        utils.setSizeOfBrowser(width,height);
    }

    @When("^User sets browser position to \"(.*)\" x coordinate and \"(.*)\" y coordinate")
    public void setPositionOfBrowser(Integer x, Integer y) {
        utils.setPositionOfBrowser(x,y);
    }

    @When("^User gets browser positions")
    public void browserPosition() {
        utils.browserPosition();
    }

    @When("^User gets window handle")
    public void windowHandle() {
        utils.windowHandle();
    }

    @When("^User gets window handles")
    public void windowHandles() {
        utils.windowHandles();
    }

    @When("^User gets page source")
    public void pageSource() {
        utils.pageSource();
    }

    @When("User closes current tab")
    public void closeTab() {
        utils.closeTab();
    }

    @When("User closes current tab and switches to \"(.*)\" tab")
    public void closeTabAndSwitch(String windowName) {
        utilFunctions.closeTabAndSwitch(windowName);
    }

    @When("^User switches to alert")
    public void alertSwitch() {
        utils.alertSwitch();
    }

    @When("^User accepts alert")
    public void alertAccept() {
        utils.alertAccept();
    }

    @When("^User dismisses alert")
    public void alertDismiss() {
        utils.alertDismiss();
    }

    @When("^User enters \"(.*)\" as alert input")
    public void inputForAlert(String input) {
        utils.inputForAlert(input);
    }

    @When("^User scrolls page up")
    public void scrollUp() {
        utils.scrollUp();
    }

    @When("^User scrolls page down")
    public void scrollDown() {
        utils.scrollDown();
    }

    @When("^User scrolls page to \"(.*)\" x coordinate and \"(.*)\" y coordinate")
    public void scrollPage(Integer x, Integer y) {
        utils.scrollPage(x,y);
    }

    @When("^User scrolls element to \"(.*)\" x coordinate and \"(.*)\" y coordinate")
    public void elementScroll(Integer x, Integer y) {
        utils.elementScroll(x,y);
    }

    @When("^User navigates to \"(.*)\" url")
    public void urlNavigation(String url) {
        utils.urlNavigation(url);
    }

    @When("^User refreshes page")
    public void refreshPage() {
        utils.refreshPage();
    }

    @When("^User take snapshot")
    public void takeScreenshot(String filePath) {
        utils.takeScreenshot(filePath);
    }

    @When("^User switches to Active Element$")
    public void switchActiveElement() {
        utils.switchActiveElement();
    }

    @When("^User switches to Parent Frame$")
    public void switchParentFrame() {
        utils.switchParentFrame();
    }

    @When("^User switches to Default Content$")
    public void switchDefaultContent() {
        utils.switchDefaultContent();
    }

    @When("^User switches to \"(.*)\" frame$")
    public void switchFrame(String nameOrId) {
        utils.switchFrame(nameOrId);
    }

    @When("^User switches to (.*) frame$")
    public void switchFrame(int index) {
        utils.switchFrame(index);
    }

    @When("^User switches to \"(.*)\" window$")
    public void switchWindow(String nameOrHandle) {
        utils.switchWindow(nameOrHandle);
    }

    @When("^User waits for (.*) seconds$")
    public void waitFor(int duration) {
        utils.wait(duration*1000);
    }

    @Then("^User clicks and holds \"(.*?)\" element$")
    public void userClicksAndHoldsElement(By locator) {
        utils.clicksAndHold(locator);
    }

    @Then("^User closes browser$")
    public void closeBrowser() {
        utils.tearDown();
    }

    @Given("^User is on homepage$")
    public void openApplication() {
        utils.openApplication();
    }
}
