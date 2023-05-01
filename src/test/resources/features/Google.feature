Feature: Google Activities
  This tests Google Activities

  Background:
    Given For the Google page, User is on homepage

  Scenario Outline: Enter Hello Serenity
    Then For the Google page, User verifies the current URL with "https://www.google.com/"
    Then For the Google page, User enters "Serenity" as SearchBox input
    Examples:
      | Title  |
      | Google |

  Scenario Outline: Launch
    Then Verify "<Title>" of page
    And Verify "<Elements>" on UI
    Examples:
      | Title  |                               Elements                            |
      | Google | Gmail,Images,Sign in,Search bar,Google Search,I'm Feeling Lucky   |

  Scenario Outline: Change Search Language of Google
    Then Verify "<Title>" of page
    And Verify "<Elements>" on UI
    Then Click on "<Language>" that you want to change
    And Verify "<Language>" change
    And Verify text of Google Site changes
    Examples:
      | Title  |                               Elements                            |     Language   |
      | Google | Gmail,Images,Sign in,Search bar,Google Search,I'm Feeling Lucky   |     Hindi      |

  Scenario Outline: Enter "serenity bdd" and click on Search button
    Then Verify "<Title>" of page
    And Verify "<Elements>" on UI
    Then Enter "<text>" in Search box
    And Click on "<buttonName>" button
    And Verify "<newTitle>" of Page
    Examples:
      | Title  |                               Elements                            |     text      |   buttonName      |     newTitle          |
      | Google | Gmail,Images,Sign in,Search bar,Google Search,I'm Feeling Lucky   | serenity bdd  | Google Search |   serenity bdd - Google Search |