package pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

/** The small cart window that opens when you click the bag icon in the header. */
public class CartPage extends BasePage {

    private final By cartIcon = By.xpath("//a[@class='cart-icon']//img[contains(@src,'bag.png')]");
    private final By cartItems = By.xpath("//div[@class='cart-preview active']//ul/li");
    private final By removeButton = By.xpath("//div[@class='cart-preview active']//a[@class='product-remove']");
    private final By emptyCartMessage = By.xpath("//div[@class='cart-preview active']//h2");
    private final By proceedToCheckoutButton = By.cssSelector("div[class='cart-preview active'] button[type='button']");

    public void openCart() {
        click(cartIcon);
    }

    public List<String> getCartItemTexts() {
        List<String> texts = new ArrayList<>();
        for (WebElement item : driver.findElements(cartItems)) {
            texts.add(item.getText());
        }
        return texts;
    }

    /** true if one of the items in the cart contains the given product name */
    public boolean isProductInCart(String productName) {
        try {
            wait.until(d -> getCartItemTexts().stream().anyMatch(text -> text.contains(productName)));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void removeProduct() {
        click(removeButton);
    }

    public String getEmptyCartMessage() {
        return getText(emptyCartMessage);
    }

    public void clickProceedToCheckout() {
        click(proceedToCheckoutButton);
    }
}
