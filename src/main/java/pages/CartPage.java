package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.List;

public class CartPage extends BasePage {
    private static final By ITEM_NAMES = By.cssSelector(".inventory_item_name");
    private static final By CHECKOUT_BUTTON = By.cssSelector("#checkout");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getItemNames() {
        waitForUrlContaining("/cart.html");
        return getTexts(ITEM_NAMES);
    }

    public CheckoutPage proceedToCheckout() {
        log.info("Proceeding to checkout");
        click(CHECKOUT_BUTTON);
        return new CheckoutPage(driver);
    }
}