Feature: Cart
  As a shopper I want to see, remove and check out the products in my cart

  # Runs before EVERY scenario in this file, so each scenario starts with one product in the cart
  Background:
    Given the user is on the home page
    And the user has added "Beetroot - 1 Kg" to the cart

  @smoke
  Scenario: Added product is shown in the cart
    When the user opens the cart
    Then the cart should contain "Beetroot - 1 Kg"

  Scenario: Removing the product empties the cart
    When the user opens the cart
    And the user removes the product from the cart
    # The spelling "You cart" is how the website itself spells the message
    Then the cart should display the message "You cart is empty!"

  Scenario: Proceeding to checkout shows the product
    When the user opens the cart
    And the user clicks proceed to checkout
    Then the checkout page should list "Beetroot"
