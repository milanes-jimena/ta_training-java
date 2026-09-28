package runners;
import driver.DriverManager;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.BeforeClass;

public abstract class BaseRunner extends AbstractTestNGCucumberTests {

    protected abstract String browser();

    @BeforeClass(alwaysRun = true)
    public void selectBrowser() {
        DriverManager.setBrowser(browser());
    }
}