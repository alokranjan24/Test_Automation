package org.testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",
        glue = {"org.stepDefinition"},
        tags = "@SwagLabs",
        plugin = {"pretty", "json:target/cucumber-reports/cucumber.json",
                "junit:target/cucumber-reports/cucumber.xml",
                "html:target/cucumber-reports/cucumber.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        monochrome = true
)

public class JUnitTestRunner {
}
