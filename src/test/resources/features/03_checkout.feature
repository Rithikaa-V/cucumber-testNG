Feature: Checkout
  As a shopper I want to place an order for the products in my cart

  Background:
    Given the user is on the home page
    And the user has added "Beetroot - 1 Kg" to the cart
    And the user opens the cart
    And the user clicks proceed to checkout

  Scenario: Place Order opens the country page
    When the user clicks place order
    Then the country page should be displayed

  Scenario: Terms and conditions must be accepted
    Given the user clicks place order
    When the user clicks the Proceed button
    Then the warning message "Please accept Terms & Conditions - Required" should be displayed

  @smoke
  Scenario: Order is placed successfully
    Given the user clicks place order
    When the user selects the country "India"
    And the user accepts the terms and conditions
    And the user clicks the Proceed button
    Then the order success message should be displayed
    And the user should be redirected to the home page
