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

public class SampleStepDefinition {

    private SampleImplementation sample = new SampleImplementation();

    @When("^For the Sample page, User clears text for FirstName input element$")
    public void userClearFirstName() {
        //The below function is for web element @FindBy(Sample.firstName);
        sample.clearFirstName();
    }

    @When("^For the Sample page, User gets the text of FirstName element$")
    public void userGetTextFirstName() {
        //The below function is for web element @FindBy(Sample.firstName);
        sample.getTextFromFirstName();
    }

    @When("^For the Sample page, User enters \"(.*)\" as FirstName element and presses enter$")
    public void typeTextAndEnterForFirstName(String typeText) {
        //The below function is for web element @FindBy(Sample.firstName);
        sample.typeTextAndEnterForFirstName(typeText);
    }

    @When("^For the Sample page, User enters \"(.*)\" as FirstName element and presses tab$")
    public void typeTextAndTabForFirstName(String typeText) {
        //The below function is for web element @FindBy(Sample.firstName);
        sample.typeTextAndTabForFirstName(typeText);
    }

    @When("^For the Sample page, User enters \"(.*)\" as input for FirstName$")
    public void userEntersAsFirstName(String typeText) {
        sample.typeTextIntoFirstName(typeText);
    }

    @Then("^For the Sample page, User verifies FirstName input is visible$")
    public void verifyFirstNameIsDisplayed() {
        //The below function is for web element @FindBy(Sample.firstName);
        sample.verifyFirstNameIsDisplayed();
    }

    @Then("^For the Sample page, User verifies \"(.*)\" is the text of FirstName input$")
    public void verifyFirstNameText(String typeText) {
        //The below function is for web element @FindBy(Sample.firstName);
        sample.verifyFirstNameText(typeText);
    }

    @When("^For the Sample page, User verifies FirstName input is enabled$")
    public void userIsEnabledFirstName() {
        sample.verifyFirstNameIsEnabled();
    }

    @When("^For the Sample page, User gets \"(.*)\" attribute of FirstName element$")
    public void userGetsAttributeFirstName(String attributeValue) {
        //This function is for web element @FindBy(Sample.firstName);
        sample.getAttributeFromFirstName(attributeValue);
    }

    @Then("^For the Sample page, User verifies \"(.*)\" is the value for FirstName$")
    public void userVerifyValueForFirstName(String valueOfElement) {
        //This function is for web element @FindBy(Sample.firstName);
        sample.verifyValueFromFirstName(valueOfElement);
    }

    @Then("^For the Sample page, User verifies \"(.*)\" attribute for FirstName as \"(.*)\"$")
    public void userVerifyAttributeForFirstName(String attributeName, String attributeValue) {
        //This function is for web element @FindBy(Sample.firstName);
        sample.verifyAttributeValueForFirstName(attributeName,attributeValue);
    }

    @Then("^For the Sample page, User verifies value for FirstName input element is cleared$")
    public void verifyValueClearedForFirstName() {
        sample.verifyValueClearedForFirstName();
    }

    @Then("^For the Sample page, User hovers over FirstName and checks \"(.*)\" label$")
    public void hoverOverFirstName(String label) {
        //The below function is for web element @FindBy(Sample.firstName);
        sample.hoverOverFirstName(label);
    }

    @Then("^For the Sample page, User verifies FirstName is present on screen$")
    public void verifyFirstNameExists() {
        //The below function is for web element @FindBy(Sample.firstName);
        sample.verifyFirstNameExists();
    }

    @Then("^For the Sample page, User scrolls to FirstName element$")
    public void scrollToFirstNameElement() {
        //The below function is for web element @FindBy(Sample.firstName);
        sample.scrollToFirstNameElement();
    }

    @Then("^For the Sample page, User verifies \"(.*)\" is present in FirstName element$")
    public void userVerifiesTextContainsInFirstName(String typeText) {
        sample.verifyFirstNameContainsText(typeText);
    }

    @Then("^For the Sample page, User verifies attribute \"(.*?)\" contains \"(.*?)\" value for FirstName$")
    public void verifyAttributeContainsValueForFirstName(String attribute, String value) {
        //The below function is for web element @FindBy(Sample.firstName);
        sample.verifyAttributeContainsValueForFirstName(attribute, value);
    }

