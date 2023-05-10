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
import org.openqa.selenium.*;

public class GoogleStepDefinition {

    private GoogleImplementation google = new GoogleImplementation();

    @Then("^For the Google page, User verifies (.*) is the count of child elements for FirstName element$")
    public void verifyChildElementsCountFirstName(int count) {
        google.verifyChildElementsCountFirstName(count);
    }

    @Then("^For the Google page, User verifies (.*) is the count of elements for FirstName element$")
    public void verifyCountOfElementsForFirstName(int count) {
        google.verifyCountOfElementsForFirstName(count);
    }

    @Then("^For the Google page, User verifies FirstName is present on screen$")
    public void verifyFirstNameExists() {
        //The below function is for web element @FindBy(Google.firstName);
        google.verifyFirstNameExists();
    }

    @Then("^For the Google page, User scrolls to FirstName element$")
    public void scrollToFirstNameElement() {
        //The below function is for web element @FindBy(Google.firstName);
        google.scrollToFirstNameElement();
    }

    @Then("^For the Google page, User verifies \"(.*)\" is present in FirstName element$")
    public void userVerifiesTextContainsInFirstName(String typeText) {
        google.verifyFirstNameContainsText(typeText);
    }

    @Then("^For the Google page, User verifies FirstName div is visible$")
    public void verifyFirstNameIsDisplayed() {
        //The below function is for web element @FindBy(Google.firstName);
        google.verifyFirstNameIsDisplayed();
    }

    @Then("^For the Google page, User verifies \"(.*)\" is the text of FirstName div$")
    public void verifyFirstNameText(String typeText) {
        //The below function is for web element @FindBy(Google.firstName);
        google.verifyFirstNameText(typeText);
    }

    @When("^For the Google page, User gets \"(.*)\" attribute of FirstName element$")
    public void userGetsAttributeFirstName(String attributeValue) {
        //This function is for web element @FindBy(Google.firstName);
        google.getAttributeFromFirstName(attributeValue);
    }

    @Then("^For the Google page, User verifies \"(.*)\" is the value for FirstName$")
    public void userVerifyValueForFirstName(String valueOfElement) {
        //This function is for web element @FindBy(Google.firstName);
        google.verifyValueFromFirstName(valueOfElement);
    }

    @Then("^For the Google page, User verifies attribute \"(.*?)\" contains \"(.*?)\" value for FirstName$")
    public void verifyAttributeContainsValueForFirstName(String attribute, String value) {
        //The below function is for web element @FindBy(Google.firstName);
        google.verifyAttributeContainsValueForFirstName(attribute, value);
    }
}
