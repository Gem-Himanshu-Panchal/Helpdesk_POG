Feature: Test

  Background:
    #This simply launches the URL, without any verification. Should the statement be modified?
    Given User is on homepage
    Then User verifies "Sample HTML Page" is the title of the page

#    Scenario: type
#      When For the Sample page, User enters "password" as input for PasswordNEW

  @reg
  Scenario: Verify console error
#    When User verifies there are no console error messages
    When For the Sample page, User enters "Ayush" as input for FirstName
    Then User refreshes page

  Scenario: Verify value entered for Firstname
    When For the Sample page, User enters "fdsdf" as FirstName element and presses enter
    Then User waits for 5 seconds
#    Then For the Sample page, User verifies "Jasleen" is the value for FirstName

  Scenario: Verify First Name field is enabled
    When For the Sample page, User verifies FirstName input is enabled
    Then For the Sample page, User enters "Jasleen" as input for FirstName

  Scenario: Verify First Name field is visible
    When For the Sample page, User verifies FirstName input is visible
    When For the Sample page, User enters "Jasleen" as input for FirstName
    Then For the Sample page, User clears text for FirstName input element

  Scenario: Verify First Name field is empty
    When For the Sample page, User enters "Jasleen" as input for FirstName
    When For the Sample page, User clears text for FirstName input element
    Then For the Sample page, User verifies value for FirstName input element is cleared

  Scenario: Get name attribute for First Name
    When For the Sample page, User enters "Jasleen" as input for FirstName
    Then For the Sample page, User gets "name" attribute of FirstName element

#  Scenario: Verify Birthday value
#    When For the Sample page, User enters "15-07-2000" as input for Birthday
#    Then For the Sample page, User verifies "2000-07-15" is the value for Birthday

  Scenario: Verify Birthday field is enabled
    When For the Sample page, User verifies Birthday input is enabled
    Then For the Sample page, User enters "15-07-2000" as input for Birthday
    And User clic
#
  Scenario: Verify Birthday field is empty
    When For the Sample page, User enters "15-07-2000" as input for Birthday
    When For the Sample page, User clears text for Birthday input element
    Then For the Sample page, User verifies value for Birthday input element is cleared
#
  Scenario: Verify Birthday field is visible
    When For the Sample page, User verifies Birthday input is visible
    Then For the Sample page, User clears text for Birthday input element

  Scenario: Get type attribute of Birthday
    When For the Sample page, User enters "15-07-2000" as input for Birthday
    Then For the Sample page, User gets "type" attribute of Birthday element

  Scenario: Verify value after selecting Javascript radio button
    When For the Sample page, User selects Javascript radio button
    Then For the Sample page, User verifies "JavaScript" is the value for Javascript

  Scenario: Verify JavaScript is selected
    When For the Sample page, User selects Javascript radio button
    Then For the Sample page, User verifies Javascript is selected

  Scenario: Verify JavaScript is enabled
    When For the Sample page, User verifies Javascript radio button is enabled
    Then For the Sample page, User selects Javascript radio button

  Scenario: Verify JavaScript is visible
    When For the Sample page, User verifies Javascript radio button is visible
    Then For the Sample page, User selects Javascript radio button

  Scenario: Get id attribute of JavaScript
    When For the Sample page, User selects Javascript radio button
    Then For the Sample page, User gets "id" attribute of Javascript element

  Scenario: Verify I have a boat option get selected when user clicks on checkbox
    When For the Sample page, User selects Boat checkbox
    Then For the Sample page, User verifies Boat checkbox is selected

  Scenario: Verify checkbox for boat is visible
    When For the Sample page, User selects Boat checkbox
    Then For the Sample page, User verifies Boat checkbox is visible

  Scenario: Get attribute id of boat checkbox
    When For the Sample page, User verifies Boat checkbox is visible
    Then For the Sample page, User gets "id" attribute of Boat element

  Scenario: Verify "I have a boat option" gets deselected when user clicks on checkbox again
    When For the Sample page, User selects Boat checkbox
    Then For the Sample page, User verifies Boat checkbox is selected
    When For the Sample page, User selects Boat checkbox
    Then For the Sample page, User verifies Boat checkbox is not selected

  Scenario: User selects Audi Car
    When For the Sample page, User verifies Car dropdown is visible
    Then For the Sample page, User selects "Audi" from  Car dropdown

  Scenario: User verifies Car is clickable
    When For the Sample page, User is able to click Car element
    Then For the Sample page, User selects "Saab" from  Car dropdown

  Scenario: User verifies Car is visible
    When For the Sample page, User verifies Car dropdown is visible
    Then For the Sample page, User selects "Mercedes" from  Car dropdown

  Scenario: User verifies Car is enabled
    When For the Sample page, User verifies Car dropdown is enabled
    Then For the Sample page, User selects "Mercedes" from  Car dropdown

  Scenario: Get name attribute of Car
    Then For the Sample page, User selects "Mercedes" from  Car dropdown
    Then For the Sample page, User gets "name" attribute of Car element

  Scenario: Verify HTML Link is clickable
    When For the Sample page, User is able to click Html element
    Then For the Sample page, User clicks on Html link
    And User navigates Back to Previous Page

  Scenario: Verify HTML Link is visible
    When For the Sample page, User verifies Html link is visible
    Then For the Sample page, User clicks on Html link
    And User navigates Back to Previous Page

  Scenario: Verify HTML Link text
    When For the Sample page, User verifies "HTML" is the text of Html link
    Then For the Sample page, User clicks on Html link and navigates back

  Scenario: Verify HTML URL
    Then For the Sample page, User clicks on Html link
    Then User verifies "HTML Tutorial" is the title of the page
    And User navigates Back to Previous Page

  Scenario: Verify SubmitButton value
    When For the Sample page, User verifies "Submit" is the value for Submit
    Then For the Sample page, User clicks on Submit button

  Scenario: Verify SubmitButton is clickable
    When For the Sample page, User is able to click Submit element
    Then For the Sample page, User clicks on Submit button

  Scenario: Verify SubmitButton is visible
    When For the Sample page, User verifies Submit button is visible
    Then For the Sample page, User clicks on Submit button

  Scenario: Get value attribute of SubmitButton
    When For the Sample page, User verifies Submit button is visible
    Then For the Sample page, User gets "value" attribute of Submit element
    And For the Sample page, User clicks on Submit button

  Scenario: Verify title of page when user click on submit button
    When For the Sample page, User clicks on Submit button
    Then User verifies "Result Page" is the title of the page

  Scenario: Verify user is able to navigate back and forward
    When For the Sample page, User clicks on Submit button
    Then User verifies "Result Page" is the title of the page
    And User navigates Back to Previous Page
    Then User verifies "Sample HTML Page" is the title of the page
    And User navigates Forward to Next Page
    Then User verifies "Result Page" is the title of the page


