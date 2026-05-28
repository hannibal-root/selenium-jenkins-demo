import io.qameta.allure.*;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.PrintStream;

import static io.restassured.RestAssured.given;
import static io.restassured.config.RestAssuredConfig.config;
import static org.hamcrest.Matchers.*;

@Epic("API Regression Suite")
@Feature("Posts API")
public class ExampleApiTest {

    @BeforeAll
    public static void setup() throws Exception {

        PrintStream logStream =
                new PrintStream(new FileOutputStream("target/api-report.log"));

        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        RestAssured.config = config().logConfig(
                LogConfig.logConfig().defaultStream(logStream)
        );
    }

    @Test
    @Story("Get single post")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Validates that post with id=1 is returned correctly")
    public void testGetPost() {

        given()
                .filter(new AllureRestAssured())
                .log().all()

                .when()
                .get("/posts/1")

                .then()
                .log().all()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("userId", notNullValue())
                .body("title", not(emptyString()));
    }
}