    @When("^For the Sample page, User clears text for Password input element$")
    public void userClearPassword() {
        //The below function is for web element @FindBy(Sample.password);
        sample.clearPassword();
    }

    @When("^For the Sample page, User gets the text of Password element$")
    public void userGetTextPassword() {
        //The below function is for web element @FindBy(Sample.password);
        sample.getTextFromPassword();
    }

    @When("^For the Sample page, User enters \"(.*)\" as Password element and presses enter$")
    public void typeTextAndEnterForPassword(String typeText) {
        //The below function is for web element @FindBy(Sample.password);
        sample.typeTextAndEnterForPassword(typeText);
    }

    @When("^For the Sample page, User enters \"(.*)\" as Password element and presses tab$")
    public void typeTextAndTabForPassword(String typeText) {
        //The below function is for web element @FindBy(Sample.password);
        sample.typeTextAndTabForPassword(typeText);
    }

    @When("^For the Sample page, User enters \"(.*)\" as input for Password$")
    public void userEntersAsPassword(String typeText) {
        sample.typeTextIntoPassword(typeText);
    }

    @Then("^For the Sample page, User verifies Password input is visible$")
    public void verifyPasswordIsDisplayed() {
        //The below function is for web element @FindBy(Sample.password);
        sample.verifyPasswordIsDisplayed();
    }

    @Then("^For the Sample page, User verifies \"(.*)\" is the text of Password input$")
    public void verifyPasswordText(String typeText) {
        //The below function is for web element @FindBy(Sample.password);
        sample.verifyPasswordText(typeText);
    }

    @When("^For the Sample page, User verifies Password input is enabled$")
    public void userIsEnabledPassword() {
        sample.verifyPasswordIsEnabled();
    }

    @When("^For the Sample page, User gets \"(.*)\" attribute of Password element$")
    public void userGetsAttributePassword(String attributeValue) {
        //This function is for web element @FindBy(Sample.password);
        sample.getAttributeFromPassword(attributeValue);
    }

    @Then("^For the Sample page, User verifies \"(.*)\" is the value for Password$")
    public void userVerifyValueForPassword(String valueOfElement) {
        //This function is for web element @FindBy(Sample.password);
        sample.verifyValueFromPassword(valueOfElement);
    }

    @Then("^For the Sample page, User verifies \"(.*)\" attribute for Password as \"(.*)\"$")
    public void userVerifyAttributeForPassword(String attributeName, String attributeValue) {
        //This function is for web element @FindBy(Sample.password);
        sample.verifyAttributeValueForPassword(attributeName,attributeValue);
    }

    @Then("^For the Sample page, User verifies value for Password input element is cleared$")
    public void verifyValueClearedForPassword() {
        sample.verifyValueClearedForPassword();
    }

    @Then("^For the Sample page, User hovers over Password and checks \"(.*)\" label$")
    public void hoverOverPassword(String label) {
        //The below function is for web element @FindBy(Sample.password);
        sample.hoverOverPassword(label);
    }

    @Then("^For the Sample page, User verifies Password is present on screen$")
    public void verifyPasswordExists() {
        //The below function is for web element @FindBy(Sample.password);
        sample.verifyPasswordExists();
    }

    @Then("^For the Sample page, User scrolls to Password element$")
    public void scrollToPasswordElement() {
        //The below function is for web element @FindBy(Sample.password);
        sample.scrollToPasswordElement();
    }

    @Then("^For the Sample page, User verifies \"(.*)\" is present in Password element$")
    public void userVerifiesTextContainsInPassword(String typeText) {
        sample.verifyPasswordContainsText(typeText);
    }

    @Then("^For the Sample page, User verifies attribute \"(.*?)\" contains \"(.*?)\" value for Password$")
    public void verifyAttributeContainsValueForPassword(String attribute, String value) {
        //The below function is for web element @FindBy(Sample.password);
        sample.verifyAttributeContainsValueForPassword(attribute, value);
    }
}
