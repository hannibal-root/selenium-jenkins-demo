import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TC7_CreateCheckingAccount_Test extends BaseAPI_Test {

    @Test
    public void renameAccount() {

        Response response =
                given()
                        .header(AUTH_HEADER, "Bearer " + authToken)
                        .contentType("application/json")
                        .body("""
                            {
                              "name": "CHECK1_MOD"
                            }
                            """)
                        .when()
                        .put("/api/v1/account/195");

        response.prettyPrint();
    }

    @Test
    public void renameAccount2() {

        Response response =
                given()
                        .header(AUTH_HEADER, "Bearer " + authToken)
                        .queryParam("newName", "CHECK1_MOD")
                        .when()
                        .put("/api/v1/account/195");

        response.prettyPrint();

        response.then()
                .statusCode(200);
    }

    @Test
    public void getAccounts() {

        Response response =
                given()
                        .header(AUTH_HEADER, "Bearer " + authToken)
                        .when()
                        .get("/api/v1/user/4/account");

        response.prettyPrint();
    }

    @Test
    public void TC7_CreateCheckingAccount() {

        Integer userId =
                given()
                        .header(AUTH_HEADER, "Bearer " + authToken)
                        .queryParam("username", "admin@demo.io")
                        .when()
                        .get("/api/v1/user/find")
                        .then()
                        .statusCode(200)
                        .extract()
                        .jsonPath()
                        .getInt("id");

        String body = """
                {
                  "accountName": "API-Checking",
                  "accountTypeCode": "SCK",
                  "openingDeposit": 999,
                  "ownerTypeCode": "IND"
                }
                """;

        Response response =
                given()
                        .header(AUTH_HEADER, "Bearer " + authToken)
                        .contentType("application/json")
                        .body(body)
                        .when()
                        .post("/api/v1/user/" + userId + "/account");

        response.prettyPrint();

        response.then()
                .statusCode(200)
                .body("name", equalTo("API-Checking"));
    }
}
