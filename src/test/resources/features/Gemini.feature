@reg
Feature: Test

  Background:
    Given User is on homepage
    Then User verifies "Gemini Solutions | IT Consulting, Product Development" is the title of the page

  Scenario: Click on Contact
    When For the GeminiHomePage page, User clicks on Contact button
    Then User verifies the current URL with "https://geminisolutions.com/contact"
    And User navigates Back to Previous Page
    Then User verifies the current URL with "https://geminisolutions.com/"
   