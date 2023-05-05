package stepdefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import java.util.concurrent.TimeUnit;
import implementation.GoogleImplementation;
import locators.Google;
import implementation.UtilsImplementation;
import utils.UtilFunctions;

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

    @When("^User navigates Forward to Next Page$")
    public void navigateForward() {
        utils.forwardNavigation();
    }

    @When(" ^User navigates Back to Previous Page$")
    public void navigateBack() {
        utils.backwardNavigation();
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
    public void userClicksAndHoldsElement(String locator) {
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
