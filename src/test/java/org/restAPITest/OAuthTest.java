/*----------------------------------------------------------------------------------------------------------------
Authorization Server EndPoint: https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token
HTTP Method : POST
Form parameters :
client_id: 692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com
client_secret: erZOWM9g3UtwNRj340YYaK_W
grant_type: client_credentials
scope: trust
----------------------------------------------------------------------------------------------------------------
GetCourseDetails EndPoint (Secured by OAuth) :
EndPoint: https://rahulshettyacademy.com/oauthapi/getCourseDetails
HTTP Method : GET
Query Parameter : access_token
----------------------------------------------------------------------------------------------------------------*/

package org.restAPITest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;

public class OAuthTest {

    public static void main(String[] args) {
        RestAssured.useRelaxedHTTPSValidation();

        String response1 =
                given()
                        .formParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                        .formParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                        .formParams("grant_type","client_credentials")
                        .formParams("scope","trust")
                        .when().log().all()
                        .post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token")
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .extract().response().asString();

        System.out.println("Response: " + response1);

        JsonPath js = new JsonPath(response1);
        String accessToken = js.getString("access_token");
        System.out.println("Access Token: "+accessToken);

//GetCourseDetails EndPoint (Secured by OAuth) :
        String response2 =
                given()
                        .queryParams("access_token",accessToken)
                        .when().log().all()
                        .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").asString();

        System.out.println("Course Details: " + response2);
    }

}
