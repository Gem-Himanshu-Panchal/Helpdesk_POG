Feature: Test


  Background:
    Given User is on homepage

  Scenario: Verify FirstName value
    When For the Google page, User enters "Ayush" as FirstName input
    Then For the Google page, User verifies the "Ayush" value for FirstName element

  Scenario: Verify FirstName is visible
    When For the Google page, User enters "Ayush" as FirstName input
    Then For the Google page, User verifies FirstName is visible

  @reg
  Scenario: Verify Password value
    When For the Google page, User enters "Ayush" as FirstName input
    Then For the Google page, User verifies the "Wrong" value for FirstName element
    Then For the Google page, User verifies Password is visible
    Then For the Google page, User verifies FirstName is visible


  Scenario: Test for Serachbar element
    When For the Google page, User enters "Ayush" as Serachbar input
    Then For the Google page, User verifies the "Ayush" value for Serachbar element


  Scenario: Test for SerachButton element
    When For the Google page, User clicks on SerachButton button
    Then For the Google page, User is able to click SerachButton element
