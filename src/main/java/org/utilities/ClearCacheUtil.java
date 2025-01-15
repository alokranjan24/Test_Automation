package org.utilities;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class ClearCacheUtil {

    static String filePath = "src/main/java/org/testData/testData.properties";
    private static String BASE_URL = null;

    static {
        try{
            BASE_URL = ReadWritePropertiesFileUtil.getPropertyValue(filePath, "QA2_URL");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String clearCache(){
        System.out.println("Running ClearCacheUtil");
        RestAssured.baseURI = BASE_URL;

        Response response = given()
                .when()
                .get("/P2P-ws/clear-cache")
                .then()
                .statusCode(200).extract().response();

        return response.getBody().asString();
    }
}
