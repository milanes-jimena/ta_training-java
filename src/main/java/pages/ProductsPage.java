package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import java.math.BigDecimal;

public class ProductsPage extends BasePage {
    private static final String INVENTORY_ITEM_XPATH =
            "//div[@class='inventory_item'][.//div[normalize-space(text())='%s']]";
    private static final By CART_LINK = By.cssSelector(".shopping_cart_link");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        try {
            waitForUrlContaining("/inventory.html");
            return true;
        } catch (TimeoutException e) {
            log.error("Products page was not displayed. Current URL: {}", driver.getCurrentUrl());
            return false;
        }
    }

    public BigDecimal getProductPrice(String productName) {
        By priceLocator = By.xpath(itemXpath(productName) + "//div[contains(@class,'inventory_item_price')]");
        return parsePrice(getText(priceLocator));
    }

    public ProductsPage addProductToCart(String productName) {
        log.info("Adding '{}' to the cart", productName);
        click(By.xpath(itemXpath(productName) + "//button"));
        return this;
    }

    public CartPage openCart() {
        log.info("Opening the cart");
        click(CART_LINK);
        return new CartPage(driver);
    }

    private static String itemXpath(String productName) {
        return String.format(INVENTORY_ITEM_XPATH, productName);
    }
}