package org.testRunner;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = "src/test/resources",
        glue = {"org.stepDefinition","org.hooks"},
        tags = "@SwagLabs",
        plugin = {"pretty", "json:target/cucumber-reports/cucumber.json",
                "html:target/cucumber-reports/cucumber.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "rerun:target/rerun-failed.txt"
                },
        monochrome = true
)

public class TestNGTestRunner extends AbstractTestNGCucumberTests {
}