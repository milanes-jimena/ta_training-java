package driver;
import config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import java.util.Map;

public final class DriverFactory {

    private static final String HEADLESS_ARGUMENT = "--headless=new";
    private static final String WINDOW_SIZE_ARGUMENT = "--window-size=1920,1080";
    private static final Map<String, Object> BROWSER_PREFERENCES = Map.of(
            "credentials_enable_service", false,
            "profile.password_manager_enabled", false,
            "profile.password_manager_leak_detection", false
    );

    private DriverFactory() {
    }

    public static WebDriver create(String browser) {
        boolean headless = ConfigReader.getBoolean("headless");
        WebDriver driver = switch (browser.toLowerCase()) {
            case "chrome" -> new ChromeDriver(chromeOptions(headless));
            case "edge" -> new EdgeDriver(edgeOptions(headless));
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };
        if (!headless) {
            driver.manage().window().maximize();
        }
        return driver;
    }

    private static ChromeOptions chromeOptions(boolean headless) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito", "--disable-notifications");
        options.setExperimentalOption("prefs", BROWSER_PREFERENCES);
        if (headless) {
            options.addArguments(HEADLESS_ARGUMENT, WINDOW_SIZE_ARGUMENT);
        }
        return options;
    }

    private static EdgeOptions edgeOptions(boolean headless) {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--inprivate", "--disable-notifications");
        options.setExperimentalOption("prefs", BROWSER_PREFERENCES);
        if (headless) {
            options.addArguments(HEADLESS_ARGUMENT, WINDOW_SIZE_ARGUMENT);
        }
        return options;
    }
}