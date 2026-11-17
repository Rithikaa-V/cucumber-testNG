# GreenKart – Cucumber + Selenium + TestNG

Automated browser tests (BDD style) for the practice shopping site
https://rahulshettyacademy.com/seleniumPractise/

## What you need
- JDK 25 or newer (`java -version`)
- Maven (`mvn -version`)
- Google Chrome (the driver is downloaded automatically by Selenium)

## How to run
```
mvn clean test                                  # run everything
mvn test -Dcucumber.filter.tags="@smoke"        # run only scenarios tagged @smoke
mvn test -Dheadless=true                        # no browser window
mvn test -Dbrowser=firefox                      # chrome (default) | firefox | edge
```
Report after a run: `target/cucumber-reports/cucumber.html` (open it in a browser;
failed scenarios contain a screenshot).

## Folder map
```
testng.xml                     tells TestNG which class to start
src/test/resources/features/   the .feature files (plain English test cases)
src/test/resources/config.properties   website URL, browser, headless, timeout
src/test/java/stepdefinitions/ Java code behind each step
src/test/java/pages/           one class per page (locators + actions)
src/test/java/hooks/Hooks.java start / close the browser around each scenario
src/test/java/utils/           DriverFactory (browser) and ConfigReader (settings)
src/test/java/runners/TestRunner.java   the class TestNG starts
```
Read `PROJECT_EXPLAINED` (the separate document) for the full walkthrough.
