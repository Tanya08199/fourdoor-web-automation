Feature: Check the header fixed functionality

  Scenario: Validate the header is fixed on home page
    Given User is on the home page
    When User scroll the page
    Then Verify the header is fixed


  Scenario: Validate the header fixed on listing page
    Given User is on the listing page
    When User scroll the page
    Then Verify the header is fixed


  Scenario: Validate the header fixed on PDP page
    Given User is on the PDP page
    When User scroll the page
    Then Verify the header is fixed

  Scenario: Validate the header fixed on cart page
    Given User is on the home page
    Then User add the car through reg number
    Then User add the package to cart
    When User scroll the page
    Then Verify cart header is fixed