package steps;
import driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Hooks {
    private static final Logger LOG = LogManager.getLogger(Hooks.class);

    @Before
    public void setUp(Scenario scenario) {
        LOG.info("START '{}' on {}", scenario.getName(), DriverManager.getBrowser());
        DriverManager.initDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                attachScreenshot(scenario);
            }
        } finally {
            LOG.info("END '{}' on {} - {}", scenario.getName(), DriverManager.getBrowser(), scenario.getStatus());
            DriverManager.quitDriver();
        }
    }

    private void attachScreenshot(Scenario scenario) {
        WebDriver driver = DriverManager.getDriver();
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", "failure-screenshot");
    }
}