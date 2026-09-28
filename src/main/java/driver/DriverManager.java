package driver;
import config.ConfigReader;
import org.openqa.selenium.WebDriver;

public final class DriverManager {
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
    private static final ThreadLocal<String> BROWSER = new ThreadLocal<>();

    private DriverManager() {
    }

    public static void setBrowser(String browser) {
        BROWSER.set(browser);
    }

    public static String getBrowser() {
        String browser = BROWSER.get();
        return browser != null ? browser : ConfigReader.get("browser");
    }

    public static void initDriver() {
        DRIVER.set(DriverFactory.create(getBrowser()));
    }

    public static WebDriver getDriver() {
        WebDriver driver = DRIVER.get();
        if (driver == null) {
            throw new IllegalStateException("WebDriver is not initialised for the current thread");
        }
        return driver;
    }

    public static void quitDriver() {
        WebDriver driver = DRIVER.get();
        if (driver != null) {
            driver.quit();
            DRIVER.remove();
        }
    }
}