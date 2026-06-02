
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class TC6_GetAllUsers_Test extends BaseAPI_Test {

    @Test
    public void getAllUsers() {

        Response response =
                given()
                        .header(AUTH_HEADER, "Bearer " + authToken)
                        .when()
                        .get("/api/v1/user");

        response.prettyPrint();
    }
    /**
     * Ez a teszt listázza az összes regisztrált felhasználó adatait.
     * <p>
     * GET /api/v1/users
     * <p>
     * getAllUsers
     */

}