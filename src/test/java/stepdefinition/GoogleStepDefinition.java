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

public class GoogleStepDefinition {

    private GoogleImplementation google = new GoogleImplementation();

    @When("^For the Google page, User clears the FirstName element text$")
    public void userClearFirstName() {
        //The below function is for web element @FindBy(Google.firstName);
        google.clearFirstName();
    }

    @When("^For the Google page, User gets the text of FirstName element$")
    public void userGetTextFirstName() {
        //The below function is for web element @FindBy(Google.firstName);
        google.getTextFromFirstName();
    }

    @When("^For the Google page, User enters \"(.*)\" as FirstName input$")
    public void userEntersAsFirstName(String typeText) {
        google.typeTextIntoFirstName(typeText);
    }

    @Then("^For the Google page, User verifies FirstName is visible$")
    public void verifyFirstNameIsDisplayed() {
        //The below function is for web element @FindBy(Google.firstName);
        google.verifyFirstNameIsDisplayed();
    }

    @Then("^For the Google page, User verifies FirstName \"(.*)\" text$")
    public void verifyFirstNameText(String typeText) {
        //The below function is for web element @FindBy(Google.firstName);
        google.verifyFirstNameText(typeText);
    }

    @When("^For the Google page, User verify the given FirstName element is enabled$")
    public void userIsEnabledFirstName() {
        google.verifyFirstNameIsEnabled();
    }

    @When("^For the Google page, User gets \"(.*)\" attribute of FirstName element$")
    public void userGetsAttributeFirstName(String attributeValue) {
        //This function is for web element @FindBy(Google.firstName);
        google.getAttributeFromFirstName(attributeValue);
    }

    @Then("^For the Google page, User verifies the \"(.*)\" value for FirstName element$")
    public void userVerifyValueForFirstName(String valueOfElement) {
        //This function is for web element @FindBy(Google.firstName);
        google.getValueFromFirstName(valueOfElement);
    }

    @When("^For the Google page, User clicks on Password button$")
    public void userClicksOnPassword() {
        google.clickOnPassword();
    }

    @Then("^For the Google page, User is able to click Password element$")
    public void verifyUserIsClickablePassword() {
        google.elementIsClickablePassword();
    }

    @Then("^For the Google page, User verifies Password is visible$")
    public void verifyPasswordIsDisplayed() {
        //The below function is for web element @FindBy(Google.password);
        google.verifyPasswordIsDisplayed();
    }

    @Then("^For the Google page, User verifies Password \"(.*)\" text$")
    public void verifyPasswordText(String typeText) {
        //The below function is for web element @FindBy(Google.password);
        google.verifyPasswordText(typeText);
    }

    @When("^For the Google page, User gets \"(.*)\" attribute of Password element$")
    public void userGetsAttributePassword(String attributeValue) {
        //This function is for web element @FindBy(Google.password);
        google.getAttributeFromPassword(attributeValue);
    }

    @Then("^For the Google page, User verifies the \"(.*)\" value for Password element$")
    public void userVerifyValueForPassword(String valueOfElement) {
        //This function is for web element @FindBy(Google.password);
        google.getValueFromPassword(valueOfElement);
    }

    @When("^For the Google page, User clicks on ClickItem click$")
    public void userClicksOnClickItem() {
        google.clickOnClickItem();
    }

    @When("^For the Google page, User double click on ClickItem element$")
    public void userDoubleCLickONClickItem() {
        //The below function is for web element @FindBy(Google.clickItem);
        google.doubleClickOnClickItem();
    }

    @When("^For the Google page, User gets the text of ClickItem element$")
    public void userGetTextClickItem() {
        //The below function is for web element @FindBy(Google.clickItem);
        google.getTextFromClickItem();
    }

    @Then("^For the Google page, User is able to click ClickItem element$")
    public void verifyUserIsClickableClickItem() {
        google.elementIsClickableClickItem();
    }

    @When("^For the Google page, User scroll and clicks on ClickItem element$")
    public void userScrollClickOnClickItem() {
        google.scrollClickClickItem();
    }

    @Then("^For the Google page, User verifies ClickItem \"(.*)\" text$")
    public void verifyClickItemText(String typeText) {
        //The below function is for web element @FindBy(Google.clickItem);
        google.verifyClickItemText(typeText);
    }

