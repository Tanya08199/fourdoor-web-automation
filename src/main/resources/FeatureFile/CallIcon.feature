Feature: Call icon on different pages

  Scenario: Verify the call icon on home page
    Given User is on the home page fourddor
    Then Check the call icon visibility
    When Click on the call icon


  Scenario: Verify the call icon on listing page
    Given User is on the listing page fourdoor
    Then Check the call icon visibility
    When Click on the call icon


  Scenario: Verify the call icon on PDP page
    Given User is on the PDP page fourdoor
    Then Check the call icon visibility
    When Click on the call icon

  Scenario: Verify the call icon on help page
    Given User click on the help section
    Then User is on the help page
    Then Check the call icon visibility
    When Click on the call icon


