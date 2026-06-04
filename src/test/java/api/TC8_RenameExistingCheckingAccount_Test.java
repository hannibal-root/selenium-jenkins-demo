package api;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;

public class TC8_RenameExistingCheckingAccount_Test extends BaseAPI_Test {

    /*
    # TC8_API - Létező "Checking" típusú account azonosítása és átnevezésének ellenőrzése
     */

    @Test
    public void TC8_RenameExistingCheckingAccount_Test() {

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
                .log().all();

        String id = response2.jsonPath().get("id").toString();
        System.out.println(id);

        //3. Checking accountok lekérdezése -> GET /api/v1/user/{id}/account
        Response response3 = given()
                .contentType(JSON)
                .header(AUTH_HEADER, "Bearer " + authToken)

                .when()
                .get("/api/v1/user/" + id + "/account");

        response3.prettyPrint();

        //Eltároljuk a `CHECK1` account azonosítóját
        // ...getInt("[0].id");
        int accountId = response3.jsonPath().getInt("find { it.name == '" + "CHECK1_MOD" + "' }.id");
        System.out.println(accountId);

        //4. Nevezzük át a `CHECK1` accountot `CHECK1_MOD` névre PUT /api/v1/account/{id}
        String modName = "CHECK1_MOD";

        Response response4 = given()
                .contentType(JSON)
                .header(AUTH_HEADER, "Bearer " + authToken)
                .queryParam("newName", modName)

                .when()
                .put("/api/v1/account/" + accountId);

        response4.then()
                .statusCode(200)
                .body("name", equalTo(modName))
                .log().all();
    }
}
