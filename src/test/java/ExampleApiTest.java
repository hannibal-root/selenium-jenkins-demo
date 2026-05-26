import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ExampleApiTest {

    @Test
    public void testApiWithReport() throws FileNotFoundException {

        PrintStream logStream = new PrintStream(
                new FileOutputStream("target/api-report.log")
        );

        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        given()
                .filter(new RequestLoggingFilter(logStream))
                .filter(new ResponseLoggingFilter(logStream))
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1));
    }
}