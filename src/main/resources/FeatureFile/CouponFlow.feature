@ignore
Feature: Coupon flow functionality
  Scenario : Verify the referral coupon
    Given User is on the home page
    Then User add the car through reg number
    Then User add particular package to cart
    When User move to the cart page
    Then User click on the view more coupon
    Then User enter the coupon code and apply

    Scenario: Verify the coupon from list
      Given User is on the home page
      Then User add the car through reg number
      Then User add particular package to cart
      When User move to the cart page
      Then User click on the view more coupon
      Then User apply the coupon from list





