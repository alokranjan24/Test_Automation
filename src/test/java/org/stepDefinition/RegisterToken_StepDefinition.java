package org.stepDefinition;

import com.jcraft.jsch.JSchException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.soapAPITest.RegisterToken;
import org.testng.Assert;
import org.utilities.CitrixLogValidationUtil;
import org.utilities.OracleJDBCUtil;
import org.utilities.ReadWritePropertiesFileUtil;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Locale;

import static org.soapAPITest.RegisterToken.*;

public class RegisterToken_StepDefinition {

    RegisterToken registerToken;

    public RegisterToken_StepDefinition(){
        registerToken = new RegisterToken();
    }

    @Step("Given Customer Enter Register Token Details")
    @Given("Customer Enter Register Token Details")
    public void customer_enter_register_token_details() {
        Allure.step("Customer Enter Register Token Details");
    }

    @Step("When Participant Call Register Token")
    @When("Participant Call Register Token")
    public void participant_call_register_token() throws IOException, ParserConfigurationException, SAXException {
        Allure.step("Participant Call Register Token");
        registerToken.registerToken();
    }

    @Step("Then Shared Directory Return the Token Status a Active with Token details")
    @Then("Shared Directory Return the Token Status a Active with Token details")
    public void shared_directory_return_the_token_status_a_active_with_token_details() {
        Allure.step("Shared Directory Return the Token Status a Active with Token details");
    }

    @Step("Then User verifies token status in database")
    @Then("User verifies token status in database")
    public void user_verifies_token_status_in_database() throws IOException {
        Allure.step("User verifies token status in database");
        String filePath = "src/main/java/org/testData/database.properties";
        String sqlQuery = ReadWritePropertiesFileUtil.getPropertyValue(filePath, "SQLQuery_RegisterToken");
        String db_identifier = OracleJDBCUtil.executeFetchValueFromDB(sqlQuery,"IDENTIFIER");
        String sb_type = OracleJDBCUtil.executeFetchValueFromDB(sqlQuery,"TYPE");
        String db_token_status = OracleJDBCUtil.executeFetchValueFromDB(sqlQuery,"STATUS");

        Assert.assertEquals(IDENTIFIER, db_identifier.toLowerCase(), "IDENTIFIER as Expected: " + IDENTIFIER);
        Assert.assertEquals(TYPE, sb_type.toLowerCase(), "TYPE as Expected: " + TYPE);
        Assert.assertEquals(TOKEN_STATUS, db_token_status.toLowerCase(), "TOKEN_STATUS as Expected: " + TOKEN_STATUS);
    }

    @Step("Then User verifies Citrix Server logs")
    @Then("User verifies Citrix Server logs")
    public void user_verifies_citrix_server_logs() throws JSchException, IOException {
        Allure.step("User verifies Citrix Server logs");
        CitrixLogValidationUtil.validateLogs();
    }

}
