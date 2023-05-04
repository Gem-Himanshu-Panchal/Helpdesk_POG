Feature: Test

  Background:
    Given User is on homepage
    Then User verifies "Sample HTML Page" is the title of the page

  Scenario: Type
     When For the Google page, User verifies "html" is present in SearchInput element
     When User switches to "d" frame
     When User switches to 2 frame

  @reg
  Scenario: Type 1
    When For the Google page, User selects "1" from SearchInput dropdown
    When For the Google page, User selects "2" from SearchInput dropdown
    When For the Google page, User deselects "1" from SearchInput dropdown
    When For the Google page, User deselects "2" from SearchInput dropdown
     
     

