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

public class GoogleStepDefinition {

    private GoogleImplementation google = new GoogleImplementation();

    @When("^For the Google page, User clears text for FirstName input element$")
    public void userClearFirstName() {
        //The below function is for web element @FindBy(Google.firstName);
        google.clearFirstName();
    }

    @When("^For the Google page, User gets the text of FirstName element$")
    public void userGetTextFirstName() {
        //The below function is for web element @FindBy(Google.firstName);
        google.getTextFromFirstName();
    }

    @When("^For the Google page, User enters \"(.*)\" as FirstName element and presses enter$")
    public void typeTextAndEnterForFirstName(String typeText) {
        //The below function is for web element @FindBy(Google.firstName);
        google.typeTextAndEnterForFirstName(typeText);
    }

    @When("^For the Google page, User enters \"(.*)\" as FirstName element and presses tab$")
    public void typeTextAndTabForFirstName(String typeText) {
        //The below function is for web element @FindBy(Google.firstName);
        google.typeTextAndTabForFirstName(typeText);
    }

    @When("^For the Google page, User enters \"(.*)\" as input for FirstName$")
    public void userEntersAsFirstName(String typeText) {
        google.typeTextIntoFirstName(typeText);
    }

    @Then("^For the Google page, User verifies FirstName input is visible$")
    public void verifyFirstNameIsDisplayed() {
        //The below function is for web element @FindBy(Google.firstName);
        google.verifyFirstNameIsDisplayed();
    }

    @Then("^For the Google page, User verifies \"(.*)\" is the text of FirstName input$")
    public void verifyFirstNameText(String typeText) {
        //The below function is for web element @FindBy(Google.firstName);
        google.verifyFirstNameText(typeText);
    }

