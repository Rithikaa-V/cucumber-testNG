package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * The entry point. TestNG starts this class (see testng.xml) and Cucumber then
 * runs the feature files. Extending AbstractTestNGCucumberTests is what turns
 * every Cucumber scenario into a TestNG test.
 *
 * Run only some scenarios by tag:  mvn test -Dcucumber.filter.tags="@smoke"
 */
@CucumberOptions(
        features = "src/test/resources/features",        // where the .feature files are
        glue = {"stepdefinitions", "hooks"},              // packages that contain steps and hooks
        plugin = {
                "pretty",                                  // readable output in the console
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json"
        },
        monochrome = true                                  // no strange colour characters in the console
)
public class TestRunner extends AbstractTestNGCucumberTests {
    // Scenarios run one after another. To run them in parallel you would
    // override the scenarios() method and mark its @DataProvider as parallel = true.
}
