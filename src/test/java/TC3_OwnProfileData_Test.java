import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TC3_OwnProfileData_Test extends BaseAPI_Test {

    /**
     * Ez a teszt kiírja a profil adatait JSON formátumban,
     * amivel a regisztráció létrejött
     *
     * GET /api/v1/user/find
     *
     * getUserByUsername
     */
    @Test
    public void TC3_OwnProfileData() {

        String userName = "admin@demo.io";

        Response response =

                given()
                        .header(AUTH_HEADER, "Bearer " + authToken)
                        .queryParam("username", userName)

                        .when()
                        .get("/api/v1/user/find");

        response.prettyPrint();

        response.then()
                .statusCode(200);

        logger.info("User profile data successfully loaded");
    }
}