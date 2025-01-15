package org.testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "@target/rerun-failed.txt",
        glue = {"org.stepDefinition"},
        plugin = {"pretty", "html:target/cucumber-reports/cucumber.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        monochrome = true
)

public class RerunFailedScenarios extends AbstractTestNGCucumberTests {
}
