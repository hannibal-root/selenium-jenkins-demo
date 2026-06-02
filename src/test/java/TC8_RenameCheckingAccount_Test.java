import io.qameta.allure.testfilter.TestPlanV1_0;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class TC8_RenameCheckingAccount_Test extends BaseAPI_Test {

    @Test
    public void TC8_RenameCheckingAccount_Test() {

        Response userResponse = given()
                .header(AUTH_HEADER, "Bearer " + authToken)
                .queryParam("username", "QWER456")
                .when()
                .get("/api/v1/user/find");

        userResponse.prettyPrint();

        int userId = userResponse.jsonPath().getInt("id");
        System.out.println(userId);


        Response accountResponse = given()
                .header(AUTH_HEADER, "Bearer " + authToken)
                .when()
                .get("/api/v1/user/" + userId + "/account");

        accountResponse.prettyPrint();

        int accountId = accountResponse.jsonPath().getInt("[0].id");
        System.out.println(accountId);

       // Response renameResponse = given()


    }
}
    /*
# TC8_API - Létező "Checking" típusú account azonosítása és átnevezésének ellenőrzése

## Lépések

1. Az `authentication-controller` segítségével authorizáljuk magunkat az igényelt tokennel.
    - Felhasználónév: `admin@demo.io`
    - Jelszó: `Demo123!`

2. Egy `GET` metódus segítségével a `/api/v1/user/find` (`getUserByUsername`) végponton keresztül kérdezzük le az általunk létrehozott profil adatait az e-mail cím segítségével, és tároljuk el a profilhoz tartozó `ID` értéket, amely a kapott válasz első eleme.

3. Egy újabb `GET` metódussal a `/api/v1/user/{id}/account` (`getAccounts`) végponton keresztül kérdezzük le a profilhoz tartozó accountokat, majd tároljuk el a `CHECK1` account azonosítóját.

4. Az előzőleg kinyert account azonosítót felhasználva a `/api/v1/account/{id}` (`updateAccount`) végponton keresztül egy `PUT` metódussal nevezzük át a `CHECK1` accountot `CHECK1_MOD` névre.

## Elvárt eredmény

- A válasz státuszkódja `200`.
- Az account neve `CHECK1_MOD`.
*/

