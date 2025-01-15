package org.stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.hooks.RetryAnalyzer;
import org.soapAPITest.GetTokenStatus;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class GetTokenStatus_StepDefinition {

    GetTokenStatus getToken;

    public GetTokenStatus_StepDefinition(){
        getToken = new GetTokenStatus();
    }

    @Step("Given Customer enter Token details")
    @Given("Customer Enter Token Details")
    public void customer_enter_token_details() {
        Allure.step("Enter Token details");
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Step("When participant call Get Token status")
    @When("Participant Call Get Token Status")
    public void participant_call_get_token_status() throws IOException, ParserConfigurationException, SAXException {
        Allure.step("Participant Call Get Token Status");
        getToken.getTokenStatus();
    }

    @Step("Then Shared Directory Return the Token Status")
    @Then("Shared Directory Return the Token Status")
    public void shared_directory_return_the_token_status() {
        Allure.step("Shared Directory Return the Token Status");
    }

}
