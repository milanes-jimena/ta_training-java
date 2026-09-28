package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage extends BasePage {

    private static final By COMPLETE_HEADER = By.cssSelector(".complete-header");

    public ConfirmationPage(WebDriver driver) {
        super(driver);
    }

    public String getSuccessMessage() {
        return getText(COMPLETE_HEADER);
    }
}