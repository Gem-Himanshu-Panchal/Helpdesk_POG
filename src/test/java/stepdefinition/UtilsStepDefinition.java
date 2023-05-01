package stepdefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.PageFactory;
import java.util.concurrent.TimeUnit;
import implementation.GoogleImplementation;
import locators.Google;
import implementation.UtilsImplementation;
import utils.UtilFunctions;

public class UtilsStepDefinition {

    private UtilsImplementation utils = new UtilsImplementation();

    private UtilFunctions utilFunctions = new UtilFunctions();

    @Then("User gets current url of the page$")
    public void getUrl() {
        utils.getURL();
    }

    @And("User presses Enter key$")
    public void pressEnter() {
        utilFunctions.pressEnter();
    }

    @When("User performs Paste Action$")
    public void selectAll() {
        utilFunctions.selectAll();
    }

    @And("User performs Copy Action$")
    public void copy() {
        utilFunctions.copy();
    }

    @Then("User performs Paste Action$")
    public void paste() {
        utilFunctions.paste();
    }

    @And("User verifies the current URL with \"(.*)\"$")
    public void verifyUrl(String url) {
        utils.verifyURL(url);
    }

    @Then("User gets title of the page$")
    public void getTitle() {
        utils.getTitle();
    }

    @And("User verifies \"(.*)\" is the title of the page$")
    public void verifyTitle(String title) {
        utils.verifyTitle(title);
    }

    @Given("User navigates to \"(.*)\"$")
    public void navigateTo(String url) {
        utils.navigateTo(url);
    }

    @When("User navigates Forward to Next Page$")
    public void navigateForward() {
        utils.forwardNavigation();
    }

    @When("User navigates Back to Previous Page$")
    public void navigateBack() {
        utils.backwardNavigation();
    }

    @Given("User is on homepage$")
    public void openApplication() {
        utils.openApplication();
    }
}
