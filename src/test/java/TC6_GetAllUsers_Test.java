
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TC6_GetAllUsers_Test extends BaseAPI_Test {

    /**
     * Ez a teszt listázza az összes regisztrált felhasználó adatait.
     * <p>
     * GET /api/v1/users
     * <p>
     * getAllUsers
     */

    @Test
    public void getAllUsers() {

        Response response = given()
                .header("Authorization", "Bearer " + authToken)
                .when()
                .get("/api/v1/users");

        assertEquals(200, response.getStatusCode());

        response.prettyPrint();
    }
}