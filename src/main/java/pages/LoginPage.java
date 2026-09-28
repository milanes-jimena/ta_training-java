package pages;
import config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private static final By USERNAME_INPUT = By.cssSelector("#user-name");
    private static final By PASSWORD_INPUT = By.cssSelector("#password");
    private static final By LOGIN_BUTTON = By.cssSelector("#login-button");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        String url = ConfigReader.get("base.url");
        log.info("Opening {}", url);
        driver.get(url);
        return this;
    }

    public ProductsPage loginAs(String username, String password) {
        log.info("Logging in as '{}'", username);
        type(USERNAME_INPUT, username);
        type(PASSWORD_INPUT, password);
        click(LOGIN_BUTTON);
        return new ProductsPage(driver);
    }
}