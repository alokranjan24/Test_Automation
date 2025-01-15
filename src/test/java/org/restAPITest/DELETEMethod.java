package org.restAPITest;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class DELETEMethod {

    public static void main(String[] args){

        RestAssured.baseURI = "https://rahulshettyacademy.com";
        RestAssured.useRelaxedHTTPSValidation();

        String requestBody = "{\n" +
                "\t\t\"place_id\": \"77e189a869409c0715f987c71b9f6db2\"\n" +
                "\t}";

        given().log().all()
                .queryParam("key","qaclick123")
                .body(requestBody)
                .when()
                .delete("/maps/api/place/delete/json")
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }

}
