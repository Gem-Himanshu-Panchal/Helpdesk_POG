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

public class GoogleStepDefinition {

    private GoogleImplementation google = new GoogleImplementation();

    @When("^For the Google page, User clicks on \"(.*?)\" button$")
    public void userClicksOnSubmit(String a) {
        google.clickOnSubmit(a);
    }

    @Then("^For the Google page, User is able to click Submit element$")
    public void verifyUserIsClickableSubmit() {
        google.elementIsClickableSubmit();
    }

    @Then("^For the Google page, User verifies \"(.*)\" is present in Submit element$")
    public void userVerifiesTextContainsInSubmit(String typeText) {
        google.verifySubmitContainsText(typeText);
    }

    @Then("^For the Google page, User verifies Submit button is visible$")
    public void verifySubmitIsDisplayed() {
        //The below function is for web element @FindBy(Google.submit);
        google.verifySubmitIsDisplayed();
    }

    @Then("^For the Google page, User verifies \"(.*)\" is the text of Submit button$")
    public void verifySubmitText(String typeText) {
        //The below function is for web element @FindBy(Google.submit);
        google.verifySubmitText(typeText);
    }

    @When("^For the Google page, User gets \"(.*)\" attribute of Submit element$")
    public void userGetsAttributeSubmit(String attributeValue) {
        //This function is for web element @FindBy(Google.submit);
        google.getAttributeFromSubmit(attributeValue);
    }

    @Then("^For the Google page, User verifies \"(.*)\" is the value for Submit$")
    public void userVerifyValueForSubmit(String valueOfElement) {
        //This function is for web element @FindBy(Google.submit);
        google.verifyValueFromSubmit(valueOfElement);
    }
}
