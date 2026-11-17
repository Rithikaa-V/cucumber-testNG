package stepdefinitions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CheckoutPage;

/** Steps for the order summary page and the country page. */
public class CheckoutSteps {

    private final CheckoutPage checkoutPage = new CheckoutPage();

    @When("the user clicks place order")
    public void the_user_clicks_place_order() {
        checkoutPage.clickPlaceOrder();
    }

    @Then("the country page should be displayed")
    public void the_country_page_should_be_displayed() {
        assertTrue(checkoutPage.isCountryPageDisplayed(), "Country page is not displayed");
    }

    @When("the user selects the country {string}")
    public void the_user_selects_the_country(String country) {
        checkoutPage.selectCountry(country);
    }

    @When("the user accepts the terms and conditions")
    public void the_user_accepts_the_terms_and_conditions() {
        checkoutPage.acceptTerms();
    }

    @When("the user clicks the Proceed button")
    public void the_user_clicks_the_proceed_button() {
        checkoutPage.clickProceed();
    }

    @Then("the warning message {string} should be displayed")
    public void the_warning_message_should_be_displayed(String expectedMessage) {
        assertEquals(checkoutPage.getTermsWarning(), expectedMessage, "Wrong warning message");
    }

    @Then("the order success message should be displayed")
    public void the_order_success_message_should_be_displayed() {
        assertTrue(checkoutPage.isSuccessMessageDisplayed(), "Order success message is not displayed");
    }

    @Then("the user should be redirected to the home page")
    public void the_user_should_be_redirected_to_the_home_page() {
        assertTrue(checkoutPage.waitForRedirectToHomePage(), "User was not redirected to the home page");
    }
}
