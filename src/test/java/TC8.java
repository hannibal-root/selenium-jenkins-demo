import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.PrintStream;

import static io.restassured.RestAssured.given;
import static io.restassured.config.RestAssuredConfig.config;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class TC8 extends BaseAPI_Test {

    @BeforeAll
    public static void setup() throws Exception {

        PrintStream logStream =
                new PrintStream(new FileOutputStream("target/api-report.log"));

        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        RestAssured.config = config().logConfig(
                LogConfig.logConfig().defaultStream(logStream)
        );
    }

    // FIXME

    @Test
    public void getUserByUsername() {

        given()
                .filter(new AllureRestAssured())
                .log().all()

                .when()
                .get("/api/v1/user/find")

                .then()
                .log().all()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("userId", notNullValue())
                .body("accountName", equalTo("API-Checking"));
    }

    @Test
    public void getAccounts() {

        given()
                .filter(new AllureRestAssured())
                .log().all()

                .when()
                .post("/api/v1/user/{id}/account")

                .then()
                .log().all()
                .statusCode(200);

                // TODO tároljuk el a `CHECK1` account azonosítóját.
    }

    @Test
    public void updateAccount() {

        given()
                .filter(new AllureRestAssured())
                .log().all()

                .when()
                .put("/api/v1/user/{id}/account")

                .then()
                .log().all()
                .statusCode(200)
                .body("accountName", equalTo("CHECK1_MOD"));

        // TODO nevezzük át a `CHECK1` accountot `CHECK1_MOD` névre.
    }

}