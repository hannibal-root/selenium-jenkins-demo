import io.qameta.allure.*;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@Epic("API Regression Suite")
@Feature("Posts API")

public class ExampleApiTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    @Story("Get single post")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Validates that post with id=1 is returned correctly")

    public void testGetPost() throws FileNotFoundException {

        PrintStream logStream = new PrintStream(
                new FileOutputStream("target/api-report.log")
        );

        given()
                .filter(new AllureRestAssured())
                .log().all()
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("userId", notNullValue())
                .body("title", not(emptyString()));
    }
}