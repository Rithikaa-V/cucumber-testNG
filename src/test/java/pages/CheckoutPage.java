package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * The two pages after the cart window:
 *   1. the order summary page (list of products + "Place Order")
 *   2. the country page (country, terms checkbox, "Proceed")
 */
public class CheckoutPage extends BasePage {

    // Order summary page
    private final By productsArea = By.xpath("//div[@class='products']");
    private final By placeOrderButton = By.xpath("//button[normalize-space()='Place Order']");

    // Country page
    private final By countryDropdown = By.cssSelector("div[class='wrapperTwo'] div select");
    private final By termsCheckbox = By.xpath("//input[@type='checkbox']");
    private final By proceedButton = By.xpath("//button[normalize-space()='Proceed']");
    private final By termsWarning = By.xpath("//b[contains(.,'Terms & Conditions')]");
    private final By successMessage = By.xpath("//*[contains(text(),'order has been placed successfully')]");

    // ---------- Order summary page ----------
    public String getProductsText() {
        return getText(productsArea);
    }

    public void clickPlaceOrder() {
        click(placeOrderButton);
    }

    // ---------- Country page ----------
    public boolean isCountryPageDisplayed() {
        return isDisplayed(countryDropdown);
    }

    public void selectCountry(String countryName) {
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(countryDropdown));
        new Select(dropdown).selectByVisibleText(countryName);
    }

    public void acceptTerms() {
        click(termsCheckbox);
    }

    public void clickProceed() {
        click(proceedButton);
    }

    public String getTermsWarning() {
        return getText(termsWarning);
    }

    public boolean isSuccessMessageDisplayed() {
        return isDisplayed(successMessage);
    }

    /** After a successful order the site goes back to the home page by itself, after a few seconds. */
    public boolean waitForRedirectToHomePage() {
        WebDriverWait longerWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return longerWait.until(ExpectedConditions.urlMatches(".*#/$"));
    }
}
