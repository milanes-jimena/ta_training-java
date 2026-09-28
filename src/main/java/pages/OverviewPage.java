package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.math.BigDecimal;

public class OverviewPage extends BasePage {
    private static final By SUBTOTAL_LABEL = By.cssSelector(".summary_subtotal_label");
    private static final By TAX_LABEL = By.cssSelector(".summary_tax_label");
    private static final By TOTAL_LABEL = By.cssSelector(".summary_total_label");
    private static final By FINISH_BUTTON = By.cssSelector("#finish");

    public OverviewPage(WebDriver driver) {
        super(driver);
    }

    public BigDecimal getItemTotal() {
        waitForUrlContaining("/checkout-step-two.html");
        return parsePrice(getText(SUBTOTAL_LABEL));
    }

    public BigDecimal getTax() {
        return parsePrice(getText(TAX_LABEL));
    }

    public BigDecimal getTotal() {
        return parsePrice(getText(TOTAL_LABEL));
    }

    public ConfirmationPage finishCheckout() {
        log.info("Finishing the checkout");
        click(FINISH_BUTTON);
        return new ConfirmationPage(driver);
    }
}