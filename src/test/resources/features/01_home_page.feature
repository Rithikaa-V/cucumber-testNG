Feature: Home page
  As a shopper I want to see the GreenKart home page and search for products

  Background:
    Given the user is on the home page

  @smoke
  Scenario: Page title is correct
    Then the page title should contain "GreenKart"

  Scenario: Header elements are displayed
    Then the logo should be displayed
    And the search box should be displayed
    And the Top Deals link should be displayed
    And the cart icon should be displayed

  Scenario Outline: Searching shows only the matching product
    When the user searches for "<product>"
    Then only the product "<product>" should be listed

    Examples:
      | product         |
      | Beetroot - 1 Kg |
      | Mushroom - 1 Kg |

  @smoke
  Scenario: Adding a product updates the cart count
    When the user searches for "Beetroot - 1 Kg"
    And the user clicks the increment button
    And the user clicks the add to cart button
    Then the cart should show 1 item
