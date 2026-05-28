import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TC3_OwnProfileData_Test extends BaseAPI_Test {

    /**
     * Ez a teszt kiírja a profil adatait JSON formátumban, amivel a regisztráció létrejött
     * <p>
     * GET /api/v1/user/find
     * <p>
     * getUserByUsername
     */
    @Test
    public void TC3_OwnProfileData() {
        /* A globalTestData.properties fájlból kiolvassuk az e-mail címet.*/
        // String userName = globalTestData.getProperty(Consts.REG_EMAILADDRESS);

        /* A regisztrációs adatoknál megadott e-mail címet felhasználva megkeressük a profilt és kiíratjuk vele a profil adatokat.*/
        Response response = given()
                .header(AUTH_HEADER, "Bearer " + authToken)
                .queryParam("username", "jsmith")
                .when()
                .get("/api/v1/user/find");
        logger.trace("Request: {}", given().log().all().header(AUTH_HEADER, "Bearer " + authToken)
                .queryParam("username", "jsmith")
                .when().get("/api/v1/user/find").prettyPrint());

        response.prettyPrint();
        response.then()
                .statusCode(200)
                .body("userProfile.emailAddress", equalTo("jsmith"));
    }
}