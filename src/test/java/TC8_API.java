import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TC8_API extends BaseAPI_Test{

    @Test
    public void TC8_API(){
        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + authToken)
                .queryParam("username", "admin@demo.io")
                .queryParam("password", "Demo123!")
                .when()
                .get("/api/v1/user/find");

        response.prettyPrint();

        assertEquals(200, response.getStatusCode());
    }
}
