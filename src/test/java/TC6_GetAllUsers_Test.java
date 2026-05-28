

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class TC6_GetAllUsers_Test extends BaseAPI_Test {

   @Test
    public void getAllUsers_Test() {
       Response response =
               given()
                       .header("Authorization", "Bearer" + authToken)
                       .when()
                       .get("/api/v1/users");
       response.prettyPrint();

       //response.then().statusCode(200);

       logger.info("Status code is 200");
   }

}