    @When("^For the Google page, User gets \"(.*)\" attribute of ClickItem element$")
    public void userGetsAttributeClickItem(String attributeValue) {
        //This function is for web element @FindBy(Google.clickItem);
        google.getAttributeFromClickItem(attributeValue);
    }

    @Then("^For the Google page, User verifies ClickItem is visible$")
    public void verifyClickItemIsDisplayed() {
        //The below function is for web element @FindBy(Google.clickItem);
        google.verifyClickItemIsDisplayed();
    }

    @Then("^For the Google page, User is able to click DropdownItem element$")
    public void verifyUserIsClickableDropdownItem() {
        google.elementIsClickableDropdownItem();
    }

    @When("^For the Google page, User gets the text of DropdownItem element$")
    public void userGetTextDropdownItem() {
        //The below function is for web element @FindBy(Google.dropdownItem);
        google.getTextFromDropdownItem();
    }

    @When("^For the Google page, user selects \"(.*)\" from  DropdownItem dropdown$")
    public void userSelectFromDropdownDropdownItem(String selectValue) {
        google.selectDropdownItem(selectValue);
    }

    @Then("^For the Google page, User verifies DropdownItem \"(.*)\" text$")
    public void verifyDropdownItemText(String typeText) {
        //The below function is for web element @FindBy(Google.dropdownItem);
        google.verifyDropdownItemText(typeText);
    }

    @Then("^For the Google page, User verifies DropdownItem is visible$")
    public void verifyDropdownItemIsDisplayed() {
        //The below function is for web element @FindBy(Google.dropdownItem);
        google.verifyDropdownItemIsDisplayed();
    }

    @When("^For the Google page, User gets \"(.*)\" attribute of DropdownItem element$")
    public void userGetsAttributeDropdownItem(String attributeValue) {
        //This function is for web element @FindBy(Google.dropdownItem);
        google.getAttributeFromDropdownItem(attributeValue);
    }

    @Then("^For the Google page, User verifies the \"(.*)\" value for DropdownItem element$")
    public void userVerifyValueForDropdownItem(String valueOfElement) {
        //This function is for web element @FindBy(Google.dropdownItem);
        google.getValueFromDropdownItem(valueOfElement);
    }

    @When("^For the Google page, User clicks on ImageItem image$")
    public void userClicksOnImageItem() {
        google.clickOnImageItem();
    }

    @Then("^For the Google page, User is able to click ImageItem element$")
    public void verifyUserIsClickableImageItem() {
        google.elementIsClickableImageItem();
    }

    @When("^For the Google page, User verify the given ImageItem element is enabled$")
    public void userIsEnabledImageItem() {
        google.verifyImageItemIsEnabled();
    }

    @When("^For the Google page, User uploads image having path \"(.*)\" for ImageItem image$")
    public void userUploadImageImageItem(String filePath) {
        google.uploadFileToImageItem(filePath);
    }

    @Then("^For the Google page, User verifies ImageItem is visible$")
    public void verifyImageItemIsDisplayed() {
        //The below function is for web element @FindBy(Google.imageItem);
        google.verifyImageItemIsDisplayed();
    }

    @Then("^For the Google page, User verifies ImageItem \"(.*)\" text$")
    public void verifyImageItemText(String typeText) {
        //The below function is for web element @FindBy(Google.imageItem);
        google.verifyImageItemText(typeText);
    }

    @Then("^For the Google page, User verifies ImageItem is selected$")
    public void verifyImageItemIsSelected() {
        //The below function is for web element @FindBy(Google.imageItem);
        google.verifyImageItemIsSelected();
    }

    @When("^For the Google page, User gets \"(.*)\" attribute of ImageItem element$")
    public void userGetsAttributeImageItem(String attributeValue) {
        //This function is for web element @FindBy(Google.imageItem);
        google.getAttributeFromImageItem(attributeValue);
    }

    @Then("^For the Google page, User verifies the \"(.*)\" value for ImageItem element$")
    public void userVerifyValueForImageItem(String valueOfElement) {
        //This function is for web element @FindBy(Google.imageItem);
        google.getValueFromImageItem(valueOfElement);
    }

    @When("^For the Google page, User clicks on AItem a$")
    public void userClicksOnAItem() {
        google.clickOnAItem();
    }

    @Then("^For the Google page, User is able to click AItem element$")
    public void verifyUserIsClickableAItem() {
        google.elementIsClickableAItem();
    }

