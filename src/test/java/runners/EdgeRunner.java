package runners;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "steps",
        plugin = {"summary", "html:target/cucumber-reports/edge.html"}
)
public class EdgeRunner extends BaseRunner {

    @Override
    protected String browser() {
        return "edge";
    }
}