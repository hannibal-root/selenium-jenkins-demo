import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TC7_API_Checking extends BaseAPI_Test{

    @Test
    public void TC7_Api_Checking() {
        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + authToken)
                .queryParam("username", "admin@demo.io")
                .queryParam("password","Demo123!")
                .when()
                .get("/api/v1/user/find");

                response.prettyPrint();

                assertEquals(200, response.getStatusCode());

                int userId = response.jsonPath().getInt("id");
                System.out.println("user Id: " + userId);

       /* Response response2 = RestAssured
                .given()
                .header("Authorization", "Bearer " + authToken)
                .pathParam("id", userId)
                .when()
                .post("/api/v1/user/{id}/account");

                response2.prettyPrint();
                assertEquals(201, response2.getStatusCode());

                System.out.println("Uj Account: " + userId); */


    }
}
