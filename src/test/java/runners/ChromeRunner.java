package runners;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "steps",
        plugin = {"summary", "html:target/cucumber-reports/chrome.html"}
)
public class ChromeRunner extends BaseRunner {

    @Override
    protected String browser() {
        return "chrome";
    }
}