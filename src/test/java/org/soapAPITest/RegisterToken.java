package org.soapAPITest;

import io.restassured.RestAssured;
import org.utilities.ReadWritePropertiesFileUtil;
import org.utilities.XMLReadWriteUtil;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class RegisterToken {

    public static String IDENTIFIER = null;
    public static String TYPE = null;
    public static String TOKEN_STATUS = null;

    public void registerToken() throws IOException, ParserConfigurationException, SAXException {
        String filePath = "src/main/java/org/testData/testData.properties";
        RestAssured.baseURI = ReadWritePropertiesFileUtil.getPropertyValue(filePath, "QA2_URL");
        File xmlData = new File("src/main/java/org/xmlSoapRequest/registerToken.xml");

        String response = given().log().all()
                .header("Content-Type", "text/xml; charset=utf-8")
                .body(Payload.registerToken())
                .when()
                .post()
                .then().log().all()
                .assertThat()
                .statusCode(200).extract().response().asString();

        //capture xml response and write in a file
        XMLReadWriteUtil xmlReadWriteUtil = new XMLReadWriteUtil();
        xmlReadWriteUtil.writeXMLResponse("registerToken", response);

        //read value from XML response to validate in Oracle Database
        IDENTIFIER = XMLReadWriteUtil.readXMLResponse(response, "normalized-value");
        TYPE = XMLReadWriteUtil.readXMLResponse(response, "type");
        TOKEN_STATUS = XMLReadWriteUtil.readXMLResponse(response, "status");
    }

}
