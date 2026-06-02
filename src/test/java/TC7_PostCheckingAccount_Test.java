
    import io.restassured.http.ContentType;
    import io.restassured.response.Response;
    import org.junit.jupiter.api.Test;

    import static io.restassured.RestAssured.given;

    public class TC7_PostCheckingAccount_Test extends BaseAPI_Test {
// # TC7_API - "Checking" típusú account létrehozásának ellenőrzése API oldalról

        //1. Az `authentication-controller` segítségével authorizáljuk magunkat az igényelt tokennel. (BASE)
        @Test
        public void TC7_PostCheckingAccount_Test(){

           /* 2. Egy `GET` metódus segítségével a `/api/v1/user/find` (`getUserByUsername`)
            végponton keresztül kérdezzük le az általunk létrehozott profil adatait az e-mail cím segítségével,
            és tároljuk el a profilhoz tartozó `ID` értéket, amely a kapott válasz első eleme. */

            Response userResponse = given()
                    .header(AUTH_HEADER, "Bearer " + authToken)
                    .queryParam("username", "QWER456")
                    .when()
                    .get("/api/v1/user/find");

            userResponse.prettyPrint();

            int userId = userResponse.jsonPath().getInt("id");
            System.out.println(userId);


            //3. `POST` metódussal a `/api/v1/user/{id}/account` (`createAccount`) végponton keresztül
            // hozzunk létre egy új, **Checking** típusú accountot az előzőleg eltárolt felhasználói azonosító
            // felhasználásával.
            Response accountResponse = given()
                    .header(AUTH_HEADER, "Bearer " + authToken)
                    .contentType(ContentType.JSON)
                    .body("""
                            {
                                    "accountName": "API-Checking",
                                        "accountTypeCode": "SCK",
                                        "openingDeposit": 999,
                                        "ownerTypeCode": "IND"
                                }
                            """)
                    .when()
                    .post("/api/v1/user/" + userId + "/account");

            accountResponse.prettyPrint();
            System.out.println(accountResponse.statusCode());
            accountResponse.then()
                    .statusCode(200);


        }

    /*








            ### Request Body

```json
    {
        "accountName": "API-Checking",
            "accountTypeCode": "SCK",
            "openingDeposit": 999,
            "ownerTypeCode": "IND"
    }
```

        ## Elvárt eredmény

- A válasz státuszkódja `200`.
            - A létrehozott account neve `API-Checking`.

            --- */
    }


