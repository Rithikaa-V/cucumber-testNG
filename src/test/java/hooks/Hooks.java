package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.DriverFactory;

/**
 * Hooks run automatically around EVERY scenario.
 *  - @Before : start a fresh browser, so scenarios never depend on each other
 *  - @After  : take a screenshot if the scenario failed, then always close the browser
 */
public class Hooks {

    @Before
    public void setUp(Scenario scenario) {
        System.out.println("Starting scenario: " + scenario.getName());
        DriverFactory.initDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
                // The screenshot appears inside the HTML report
                scenario.attach(screenshot, "image/png", scenario.getName());
            }
        } finally {
            // finally = runs even if taking the screenshot failed, so Chrome never stays open
            DriverFactory.quitDriver();
        }
        System.out.println("Finished scenario: " + scenario.getName() + " -> " + scenario.getStatus());
    }
}
