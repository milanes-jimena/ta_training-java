Feature: End-to-end checkout flow

  Background:
    Given I am logged in as "standard_user" with password "secret_sauce"

  @UC1
  Scenario: UC-1 Checkout flow with one item
    When I add the product "Sauce Labs Backpack" to the cart
    And I go to the cart
    Then the cart should contain 1 item
    And I should see "Sauce Labs Backpack" in the cart
    When I proceed to checkout
    And I fill in my information with "John", "Doe", "12345"
    And I continue to the order overview
    And I complete the checkout
    Then I should see the success message "Thank you for your order!"

  @UC2
  Scenario: UC-2 Checkout flow with several items
    When I add the product "Sauce Labs Backpack" to the cart
    And I add the product "Sauce Labs Bike Light" to the cart
    And I go to the cart
    Then the cart should contain 2 items
    And I should see "Sauce Labs Backpack" in the cart
    And I should see "Sauce Labs Bike Light" in the cart
    When I proceed to checkout
    And I fill in my information with "Jane", "Doe", "54321"
    And I continue to the order overview
    Then the final price should equal the sum of both product prices
    When I complete the checkout
    Then I should see the success message "Thank you for your order!"