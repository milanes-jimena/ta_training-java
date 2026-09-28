package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

    private static final By FIRST_NAME_INPUT = By.cssSelector("#first-name");
    private static final By LAST_NAME_INPUT = By.cssSelector("#last-name");
    private static final By ZIP_INPUT = By.cssSelector("#postal-code");
    private static final By CONTINUE_BUTTON = By.cssSelector("#continue");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage fillInformation(String firstName, String lastName, String zip) {
        log.info("Filling checkout information for {} {}", firstName, lastName);
        type(FIRST_NAME_INPUT, firstName);
        type(LAST_NAME_INPUT, lastName);
        type(ZIP_INPUT, zip);
        return this;
    }

    public OverviewPage continueToOverview() {
        click(CONTINUE_BUTTON);
        return new OverviewPage(driver);
    }
}