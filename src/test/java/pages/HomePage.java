package pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.ConfigReader;

/** The GreenKart home page: header, search box, product list and the cart count. */
public class HomePage extends BasePage {

    // ---------- Locators (all the places where the website's HTML is described) ----------
    private final By logo = By.cssSelector(".brand.greenLogo");
    private final By searchForm = By.cssSelector(".search-form");
    private final By searchBox = By.cssSelector("input[placeholder='Search for Vegetables and Fruits']");
    private final By searchButton = By.cssSelector("button[type='submit']");
    private final By topDealsLink = By.xpath("//a[normalize-space()='Top Deals']");
    private final By cartIcon = By.cssSelector(".cart-icon");
    private final By productNames = By.xpath("//div[@class='product']//h4[@class='product-name']");
    private final By incrementButton = By.xpath("//a[@class='increment']");
    private final By addToCartButton = By.xpath("//button[normalize-space()='ADD TO CART']");
    // The "No. of items" number shown in the header
    private final By cartItemCount = By.xpath("//tbody/tr[1]/td[3]");

    // ---------- Actions ----------
    public void open() {
        driver.get(ConfigReader.get("baseUrl"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
    }

    public boolean isLogoDisplayed() {
        return isDisplayed(logo);
    }

    public boolean isSearchBarDisplayed() {
        return isDisplayed(searchForm);
    }

    public boolean isTopDealsLinkDisplayed() {
        return isDisplayed(topDealsLink);
    }

    public boolean isCartIconDisplayed() {
        return isDisplayed(cartIcon);
    }

    public void searchFor(String productName) {
        type(searchBox, productName);
        click(searchButton);
    }

    /** Names of the products that are currently shown on the page. */
    public List<String> getProductNames() {
        List<String> names = new ArrayList<>();
        for (WebElement element : driver.findElements(productNames)) {
            names.add(element.getText().trim());
        }
        return names;
    }

    /**
     * The product list refreshes a moment after searching, so wait until
     * exactly one product is left and return its name.
     */
    public String waitForSingleProduct() {
        wait.until(d -> getProductNames().size() == 1);
        return getProductNames().get(0);
    }

    public void clickIncrement() {
        click(incrementButton);
    }

    public void clickAddToCart() {
        click(addToCartButton);
    }

    /** Waits until the header shows the expected number of items. */
    public void waitForCartItemCount(int expectedCount) {
        wait.until(ExpectedConditions.textToBe(cartItemCount, String.valueOf(expectedCount)));
    }

    public int getCartItemCount() {
        return Integer.parseInt(getText(cartItemCount));
    }
}
