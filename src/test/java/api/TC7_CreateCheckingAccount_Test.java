package api;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;

public class TC7_CreateCheckingAccount_Test extends BaseAPI_Test {

    /*
    # TC7_API - "Checking" típusú account létrehozásának ellenőrzése API oldalról
     */

    @Test
    public void TC7_CreateCheckingAccount_Test() {

        String username = "admin@demo.io";
        String password = "Demo123!";

        //1. authorizáljuk magunkat -> /api/v1/auth
        Response response1 = given()
                .contentType(JSON)
                .queryParam("username", username)
                .queryParam("password", password)

                .when()
                .post("/api/v1/auth");

        response1.prettyPrint();
        authToken = response1.jsonPath().get("authToken").toString();

        //2. userünk lekérdezése -> GET /api/v1/user/find
        Response response2 = given()
                .contentType(JSON)
                .header(AUTH_HEADER, "Bearer " + authToken)
                .queryParam("username", username)

                .when()
                .get("/api/v1/user/find");

        response2.then()
                .statusCode(200)
                .body("username", equalTo(username))
                .log().all();

        int id = response2.jsonPath().get("id");
        System.out.println(id);

        //int userId = findResponse.path("id");

        //3. Checking account létrehozása -> POST /api/v1/user/{id}/account

        //Map<String, Object> accountRequest = new HashMap<>();
        //accountRequest.put("accountName", "API-Checking");
        //accountRequest.put("accountTypeCode", "SCK");
        //accountRequest.put("openingDeposit", 999);
        //accountRequest.put("ownerTypeCode", "IND");

        Response response3 = given()
                .contentType(JSON)
                .header(AUTH_HEADER, "Bearer " + authToken)
                .body("""
                        {
                          "accountName": "API-Checking",
                          "accountTypeCode": "SCK",
                          "openingDeposit": 999,
                          "ownerTypeCode": "IND"
                        }
                        """)

                .when()
                .post("/api/v1/user/" + id + "/account");

        response3.then()
                .statusCode(200)
                .body("name", equalTo("API-Checking"))
                .log().all();
    }

}
