package org.restAPITest;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

public class PUTMethod {

    public static void main(String[] args){

        RestAssured.baseURI = "https://rahulshettyacademy.com";
        RestAssured.useRelaxedHTTPSValidation();

        String requestBody = "{\n" +
                "\t\t\"place_id\": \"8769b716e752cb18b2f4c60e59aecc7b\",\n" +
                "\t\t\"address\": \"70 winter walk, USA\",\n" +
                "\t\t\"key\": \"qaclick123\"\n" +
                "\t}";

        given().log().all()
                .queryParam("key","qaclick123")
                .header("Content-Type","application/json")
                .body(requestBody)
                .when()
                .put("/maps/api/place/update/json")
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }
}
