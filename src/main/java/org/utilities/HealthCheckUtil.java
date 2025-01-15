package org.utilities;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class HealthCheckUtil {

    static String filePath = "src/main/java/org/testData/testData.properties";
    private static String BASE_URL = null;

    static {
        try{
            BASE_URL = ReadWritePropertiesFileUtil.getPropertyValue(filePath, "QA2_URL");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String healthCheckDirectoryApp(){
        System.out.println("Running HealthCheckUtil");
        RestAssured.baseURI = BASE_URL;

        Response response = given()
                .when()
                .get("/ZSD-directory-ws/ping")
                .then()
                .statusCode(200).extract().response();

        return response.getBody().asString();
    }

    public static String healthCheckPaymentApp(){
        System.out.println("Running HealthCheckUtil");
        RestAssured.baseURI = BASE_URL;

        Response response = given()
                .when()
                .get("/ZSP-payment-ws/ping")
                .then()
                .statusCode(200).extract().response();

        return response.getBody().asString();
    }
}
