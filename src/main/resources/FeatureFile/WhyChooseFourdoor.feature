@ignore
Feature: Why choose fourdoor on listing page

  Scenario Outline: Verify all the super categories have "Why choose Fourdoor"
    Given User is on the home page
    When User clicks on the super category <category>
    Then User checks for "Why choose Fourdoor"

    Examples:
      | category           |
      | Service & Maintenance   |
      | AC Service & Repair   |
      | General Inspection   |
      | Denting & Painting   |
      | Steering & Suspension   |
      | Engine & Brakes   |
      | Car Detailing   |
      | Car Wash & Spa   |
