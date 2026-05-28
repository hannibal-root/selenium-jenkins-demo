
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class TC6_GetAllUsers_Test extends BaseAPI_Test {

    /**
     * Ez a teszt listázza az összes regisztrált felhasználó adatait.
     * <p>
     * GET /api/v1/users
     * <p>
     * getAllUsers
     */

    @Test
    @Story("Get all user data")
    @Description("...")
    public void testGetUsers() {

        Response response =given()
                .contentType(JSON)
                .header(AUTH_HEADER, "Bearer " + authToken)

                .when()
                .get("/api/v1/users");

                response.prettyPrint();

                response.then()
                .statusCode(200)
                .log().all();
    }
}