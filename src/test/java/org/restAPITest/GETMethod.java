package org.restAPITest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GETMethod {

    public static void main(String[] args){

        RestAssured.baseURI = "https://rahulshettyacademy.com";
        RestAssured.useRelaxedHTTPSValidation();

        String response = given().log().all()
                .queryParam("place_id","fd7f2c555389955d47c3366715d51fbf")
                .queryParam("key","qaclick123")
                .when()
                .get("maps/api/place/get/json")
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .extract().response().asString();

        System.out.println("Response: " + response);

        JsonPath js = new JsonPath(response);
        String name = js.getString("name");
        String phoneNum = js.getString("phone_number");
        System.out.println("Name: "+name);
        System.out.println("Phone Number: "+phoneNum);
    }

}
