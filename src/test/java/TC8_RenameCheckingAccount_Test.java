import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TC8_RenameCheckingAccount_Test extends BaseAPI_Test {

    @Test
    public void TC8_RenameCheckingAccount() {

        Integer accountId =
                given()
                        .header(AUTH_HEADER, "Bearer " + authToken)
                        .when()
                        .get("/api/v1/user/4/account")
                        .then()
                        .statusCode(200)
                        .extract()
                        .jsonPath()
                        .getInt("[0].id");

        Response response =
                given()
                        .header(AUTH_HEADER, "Bearer " + authToken)
                        .queryParam("newName", "CHECK1_MOD")
                        .when()
                        .put("/api/v1/account/" + accountId);

        response.prettyPrint();

        response.then()
                .statusCode(200)
                .body("name", equalTo("CHECK1_MOD"));
    }
}