package org.soapAPITest;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.utilities.ReadWritePropertiesFileUtil;
import org.utilities.XMLReadWriteUtil;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class GetTokenStatus {

    public void getTokenStatus() throws IOException, ParserConfigurationException, SAXException {
        String filePath = "src/main/java/org/testData/testData.properties";
        RestAssured.baseURI = ReadWritePropertiesFileUtil.getPropertyValue(filePath, "QA2_URL");
        File xmlData = new File("src/main/java/org/xmlSoapRequest/registerToken.xml");

        String response = given().log().all()
                .header("Content-Type", "text/xml; charset=utf-8")
                .body(xmlData)
                .when()
                .post()
                .then().log().all()
                .assertThat()
                .statusCode(200).extract().response().asString();

        //capture xml response and write in a file
        XMLReadWriteUtil xmlReadWriteUtil = new XMLReadWriteUtil();
        xmlReadWriteUtil.writeXMLResponse("getTokenStatus", response);

        //read value from xml file
        String actualRequestID = XMLReadWriteUtil.readXMLResponse(response,"request-id");
        XMLReadWriteUtil.readXMLResponse(response, "status");

        //assert the expected and actual value
        String expectedRequestID = "susmitacustomertest";
        Assert.assertEquals(expectedRequestID, actualRequestID, "Request ID is as expected");
    }
}