    @When("^For the Google page, User verifies FirstName input is enabled$")
    public void userIsEnabledFirstName() {
        google.verifyFirstNameIsEnabled();
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

    @Then("^For the Google page, User verifies \"(.*)\" attribute for FirstName as \"(.*)\"$")
    public void userVerifyAttributeForFirstName(String attributeName, String attributeValue) {
        //This function is for web element @FindBy(Google.firstName);
        google.verifyAttributeValueForFirstName(attributeName,attributeValue);
    }

    @Then("^For the Google page, User verifies value for FirstName input element is cleared$")
    public void verifyValueClearedForFirstName() {
        google.verifyValueClearedForFirstName();
    }

    @When("^For the Google page, User clears text for Password input element$")
    public void userClearPassword() {
        //The below function is for web element @FindBy(Google.password);
        google.clearPassword();
    }

    @When("^For the Google page, User gets the text of Password element$")
    public void userGetTextPassword() {
        //The below function is for web element @FindBy(Google.password);
        google.getTextFromPassword();
    }

    @When("^For the Google page, User enters \"(.*)\" as Password element and presses enter$")
    public void typeTextAndEnterForPassword(String typeText) {
        //The below function is for web element @FindBy(Google.password);
        google.typeTextAndEnterForPassword(typeText);
    }

    @When("^For the Google page, User enters \"(.*)\" as Password element and presses tab$")
    public void typeTextAndTabForPassword(String typeText) {
        //The below function is for web element @FindBy(Google.password);
        google.typeTextAndTabForPassword(typeText);
    }

    @When("^For the Google page, User enters \"(.*)\" as input for Password$")
    public void userEntersAsPassword(String typeText) {
        google.typeTextIntoPassword(typeText);
    }

    @Then("^For the Google page, User verifies Password input is visible$")
    public void verifyPasswordIsDisplayed() {
        //The below function is for web element @FindBy(Google.password);
        google.verifyPasswordIsDisplayed();
    }

    @Then("^For the Google page, User verifies \"(.*)\" is the text of Password input$")
    public void verifyPasswordText(String typeText) {
        //The below function is for web element @FindBy(Google.password);
        google.verifyPasswordText(typeText);
    }

    @When("^For the Google page, User verifies Password input is enabled$")
    public void userIsEnabledPassword() {
        google.verifyPasswordIsEnabled();
    }

    @When("^For the Google page, User gets \"(.*)\" attribute of Password element$")
    public void userGetsAttributePassword(String attributeValue) {
        //This function is for web element @FindBy(Google.password);
        google.getAttributeFromPassword(attributeValue);
    }

    @Then("^For the Google page, User verifies \"(.*)\" is the value for Password$")
    public void userVerifyValueForPassword(String valueOfElement) {
        //This function is for web element @FindBy(Google.password);
        google.verifyValueFromPassword(valueOfElement);
    }

    @Then("^For the Google page, User verifies \"(.*)\" attribute for Password as \"(.*)\"$")
    public void userVerifyAttributeForPassword(String attributeName, String attributeValue) {
        //This function is for web element @FindBy(Google.password);
        google.verifyAttributeValueForPassword(attributeName,attributeValue);
    }

    @Then("^For the Google page, User verifies value for Password input element is cleared$")
    public void verifyValueClearedForPassword() {
        google.verifyValueClearedForPassword();
    }

    @When("^For the Google page, User clears text for LastName input element$")
    public void userClearLastName() {
        //The below function is for web element @FindBy(Google.lastName);
        google.clearLastName();
    }

    @When("^For the Google page, User gets the text of LastName element$")
    public void userGetTextLastName() {
        //The below function is for web element @FindBy(Google.lastName);
        google.getTextFromLastName();
    }

    @When("^For the Google page, User enters \"(.*)\" as LastName element and presses enter$")
    public void typeTextAndEnterForLastName(String typeText) {
        //The below function is for web element @FindBy(Google.lastName);
        google.typeTextAndEnterForLastName(typeText);
    }

    @When("^For the Google page, User enters \"(.*)\" as LastName element and presses tab$")
    public void typeTextAndTabForLastName(String typeText) {
        //The below function is for web element @FindBy(Google.lastName);
        google.typeTextAndTabForLastName(typeText);
    }

    @When("^For the Google page, User enters \"(.*)\" as input for LastName$")
    public void userEntersAsLastName(String typeText) {
        google.typeTextIntoLastName(typeText);
    }

    @Then("^For the Google page, User verifies LastName input is visible$")
    public void verifyLastNameIsDisplayed() {
        //The below function is for web element @FindBy(Google.lastName);
        google.verifyLastNameIsDisplayed();
    }

    @Then("^For the Google page, User verifies \"(.*)\" is the text of LastName input$")
    public void verifyLastNameText(String typeText) {
        //The below function is for web element @FindBy(Google.lastName);
        google.verifyLastNameText(typeText);
    }

    @When("^For the Google page, User verifies LastName input is enabled$")
    public void userIsEnabledLastName() {
        google.verifyLastNameIsEnabled();
    }

    @When("^For the Google page, User gets \"(.*)\" attribute of LastName element$")
    public void userGetsAttributeLastName(String attributeValue) {
        //This function is for web element @FindBy(Google.lastName);
        google.getAttributeFromLastName(attributeValue);
    }

    @Then("^For the Google page, User verifies \"(.*)\" is the value for LastName$")
    public void userVerifyValueForLastName(String valueOfElement) {
        //This function is for web element @FindBy(Google.lastName);
        google.verifyValueFromLastName(valueOfElement);
    }

    @Then("^For the Google page, User verifies \"(.*)\" attribute for LastName as \"(.*)\"$")
    public void userVerifyAttributeForLastName(String attributeName, String attributeValue) {
        //This function is for web element @FindBy(Google.lastName);
        google.verifyAttributeValueForLastName(attributeName,attributeValue);
    }

    @Then("^For the Google page, User verifies value for LastName input element is cleared$")
    public void verifyValueClearedForLastName() {
        google.verifyValueClearedForLastName();
    }

    @When("^For the Google page, User clears text for Heading input element$")
    public void userClearHeading() {
        //The below function is for web element @FindBy(Google.heading);
        google.clearHeading();
    }

    @When("^For the Google page, User gets the text of Heading element$")
    public void userGetTextHeading() {
        //The below function is for web element @FindBy(Google.heading);
        google.getTextFromHeading();
    }

    @When("^For the Google page, User enters \"(.*)\" as Heading element and presses enter$")
    public void typeTextAndEnterForHeading(String typeText) {
        //The below function is for web element @FindBy(Google.heading);
        google.typeTextAndEnterForHeading(typeText);
    }

    @When("^For the Google page, User enters \"(.*)\" as Heading element and presses tab$")
    public void typeTextAndTabForHeading(String typeText) {
        //The below function is for web element @FindBy(Google.heading);
        google.typeTextAndTabForHeading(typeText);
    }

    @When("^For the Google page, User enters \"(.*)\" as input for Heading$")
    public void userEntersAsHeading(String typeText) {
        google.typeTextIntoHeading(typeText);
    }

    @Then("^For the Google page, User verifies Heading input is visible$")
    public void verifyHeadingIsDisplayed() {
        //The below function is for web element @FindBy(Google.heading);
        google.verifyHeadingIsDisplayed();
    }

    @Then("^For the Google page, User verifies \"(.*)\" is the text of Heading input$")
    public void verifyHeadingText(String typeText) {
        //The below function is for web element @FindBy(Google.heading);
        google.verifyHeadingText(typeText);
    }

    @When("^For the Google page, User verifies Heading input is enabled$")
    public void userIsEnabledHeading() {
        google.verifyHeadingIsEnabled();
    }

    @When("^For the Google page, User gets \"(.*)\" attribute of Heading element$")
    public void userGetsAttributeHeading(String attributeValue) {
        //This function is for web element @FindBy(Google.heading);
        google.getAttributeFromHeading(attributeValue);
    }

    @Then("^For the Google page, User verifies \"(.*)\" is the value for Heading$")
    public void userVerifyValueForHeading(String valueOfElement) {
        //This function is for web element @FindBy(Google.heading);
        google.verifyValueFromHeading(valueOfElement);
    }

    @Then("^For the Google page, User verifies \"(.*)\" attribute for Heading as \"(.*)\"$")
    public void userVerifyAttributeForHeading(String attributeName, String attributeValue) {
        //This function is for web element @FindBy(Google.heading);
        google.verifyAttributeValueForHeading(attributeName,attributeValue);
    }

    @Then("^For the Google page, User verifies value for Heading input element is cleared$")
    public void verifyValueClearedForHeading() {
        google.verifyValueClearedForHeading();
    }

    @When("^For the Google page, User clears text for Birthday input element$")
    public void userClearBirthday() {
        //The below function is for web element @FindBy(Google.birthday);
        google.clearBirthday();
    }

    @When("^For the Google page, User gets the text of Birthday element$")
    public void userGetTextBirthday() {
        //The below function is for web element @FindBy(Google.birthday);
        google.getTextFromBirthday();
    }

    @When("^For the Google page, User enters \"(.*)\" as Birthday element and presses enter$")
    public void typeTextAndEnterForBirthday(String typeText) {
        //The below function is for web element @FindBy(Google.birthday);
        google.typeTextAndEnterForBirthday(typeText);
    }

    @When("^For the Google page, User enters \"(.*)\" as Birthday element and presses tab$")
    public void typeTextAndTabForBirthday(String typeText) {
        //The below function is for web element @FindBy(Google.birthday);
        google.typeTextAndTabForBirthday(typeText);
    }

    @When("^For the Google page, User enters \"(.*)\" as input for Birthday$")
    public void userEntersAsBirthday(String typeText) {
        google.typeTextIntoBirthday(typeText);
    }

    @Then("^For the Google page, User verifies Birthday input is visible$")
    public void verifyBirthdayIsDisplayed() {
        //The below function is for web element @FindBy(Google.birthday);
        google.verifyBirthdayIsDisplayed();
    }

    @Then("^For the Google page, User verifies \"(.*)\" is the text of Birthday input$")
    public void verifyBirthdayText(String typeText) {
        //The below function is for web element @FindBy(Google.birthday);
        google.verifyBirthdayText(typeText);
    }

    @When("^For the Google page, User verifies Birthday input is enabled$")
    public void userIsEnabledBirthday() {
        google.verifyBirthdayIsEnabled();
    }

    @When("^For the Google page, User gets \"(.*)\" attribute of Birthday element$")
    public void userGetsAttributeBirthday(String attributeValue) {
        //This function is for web element @FindBy(Google.birthday);
        google.getAttributeFromBirthday(attributeValue);
    }

    @Then("^For the Google page, User verifies \"(.*)\" is the value for Birthday$")
    public void userVerifyValueForBirthday(String valueOfElement) {
        //This function is for web element @FindBy(Google.birthday);
        google.verifyValueFromBirthday(valueOfElement);
    }

    @Then("^For the Google page, User verifies \"(.*)\" attribute for Birthday as \"(.*)\"$")
    public void userVerifyAttributeForBirthday(String attributeName, String attributeValue) {
        //This function is for web element @FindBy(Google.birthday);
        google.verifyAttributeValueForBirthday(attributeName,attributeValue);
    }

    @Then("^For the Google page, User verifies value for Birthday input element is cleared$")
    public void verifyValueClearedForBirthday() {
        google.verifyValueClearedForBirthday();
    }

    @When("^For the Google page, User selects Css radio button$")
    public void userSelectsCss() {
        google.selectCss();
    }

    @Then("^For the Google page, User verifies Css is selected$")
    public void verifyCssIsSelected() {
        //The below function is for web element @FindBy(Google.css);
        google.verifyCssIsSelected();
    }

    @When("^For the Google page, User verifies Css radio button is enabled$")
    public void userIsEnabledCss() {
        google.verifyCssIsEnabled();
    }

    @Then("^For the Google page, User verifies Css radio button is visible$")
    public void verifyCssIsDisplayed() {
        //The below function is for web element @FindBy(Google.css);
        google.verifyCssIsDisplayed();
    }

    @Then("^For the Google page, User verifies \"(.*)\" is the text of Css radio button$")
    public void verifyCssText(String typeText) {
        //The below function is for web element @FindBy(Google.css);
        google.verifyCssText(typeText);
    }

    @When("^For the Google page, User gets \"(.*)\" attribute of Css element$")
    public void userGetsAttributeCss(String attributeValue) {
        //This function is for web element @FindBy(Google.css);
        google.getAttributeFromCss(attributeValue);
    }

    @Then("^For the Google page, User verifies \"(.*)\" is the value for Css$")
    public void userVerifyValueForCss(String valueOfElement) {
        //This function is for web element @FindBy(Google.css);
        google.verifyValueFromCss(valueOfElement);
    }

    @When("^For the Google page, User selects Javascript radio button$")
    public void userSelectsJavascript() {
        google.selectJavascript();
    }

    @Then("^For the Google page, User verifies Javascript is selected$")
    public void verifyJavascriptIsSelected() {
        //The below function is for web element @FindBy(Google.javascript);
        google.verifyJavascriptIsSelected();
    }

    @When("^For the Google page, User verifies Javascript radio button is enabled$")
    public void userIsEnabledJavascript() {
        google.verifyJavascriptIsEnabled();
    }

    @Then("^For the Google page, User verifies Javascript radio button is visible$")
    public void verifyJavascriptIsDisplayed() {
        //The below function is for web element @FindBy(Google.javascript);
        google.verifyJavascriptIsDisplayed();
    }

    @Then("^For the Google page, User verifies \"(.*)\" is the text of Javascript radio button$")
    public void verifyJavascriptText(String typeText) {
        //The below function is for web element @FindBy(Google.javascript);
        google.verifyJavascriptText(typeText);
    }

    @When("^For the Google page, User gets \"(.*)\" attribute of Javascript element$")
    public void userGetsAttributeJavascript(String attributeValue) {
        //This function is for web element @FindBy(Google.javascript);
        google.getAttributeFromJavascript(attributeValue);
    }

    @Then("^For the Google page, User verifies \"(.*)\" is the value for Javascript$")
    public void userVerifyValueForJavascript(String valueOfElement) {
        //This function is for web element @FindBy(Google.javascript);
        google.verifyValueFromJavascript(valueOfElement);
    }

    @When("^For the Google page, User selects Bike checkbox$")
    public void userSelectsBike() {
        google.selectBike();
    }

    @Then("^For the Google page, User is able to click Bike element$")
    public void verifyUserIsClickableBike() {
        google.elementIsClickableBike();
    }

    @Then("^For the Google page, User verifies Bike checkbox is visible$")
    public void verifyBikeIsDisplayed() {
        //The below function is for web element @FindBy(Google.Bike);
        google.verifyBikeIsDisplayed();
    }

    @Then("^For the Google page, User verifies \"(.*)\" is the text of Bike checkbox$")
    public void verifyBikeText(String typeText) {
        //The below function is for web element @FindBy(Google.Bike);
        google.verifyBikeText(typeText);
    }

    @When("^For the Google page, User gets \"(.*)\" attribute of Bike element$")
    public void userGetsAttributeBike(String attributeValue) {
        //This function is for web element @FindBy(Google.Bike);
        google.getAttributeFromBike(attributeValue);
    }

    @Then("^For the Google page, User verifies \"(.*)\" is the value for Bike$")
    public void userVerifyValueForBike(String valueOfElement) {
        //This function is for web element @FindBy(Google.Bike);
        google.verifyValueFromBike(valueOfElement);
    }

    @Then("^For the Google page, User verifies Bike checkbox is selected$")
    public void verifyElementSelectedForBike() {
        //The below function is for web element @FindBy(Google.Bike);
        google.verifyElementSelectedForBike();
    }

    @Then("^For the Google page, User verifies Bike checkbox is not selected$")
    public void verifyElementNotSelectedForBike() {
        //The below function is for web element @FindBy(Google.Bike);
        google.verifyElementNotSelectedForBike();
    }

    @When("^For the Google page, User selects Boat checkbox$")
    public void userSelectsBoat() {
        google.selectBoat();
    }

    @Then("^For the Google page, User is able to click Boat element$")
    public void verifyUserIsClickableBoat() {
        google.elementIsClickableBoat();
    }

    @Then("^For the Google page, User verifies Boat checkbox is visible$")
    public void verifyBoatIsDisplayed() {
        //The below function is for web element @FindBy(Google.Boat);
        google.verifyBoatIsDisplayed();
    }

    @Then("^For the Google page, User verifies \"(.*)\" is the text of Boat checkbox$")
    public void verifyBoatText(String typeText) {
        //The below function is for web element @FindBy(Google.Boat);
        google.verifyBoatText(typeText);
    }

    @When("^For the Google page, User gets \"(.*)\" attribute of Boat element$")
    public void userGetsAttributeBoat(String attributeValue) {
        //This function is for web element @FindBy(Google.Boat);
        google.getAttributeFromBoat(attributeValue);
    }

    @Then("^For the Google page, User verifies \"(.*)\" is the value for Boat$")
    public void userVerifyValueForBoat(String valueOfElement) {
        //This function is for web element @FindBy(Google.Boat);
        google.verifyValueFromBoat(valueOfElement);
    }

    @Then("^For the Google page, User verifies Boat checkbox is selected$")
    public void verifyElementSelectedForBoat() {
        //The below function is for web element @FindBy(Google.Boat);
        google.verifyElementSelectedForBoat();
    }

    @Then("^For the Google page, User verifies Boat checkbox is not selected$")
    public void verifyElementNotSelectedForBoat() {
        //The below function is for web element @FindBy(Google.Boat);
        google.verifyElementNotSelectedForBoat();
    }

    @Then("^For the Google page, User is able to click Car element$")
    public void verifyUserIsClickableCar() {
        google.elementIsClickableCar();
    }

    @When("^For the Google page, User verifies Car dropdown is enabled$")
    public void userIsEnabledCar() {
        google.verifyCarIsEnabled();
    }

    @When("^For the Google page, User gets the text of Car element$")
    public void userGetTextCar() {
        //The below function is for web element @FindBy(Google.car);
        google.getTextFromCar();
    }

    @Then("^For the Google page, User verifies Car dropdown is visible$")
    public void verifyCarIsDisplayed() {
        //The below function is for web element @FindBy(Google.car);
        google.verifyCarIsDisplayed();
    }

    @When("^For the Google page, User selects \"(.*)\" from  Car dropdown$")
    public void userSelectFromDropdownCar(String selectValue) {
        google.selectCar(selectValue);
    }

    @Then("^For the Google page, User verifies \"(.*)\" is the text of Car dropdown$")
    public void verifyCarText(String typeText) {
        //The below function is for web element @FindBy(Google.car);
        google.verifyCarText(typeText);
    }

    @When("^For the Google page, User gets \"(.*)\" attribute of Car element$")
    public void userGetsAttributeCar(String attributeValue) {
        //This function is for web element @FindBy(Google.car);
        google.getAttributeFromCar(attributeValue);
    }

    @Then("^For the Google page, User verifies \"(.*)\" is the value for Car$")
    public void userVerifyValueForCar(String valueOfElement) {
        //This function is for web element @FindBy(Google.car);
        google.verifyValueFromCar(valueOfElement);
    }

    @When("^For the Google page, User clicks on Submit button$")
    public void userClicksOnSubmit() {
        google.clickOnSubmit();
    }

    @Then("^For the Google page, User is able to click Submit element$")
    public void verifyUserIsClickableSubmit() {
        google.elementIsClickableSubmit();
    }

    @Then("^For the Google page, User verifies Submit button is visible$")
    public void verifySubmitIsDisplayed() {
        //The below function is for web element @FindBy(Google.Submit);
        google.verifySubmitIsDisplayed();
    }

    @Then("^For the Google page, User verifies \"(.*)\" is the text of Submit button$")
    public void verifySubmitText(String typeText) {
        //The below function is for web element @FindBy(Google.Submit);
        google.verifySubmitText(typeText);
    }

    @When("^For the Google page, User gets \"(.*)\" attribute of Submit element$")
    public void userGetsAttributeSubmit(String attributeValue) {
        //This function is for web element @FindBy(Google.Submit);
        google.getAttributeFromSubmit(attributeValue);
    }

    @Then("^For the Google page, User verifies \"(.*)\" is the value for Submit$")
    public void userVerifyValueForSubmit(String valueOfElement) {
        //This function is for web element @FindBy(Google.Submit);
        google.verifyValueFromSubmit(valueOfElement);
    }

    @When("^For the Google page, User clicks on Html link$")
    public void userClicksOnHtml() {
        google.clickOnHtml();
    }

    @When("^For the Google page, User clicks on Html link and navigates back$")
    public void userClicksOnHtmlAndNavigateBack() {
        google.clickOnHtmlAndNavigateBack();
    }

    @Then("^For the Google page, User is able to click Html element$")
    public void verifyUserIsClickableHtml() {
        google.elementIsClickableHtml();
    }

    @Then("^For the Google page, User verifies Html link is visible$")
    public void verifyHtmlIsDisplayed() {
        //The below function is for web element @FindBy(Google.html);
        google.verifyHtmlIsDisplayed();
    }

    @Then("^For the Google page, User verifies \"(.*)\" is the text of Html link$")
    public void verifyHtmlText(String typeText) {
        //The below function is for web element @FindBy(Google.html);
        google.verifyHtmlText(typeText);
    }

    @When("^For the Google page, User gets \"(.*)\" attribute of Html element$")
    public void userGetsAttributeHtml(String attributeValue) {
        //This function is for web element @FindBy(Google.html);
        google.getAttributeFromHtml(attributeValue);
    }

    @Then("^For the Google page, User verifies \"(.*)\" is the value for Html$")
    public void userVerifyValueForHtml(String valueOfElement) {
        //This function is for web element @FindBy(Google.html);
        google.verifyValueFromHtml(valueOfElement);
    }
}
