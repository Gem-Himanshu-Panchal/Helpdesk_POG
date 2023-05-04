Feature: Google Activities. This tests Google Activities

  Background:
    Given User is on homepage
    Then User verifies "Google" is the title of the page

  Scenario: Search Serenity Framework
    Then For the Google page, User verifies "Serenity Framework" is the text of SearchInput input
    And For the Google page, User clicks on SearchButton button
    Then User verifies "Serenity Framework - Google Search" is the title of the page
    
  Scenario: Search Serenity Framework in Images
    When For the Google page, User clicks on Images link
    Then User verifies "Google Images" is the title of the page
    When For the Google page, User enters "Serenity Framework" as input for SearchInput
    Then For the Google page, User clicks on SearchButton button
    And User verifies "Serenity Framework - Google Search" is the title of the page