    @When("^For the Google page, User clicks on AItem and navigate back$")
    public void userClicksOnAItemAndNavigateBack() {
        google.clickOnAItemAndNavigateBack();
    }

    @Then("^For the Google page, User verifies AItem is visible$")
    public void verifyAItemIsDisplayed() {
        //The below function is for web element @FindBy(Google.aItem);
        google.verifyAItemIsDisplayed();
    }

    @Then("^For the Google page, User verifies AItem \"(.*)\" text$")
    public void verifyAItemText(String typeText) {
        //The below function is for web element @FindBy(Google.aItem);
        google.verifyAItemText(typeText);
    }

    @When("^For the Google page, User clears the Serachbar element text$")
    public void userClearSerachbar() {
        //The below function is for web element @FindBy(Google.serachbar);
        google.clearSerachbar();
    }

    @When("^For the Google page, User gets the text of Serachbar element$")
    public void userGetTextSerachbar() {
        //The below function is for web element @FindBy(Google.serachbar);
        google.getTextFromSerachbar();
    }

    @When("^For the Google page, User enters \"(.*)\" as Serachbar input$")
    public void userEntersAsSerachbar(String typeText) {
        google.typeTextIntoSerachbar(typeText);
    }

    @Then("^For the Google page, User verifies Serachbar is visible$")
    public void verifySerachbarIsDisplayed() {
        //The below function is for web element @FindBy(Google.serachbar);
        google.verifySerachbarIsDisplayed();
    }

    @Then("^For the Google page, User verifies Serachbar \"(.*)\" text$")
    public void verifySerachbarText(String typeText) {
        //The below function is for web element @FindBy(Google.serachbar);
        google.verifySerachbarText(typeText);
    }

    @When("^For the Google page, User verify the given Serachbar element is enabled$")
    public void userIsEnabledSerachbar() {
        google.verifySerachbarIsEnabled();
    }

    @When("^For the Google page, User gets \"(.*)\" attribute of Serachbar element$")
    public void userGetsAttributeSerachbar(String attributeValue) {
        //This function is for web element @FindBy(Google.serachbar);
        google.getAttributeFromSerachbar(attributeValue);
    }

    @Then("^For the Google page, User verifies the \"(.*)\" value for Serachbar element$")
    public void userVerifyValueForSerachbar(String valueOfElement) {
        //This function is for web element @FindBy(Google.serachbar);
        google.getValueFromSerachbar(valueOfElement);
    }

    @When("^For the Google page, User clicks on SerachButton button$")
    public void userClicksOnSerachButton() {
        google.clickOnSerachButton();
    }

    @Then("^For the Google page, User is able to click SerachButton element$")
    public void verifyUserIsClickableSerachButton() {
        google.elementIsClickableSerachButton();
    }

    @Then("^For the Google page, User verifies SerachButton is visible$")
    public void verifySerachButtonIsDisplayed() {
        //The below function is for web element @FindBy(Google.serachButton);
        google.verifySerachButtonIsDisplayed();
    }

    @Then("^For the Google page, User verifies SerachButton \"(.*)\" text$")
    public void verifySerachButtonText(String typeText) {
        //The below function is for web element @FindBy(Google.serachButton);
        google.verifySerachButtonText(typeText);
    }

    @When("^For the Google page, User gets \"(.*)\" attribute of SerachButton element$")
    public void userGetsAttributeSerachButton(String attributeValue) {
        //This function is for web element @FindBy(Google.serachButton);
        google.getAttributeFromSerachButton(attributeValue);
    }

    @Then("^For the Google page, User verifies the \"(.*)\" value for SerachButton element$")
    public void userVerifyValueForSerachButton(String valueOfElement) {
        //This function is for web element @FindBy(Google.serachButton);
        google.getValueFromSerachButton(valueOfElement);
    }

    @Given("^User navigates to \"(.*)\"$")
    public void navigateTo(String url) {
        google.navigateTo(url);
    }

    @When("^User Navigates Forward to Next Page$")
    public void navigateForward() {
        google.forwardNavigation();
    }

    @When("^User Navigates Back to Previous Page$")
    public void navigateBack() {
        google.backwardNavigation();
    }

    @Given("^User is on homepage$")
    public void openApplication() {
        google.openApplication();
    }
}
