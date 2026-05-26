import io.qameta.allure.*;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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

    public void testGetPost() {

        given()
                .filter(new AllureRestAssured())   // 🔥 request/response capture
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