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

    @When("^For the Sample page, User clears text for LastName input element$")
    public void userClearLastName() {
        //The below function is for web element @FindBy(Sample.lastName);
        sample.clearLastName();
    }

    @When("^For the Sample page, User gets the text of LastName element$")
    public void userGetTextLastName() {
        //The below function is for web element @FindBy(Sample.lastName);
        sample.getTextFromLastName();
    }

    @When("^For the Sample page, User enters \"(.*)\" as LastName element and presses enter$")
    public void typeTextAndEnterForLastName(String typeText) {
        //The below function is for web element @FindBy(Sample.lastName);
        sample.typeTextAndEnterForLastName(typeText);
    }

    @When("^For the Sample page, User enters \"(.*)\" as LastName element and presses tab$")
    public void typeTextAndTabForLastName(String typeText) {
        //The below function is for web element @FindBy(Sample.lastName);
        sample.typeTextAndTabForLastName(typeText);
    }

    @When("^For the Sample page, User enters \"(.*)\" as input for LastName$")
    public void userEntersAsLastName(String typeText) {
        sample.typeTextIntoLastName(typeText);
    }

    @Then("^For the Sample page, User verifies LastName input is visible$")
    public void verifyLastNameIsDisplayed() {
        //The below function is for web element @FindBy(Sample.lastName);
        sample.verifyLastNameIsDisplayed();
    }

    @Then("^For the Sample page, User verifies \"(.*)\" is the text of LastName input$")
    public void verifyLastNameText(String typeText) {
        //The below function is for web element @FindBy(Sample.lastName);
        sample.verifyLastNameText(typeText);
    }

    @When("^For the Sample page, User verifies LastName input is enabled$")
    public void userIsEnabledLastName() {
        sample.verifyLastNameIsEnabled();
    }

    @When("^For the Sample page, User gets \"(.*)\" attribute of LastName element$")
    public void userGetsAttributeLastName(String attributeValue) {
        //This function is for web element @FindBy(Sample.lastName);
        sample.getAttributeFromLastName(attributeValue);
    }

    @Then("^For the Sample page, User verifies \"(.*)\" is the value for LastName$")
    public void userVerifyValueForLastName(String valueOfElement) {
        //This function is for web element @FindBy(Sample.lastName);
        sample.verifyValueFromLastName(valueOfElement);
    }

    @Then("^For the Sample page, User verifies \"(.*)\" attribute for LastName as \"(.*)\"$")
    public void userVerifyAttributeForLastName(String attributeName, String attributeValue) {
        //This function is for web element @FindBy(Sample.lastName);
        sample.verifyAttributeValueForLastName(attributeName,attributeValue);
    }

    @Then("^For the Sample page, User verifies value for LastName input element is cleared$")
    public void verifyValueClearedForLastName() {
        sample.verifyValueClearedForLastName();
    }

    @Then("^For the Sample page, User hovers over LastName and checks \"(.*)\" label$")
    public void hoverOverLastName(String label) {
        //The below function is for web element @FindBy(Sample.lastName);
        sample.hoverOverLastName(label);
    }

    @Then("^For the Sample page, User verifies LastName is present on screen$")
    public void verifyLastNameExists() {
        //The below function is for web element @FindBy(Sample.lastName);
        sample.verifyLastNameExists();
    }

    @Then("^For the Sample page, User scrolls to LastName element$")
    public void scrollToLastNameElement() {
        //The below function is for web element @FindBy(Sample.lastName);
        sample.scrollToLastNameElement();
    }

    @Then("^For the Sample page, User verifies \"(.*)\" is present in LastName element$")
    public void userVerifiesTextContainsInLastName(String typeText) {
        sample.verifyLastNameContainsText(typeText);
    }

    @When("^For the Sample page, User clears text for Heading input element$")
    public void userClearHeading() {
        //The below function is for web element @FindBy(Sample.heading);
        sample.clearHeading();
    }

    @When("^For the Sample page, User gets the text of Heading element$")
    public void userGetTextHeading() {
        //The below function is for web element @FindBy(Sample.heading);
        sample.getTextFromHeading();
    }

    @When("^For the Sample page, User enters \"(.*)\" as Heading element and presses enter$")
    public void typeTextAndEnterForHeading(String typeText) {
        //The below function is for web element @FindBy(Sample.heading);
        sample.typeTextAndEnterForHeading(typeText);
    }

    @When("^For the Sample page, User enters \"(.*)\" as Heading element and presses tab$")
    public void typeTextAndTabForHeading(String typeText) {
        //The below function is for web element @FindBy(Sample.heading);
        sample.typeTextAndTabForHeading(typeText);
    }

    @When("^For the Sample page, User enters \"(.*)\" as input for Heading$")
    public void userEntersAsHeading(String typeText) {
        sample.typeTextIntoHeading(typeText);
    }

    @Then("^For the Sample page, User verifies Heading input is visible$")
    public void verifyHeadingIsDisplayed() {
        //The below function is for web element @FindBy(Sample.heading);
        sample.verifyHeadingIsDisplayed();
    }

    @Then("^For the Sample page, User verifies \"(.*)\" is the text of Heading input$")
    public void verifyHeadingText(String typeText) {
        //The below function is for web element @FindBy(Sample.heading);
        sample.verifyHeadingText(typeText);
    }

    @When("^For the Sample page, User verifies Heading input is enabled$")
    public void userIsEnabledHeading() {
        sample.verifyHeadingIsEnabled();
    }

    @When("^For the Sample page, User gets \"(.*)\" attribute of Heading element$")
    public void userGetsAttributeHeading(String attributeValue) {
        //This function is for web element @FindBy(Sample.heading);
        sample.getAttributeFromHeading(attributeValue);
    }

    @Then("^For the Sample page, User verifies \"(.*)\" is the value for Heading$")
    public void userVerifyValueForHeading(String valueOfElement) {
        //This function is for web element @FindBy(Sample.heading);
        sample.verifyValueFromHeading(valueOfElement);
    }

    @Then("^For the Sample page, User verifies \"(.*)\" attribute for Heading as \"(.*)\"$")
    public void userVerifyAttributeForHeading(String attributeName, String attributeValue) {
        //This function is for web element @FindBy(Sample.heading);
        sample.verifyAttributeValueForHeading(attributeName,attributeValue);
    }

    @Then("^For the Sample page, User verifies value for Heading input element is cleared$")
    public void verifyValueClearedForHeading() {
        sample.verifyValueClearedForHeading();
    }

    @Then("^For the Sample page, User hovers over Heading and checks \"(.*)\" label$")
    public void hoverOverHeading(String label) {
        //The below function is for web element @FindBy(Sample.heading);
        sample.hoverOverHeading(label);
    }

    @Then("^For the Sample page, User verifies Heading is present on screen$")
    public void verifyHeadingExists() {
        //The below function is for web element @FindBy(Sample.heading);
        sample.verifyHeadingExists();
    }

    @Then("^For the Sample page, User scrolls to Heading element$")
    public void scrollToHeadingElement() {
        //The below function is for web element @FindBy(Sample.heading);
        sample.scrollToHeadingElement();
    }

    @Then("^For the Sample page, User verifies \"(.*)\" is present in Heading element$")
    public void userVerifiesTextContainsInHeading(String typeText) {
        sample.verifyHeadingContainsText(typeText);
    }

    @When("^For the Sample page, User clears text for Birthday input element$")
    public void userClearBirthday() {
        //The below function is for web element @FindBy(Sample.birthday);
        sample.clearBirthday();
    }

    @When("^For the Sample page, User gets the text of Birthday element$")
    public void userGetTextBirthday() {
        //The below function is for web element @FindBy(Sample.birthday);
        sample.getTextFromBirthday();
    }

    @When("^For the Sample page, User enters \"(.*)\" as Birthday element and presses enter$")
    public void typeTextAndEnterForBirthday(String typeText) {
        //The below function is for web element @FindBy(Sample.birthday);
        sample.typeTextAndEnterForBirthday(typeText);
    }

    @When("^For the Sample page, User enters \"(.*)\" as Birthday element and presses tab$")
    public void typeTextAndTabForBirthday(String typeText) {
        //The below function is for web element @FindBy(Sample.birthday);
        sample.typeTextAndTabForBirthday(typeText);
    }

    @When("^For the Sample page, User enters \"(.*)\" as input for Birthday$")
    public void userEntersAsBirthday(String typeText) {
        sample.typeTextIntoBirthday(typeText);
    }

    @Then("^For the Sample page, User verifies Birthday input is visible$")
    public void verifyBirthdayIsDisplayed() {
        //The below function is for web element @FindBy(Sample.birthday);
        sample.verifyBirthdayIsDisplayed();
    }

    @Then("^For the Sample page, User verifies \"(.*)\" is the text of Birthday input$")
    public void verifyBirthdayText(String typeText) {
        //The below function is for web element @FindBy(Sample.birthday);
        sample.verifyBirthdayText(typeText);
    }

    @When("^For the Sample page, User verifies Birthday input is enabled$")
    public void userIsEnabledBirthday() {
        sample.verifyBirthdayIsEnabled();
    }

    @When("^For the Sample page, User gets \"(.*)\" attribute of Birthday element$")
    public void userGetsAttributeBirthday(String attributeValue) {
        //This function is for web element @FindBy(Sample.birthday);
        sample.getAttributeFromBirthday(attributeValue);
    }

    @Then("^For the Sample page, User verifies \"(.*)\" is the value for Birthday$")
    public void userVerifyValueForBirthday(String valueOfElement) {
        //This function is for web element @FindBy(Sample.birthday);
        sample.verifyValueFromBirthday(valueOfElement);
    }

    @Then("^For the Sample page, User verifies \"(.*)\" attribute for Birthday as \"(.*)\"$")
    public void userVerifyAttributeForBirthday(String attributeName, String attributeValue) {
        //This function is for web element @FindBy(Sample.birthday);
        sample.verifyAttributeValueForBirthday(attributeName,attributeValue);
    }

    @Then("^For the Sample page, User verifies value for Birthday input element is cleared$")
    public void verifyValueClearedForBirthday() {
        sample.verifyValueClearedForBirthday();
    }

    @Then("^For the Sample page, User hovers over Birthday and checks \"(.*)\" label$")
    public void hoverOverBirthday(String label) {
        //The below function is for web element @FindBy(Sample.birthday);
        sample.hoverOverBirthday(label);
    }

    @Then("^For the Sample page, User verifies Birthday is present on screen$")
    public void verifyBirthdayExists() {
        //The below function is for web element @FindBy(Sample.birthday);
        sample.verifyBirthdayExists();
    }

    @Then("^For the Sample page, User scrolls to Birthday element$")
    public void scrollToBirthdayElement() {
        //The below function is for web element @FindBy(Sample.birthday);
        sample.scrollToBirthdayElement();
    }

    @Then("^For the Sample page, User verifies \"(.*)\" is present in Birthday element$")
    public void userVerifiesTextContainsInBirthday(String typeText) {
        sample.verifyBirthdayContainsText(typeText);
    }

    @When("^For the Sample page, User selects Css radio button$")
    public void userSelectsCss() {
        sample.selectCss();
    }

    @Then("^For the Sample page, User verifies Css is selected$")
    public void verifyCssIsSelected() {
        //The below function is for web element @FindBy(Sample.css);
        sample.verifyCssIsSelected();
    }

    @When("^For the Sample page, User verifies Css radio button is enabled$")
    public void userIsEnabledCss() {
        sample.verifyCssIsEnabled();
    }

    @Then("^For the Sample page, User verifies Css radio button is visible$")
    public void verifyCssIsDisplayed() {
        //The below function is for web element @FindBy(Sample.css);
        sample.verifyCssIsDisplayed();
    }

    @Then("^For the Sample page, User verifies \"(.*)\" is the text of Css radio button$")
    public void verifyCssText(String typeText) {
        //The below function is for web element @FindBy(Sample.css);
        sample.verifyCssText(typeText);
    }

    @When("^For the Sample page, User gets \"(.*)\" attribute of Css element$")
    public void userGetsAttributeCss(String attributeValue) {
        //This function is for web element @FindBy(Sample.css);
        sample.getAttributeFromCss(attributeValue);
    }

    @Then("^For the Sample page, User verifies \"(.*)\" is the value for Css$")
    public void userVerifyValueForCss(String valueOfElement) {
        //This function is for web element @FindBy(Sample.css);
        sample.verifyValueFromCss(valueOfElement);
    }

    @Then("^For the Sample page, User hovers over Css and checks \"(.*)\" label$")
    public void hoverOverCss(String label) {
        //The below function is for web element @FindBy(Sample.css);
        sample.hoverOverCss(label);
    }

    @Then("^For the Sample page, User verifies Css is present on screen$")
    public void verifyCssExists() {
        //The below function is for web element @FindBy(Sample.css);
        sample.verifyCssExists();
    }

    @Then("^For the Sample page, User scrolls to Css element$")
    public void scrollToCssElement() {
        //The below function is for web element @FindBy(Sample.css);
        sample.scrollToCssElement();
    }

    @Then("^For the Sample page, User verifies \"(.*)\" is present in Css element$")
    public void userVerifiesTextContainsInCss(String typeText) {
        sample.verifyCssContainsText(typeText);
    }

    @When("^For the Sample page, User selects Javascript radio button$")
    public void userSelectsJavascript() {
        sample.selectJavascript();
    }

    @Then("^For the Sample page, User verifies Javascript is selected$")
    public void verifyJavascriptIsSelected() {
        //The below function is for web element @FindBy(Sample.javascript);
        sample.verifyJavascriptIsSelected();
    }

    @When("^For the Sample page, User verifies Javascript radio button is enabled$")
    public void userIsEnabledJavascript() {
        sample.verifyJavascriptIsEnabled();
    }

    @Then("^For the Sample page, User verifies Javascript radio button is visible$")
    public void verifyJavascriptIsDisplayed() {
        //The below function is for web element @FindBy(Sample.javascript);
        sample.verifyJavascriptIsDisplayed();
    }

    @Then("^For the Sample page, User verifies \"(.*)\" is the text of Javascript radio button$")
    public void verifyJavascriptText(String typeText) {
        //The below function is for web element @FindBy(Sample.javascript);
        sample.verifyJavascriptText(typeText);
    }

    @When("^For the Sample page, User gets \"(.*)\" attribute of Javascript element$")
    public void userGetsAttributeJavascript(String attributeValue) {
        //This function is for web element @FindBy(Sample.javascript);
        sample.getAttributeFromJavascript(attributeValue);
    }

    @Then("^For the Sample page, User verifies \"(.*)\" is the value for Javascript$")
    public void userVerifyValueForJavascript(String valueOfElement) {
        //This function is for web element @FindBy(Sample.javascript);
        sample.verifyValueFromJavascript(valueOfElement);
    }

    @Then("^For the Sample page, User hovers over Javascript and checks \"(.*)\" label$")
    public void hoverOverJavascript(String label) {
        //The below function is for web element @FindBy(Sample.javascript);
        sample.hoverOverJavascript(label);
    }

    @Then("^For the Sample page, User verifies Javascript is present on screen$")
    public void verifyJavascriptExists() {
        //The below function is for web element @FindBy(Sample.javascript);
        sample.verifyJavascriptExists();
    }

    @Then("^For the Sample page, User scrolls to Javascript element$")
    public void scrollToJavascriptElement() {
        //The below function is for web element @FindBy(Sample.javascript);
        sample.scrollToJavascriptElement();
    }

    @Then("^For the Sample page, User verifies \"(.*)\" is present in Javascript element$")
    public void userVerifiesTextContainsInJavascript(String typeText) {
        sample.verifyJavascriptContainsText(typeText);
    }

    @When("^For the Sample page, User selects Bike checkbox$")
    public void userSelectsBike() {
        sample.selectBike();
    }

    @Then("^For the Sample page, User is able to click Bike element$")
    public void verifyUserIsClickableBike() {
        sample.elementIsClickableBike();
    }

    @Then("^For the Sample page, User verifies Bike checkbox is visible$")
    public void verifyBikeIsDisplayed() {
        //The below function is for web element @FindBy(Sample.Bike);
        sample.verifyBikeIsDisplayed();
    }

    @Then("^For the Sample page, User verifies \"(.*)\" is the text of Bike checkbox$")
    public void verifyBikeText(String typeText) {
        //The below function is for web element @FindBy(Sample.Bike);
        sample.verifyBikeText(typeText);
    }

    @When("^For the Sample page, User gets \"(.*)\" attribute of Bike element$")
    public void userGetsAttributeBike(String attributeValue) {
        //This function is for web element @FindBy(Sample.Bike);
        sample.getAttributeFromBike(attributeValue);
    }

    @Then("^For the Sample page, User verifies \"(.*)\" is the value for Bike$")
    public void userVerifyValueForBike(String valueOfElement) {
        //This function is for web element @FindBy(Sample.Bike);
        sample.verifyValueFromBike(valueOfElement);
    }

    @Then("^For the Sample page, User verifies Bike checkbox is selected$")
    public void verifyElementSelectedForBike() {
        //The below function is for web element @FindBy(Sample.Bike);
        sample.verifyElementSelectedForBike();
    }

    @Then("^For the Sample page, User verifies Bike checkbox is not selected$")
    public void verifyElementNotSelectedForBike() {
        //The below function is for web element @FindBy(Sample.Bike);
        sample.verifyElementNotSelectedForBike();
    }

    @Then("^For the Sample page, User verifies Bike is present on screen$")
    public void verifyBikeExists() {
        //The below function is for web element @FindBy(Sample.Bike);
        sample.verifyBikeExists();
    }

    @Then("^For the Sample page, User scrolls to Bike element$")
    public void scrollToBikeElement() {
        //The below function is for web element @FindBy(Sample.Bike);
        sample.scrollToBikeElement();
    }

    @Then("^For the Sample page, User verifies \"(.*)\" is present in Bike element$")
    public void userVerifiesTextContainsInBike(String typeText) {
        sample.verifyBikeContainsText(typeText);
    }

    @When("^For the Sample page, User selects Boat checkbox$")
    public void userSelectsBoat() {
        sample.selectBoat();
    }

    @Then("^For the Sample page, User is able to click Boat element$")
    public void verifyUserIsClickableBoat() {
        sample.elementIsClickableBoat();
    }

    @Then("^For the Sample page, User verifies Boat checkbox is visible$")
    public void verifyBoatIsDisplayed() {
        //The below function is for web element @FindBy(Sample.Boat);
        sample.verifyBoatIsDisplayed();
    }

    @Then("^For the Sample page, User verifies \"(.*)\" is the text of Boat checkbox$")
    public void verifyBoatText(String typeText) {
        //The below function is for web element @FindBy(Sample.Boat);
        sample.verifyBoatText(typeText);
    }

    @When("^For the Sample page, User gets \"(.*)\" attribute of Boat element$")
    public void userGetsAttributeBoat(String attributeValue) {
        //This function is for web element @FindBy(Sample.Boat);
        sample.getAttributeFromBoat(attributeValue);
    }

    @Then("^For the Sample page, User verifies \"(.*)\" is the value for Boat$")
    public void userVerifyValueForBoat(String valueOfElement) {
        //This function is for web element @FindBy(Sample.Boat);
        sample.verifyValueFromBoat(valueOfElement);
    }

    @Then("^For the Sample page, User verifies Boat checkbox is selected$")
    public void verifyElementSelectedForBoat() {
        //The below function is for web element @FindBy(Sample.Boat);
        sample.verifyElementSelectedForBoat();
    }

    @Then("^For the Sample page, User verifies Boat checkbox is not selected$")
    public void verifyElementNotSelectedForBoat() {
        //The below function is for web element @FindBy(Sample.Boat);
        sample.verifyElementNotSelectedForBoat();
    }

    @Then("^For the Sample page, User verifies Boat is present on screen$")
    public void verifyBoatExists() {
        //The below function is for web element @FindBy(Sample.Boat);
        sample.verifyBoatExists();
    }

    @Then("^For the Sample page, User scrolls to Boat element$")
    public void scrollToBoatElement() {
        //The below function is for web element @FindBy(Sample.Boat);
        sample.scrollToBoatElement();
    }

    @Then("^For the Sample page, User verifies \"(.*)\" is present in Boat element$")
    public void userVerifiesTextContainsInBoat(String typeText) {
        sample.verifyBoatContainsText(typeText);
    }

    @Then("^For the Sample page, User is able to click Car element$")
    public void verifyUserIsClickableCar() {
        sample.elementIsClickableCar();
    }

    @When("^For the Sample page, User verifies Car dropdown is enabled$")
    public void userIsEnabledCar() {
        sample.verifyCarIsEnabled();
    }

    @When("^For the Sample page, User gets the text of Car element$")
    public void userGetTextCar() {
        //The below function is for web element @FindBy(Sample.car);
        sample.getTextFromCar();
    }

    @Then("^For the Sample page, User verifies Car dropdown is visible$")
    public void verifyCarIsDisplayed() {
        //The below function is for web element @FindBy(Sample.car);
        sample.verifyCarIsDisplayed();
    }

    @When("^For the Sample page, User selects \"(.*)\" from Car dropdown$")
    public void userSelectFromDropdownCar(String selectValue) {
        sample.selectCar(selectValue);
    }

    @When("^For the Sample page, User deselects \"(.*)\" from Car dropdown$")
    public void userDeselectFromDropdownCar(String deselectValue) {
        sample.deselectCar(deselectValue);
    }

    @Then("^For the Sample page, User verifies \"(.*)\" is the text of Car dropdown$")
    public void verifyCarText(String typeText) {
        //The below function is for web element @FindBy(Sample.car);
        sample.verifyCarText(typeText);
    }

    @When("^For the Sample page, User gets \"(.*)\" attribute of Car element$")
    public void userGetsAttributeCar(String attributeValue) {
        //This function is for web element @FindBy(Sample.car);
        sample.getAttributeFromCar(attributeValue);
    }

    @Then("^For the Sample page, User verifies \"(.*)\" is the value for Car$")
    public void userVerifyValueForCar(String valueOfElement) {
        //This function is for web element @FindBy(Sample.car);
        sample.verifyValueFromCar(valueOfElement);
    }

    @Then("^For the Sample page, User hovers over Car and checks \"(.*)\" label$")
    public void hoverOverCar(String label) {
        //The below function is for web element @FindBy(Sample.car);
        sample.hoverOverCar(label);
    }

    @Then("^For the Sample page, User verifies Car is present on screen$")
    public void verifyCarExists() {
        //The below function is for web element @FindBy(Sample.car);
        sample.verifyCarExists();
    }

    @Then("^For the Sample page, User scrolls to Car element$")
    public void scrollToCarElement() {
        //The below function is for web element @FindBy(Sample.car);
        sample.scrollToCarElement();
    }

    @Then("^For the Sample page, User verifies \"(.*)\" is present in Car element$")
    public void userVerifiesTextContainsInCar(String typeText) {
        sample.verifyCarContainsText(typeText);
    }

    @When("^For the Sample page, User clicks on Submit button$")
    public void userClicksOnSubmit() {
        sample.clickOnSubmit();
    }

    @Then("^For the Sample page, User is able to click Submit element$")
    public void verifyUserIsClickableSubmit() {
        sample.elementIsClickableSubmit();
    }

    @Then("^For the Sample page, User verifies \"(.*)\" is present in Submit element$")
    public void userVerifiesTextContainsInSubmit(String typeText) {
        sample.verifySubmitContainsText(typeText);
    }

    @Then("^For the Sample page, User verifies Submit button is visible$")
    public void verifySubmitIsDisplayed() {
        //The below function is for web element @FindBy(Sample.Submit);
        sample.verifySubmitIsDisplayed();
    }

    @Then("^For the Sample page, User verifies \"(.*)\" is the text of Submit button$")
    public void verifySubmitText(String typeText) {
        //The below function is for web element @FindBy(Sample.Submit);
        sample.verifySubmitText(typeText);
    }

    @When("^For the Sample page, User gets \"(.*)\" attribute of Submit element$")
    public void userGetsAttributeSubmit(String attributeValue) {
        //This function is for web element @FindBy(Sample.Submit);
        sample.getAttributeFromSubmit(attributeValue);
    }

    @Then("^For the Sample page, User verifies \"(.*)\" is the value for Submit$")
    public void userVerifyValueForSubmit(String valueOfElement) {
        //This function is for web element @FindBy(Sample.Submit);
        sample.verifyValueFromSubmit(valueOfElement);
    }

    @Then("^For the Sample page, User hovers over Submit and checks \"(.*)\" label$")
    public void hoverOverSubmit(String label) {
        //The below function is for web element @FindBy(Sample.Submit);
        sample.hoverOverSubmit(label);
    }

    @Then("^For the Sample page, User verifies Submit is present on screen$")
    public void verifySubmitExists() {
        //The below function is for web element @FindBy(Sample.Submit);
        sample.verifySubmitExists();
    }

    @Then("^For the Sample page, User scrolls to Submit element$")
    public void scrollToSubmitElement() {
        //The below function is for web element @FindBy(Sample.Submit);
        sample.scrollToSubmitElement();
    }

    @When("^For the Sample page, User clicks on Html link$")
    public void userClicksOnHtml() {
        sample.clickOnHtml();
    }

    @When("^For the Sample page, User clicks on Html link and navigates back$")
    public void userClicksOnHtmlAndNavigateBack() {
        sample.clickOnHtmlAndNavigateBack();
    }

    @Then("^For the Sample page, User is able to click Html element$")
    public void verifyUserIsClickableHtml() {
        sample.elementIsClickableHtml();
    }

    @Then("^For the Sample page, User verifies Html link is visible$")
    public void verifyHtmlIsDisplayed() {
        //The below function is for web element @FindBy(Sample.html);
        sample.verifyHtmlIsDisplayed();
    }

    @Then("^For the Sample page, User verifies \"(.*)\" is the text of Html link$")
    public void verifyHtmlText(String typeText) {
        //The below function is for web element @FindBy(Sample.html);
        sample.verifyHtmlText(typeText);
    }

    @When("^For the Sample page, User gets \"(.*)\" attribute of Html element$")
    public void userGetsAttributeHtml(String attributeValue) {
        //This function is for web element @FindBy(Sample.html);
        sample.getAttributeFromHtml(attributeValue);
    }

    @Then("^For the Sample page, User verifies \"(.*)\" is the value for Html$")
    public void userVerifyValueForHtml(String valueOfElement) {
        //This function is for web element @FindBy(Sample.html);
        sample.verifyValueFromHtml(valueOfElement);
    }

    @Then("^For the Sample page, User hovers over Html and checks \"(.*)\" label$")
    public void hoverOverHtml(String label) {
        //The below function is for web element @FindBy(Sample.html);
        sample.hoverOverHtml(label);
    }

    @Then("^For the Sample page, User verifies Html is present on screen$")
    public void verifyHtmlExists() {
        //The below function is for web element @FindBy(Sample.html);
        sample.verifyHtmlExists();
    }

    @Then("^For the Sample page, User scrolls to Html element$")
    public void scrollToHtmlElement() {
        //The below function is for web element @FindBy(Sample.html);
        sample.scrollToHtmlElement();
    }

    @Then("^For the Sample page, User verifies \"(.*)\" is present in Html element$")
    public void userVerifiesTextContainsInHtml(String typeText) {
        sample.verifyHtmlContainsText(typeText);
    }
}
