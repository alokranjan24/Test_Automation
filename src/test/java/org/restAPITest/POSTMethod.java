package org.restAPITest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class POSTMethod {

    public static void main (String[] args){
// RestAssured.baseURI = "https://rahulshettyacademy.com";
        RestAssured.useRelaxedHTTPSValidation();

        String response = given().log().all()
                .queryParam("key","qaclick123")
                .header("Content-Type", "application/json")
                .body(Payload.addPlace())
                .when()
                .post("https://rahulshettyacademy.com/maps/api/place/add/json")
                .then()
                .assertThat()
                .statusCode(200)
                .body("scope",equalTo("APP"))
                .header("Server",equalTo("Apache/2.4.52 (Ubuntu)"))
                .extract().response().asString();

        System.out.println("Response: " + response);

        JsonPath json = new JsonPath(response);
        String placeID = json.getString("place_id");

        System.out.println("Place ID: " +placeID);


    }
}
