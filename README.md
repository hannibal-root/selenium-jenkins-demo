# TC7_API - "Checking" típusú account létrehozásának ellenőrzése API oldalról

## Lépések

1. Az `authentication-controller` segítségével authorizáljuk magunkat az igényelt tokennel.
    - Felhasználónév: `admin@demo.io`
    - Jelszó: `Demo123!`

2. Egy `GET` metódus segítségével a `/api/v1/user/find` (`getUserByUsername`) végponton keresztül kérdezzük le az általunk létrehozott profil adatait az e-mail cím segítségével, és tároljuk el a profilhoz tartozó `ID` értéket, amely a kapott válasz első eleme.

3. `POST` metódussal a `/api/v1/user/{id}/account` (`createAccount`) végponton keresztül hozzunk létre egy új, **Checking** típusú accountot az előzőleg eltárolt felhasználói azonosító felhasználásával.

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

---

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

# TC12 - "No Accounts" figyelmeztetés ellenőrzése, ha nincs létrehozva "Savings" típusú account

## Lépések

1. Navigálás: `https://eng.digitalbank.masterfield.hu/bank/login`

2. Ellenőrzés, hogy a Login oldal minden eleme betöltődött.

3. Érvényes, a rendszerben már regisztrált belépési adatok megadása.

4. Ellenőrzés, hogy a Home Page oldal minden eleme betöltődött.

5. A jobb felső sarokban található profil ikonra kattintva, a megjelenő menüben kattintsunk a **Delete Data** gombra.

6. Navigálás a **Savings → View Savings** oldalra.

7. Ellenőrzés, hogy a **View Savings** oldalon megjelenik a **"No Accounts"** figyelmeztetés.

8. Kattintás a **Continue** gombra.

## Elvárt eredmény

- A **"No Accounts"** figyelmeztetés megjelenik.
- A **Continue** gombra kattintva a rendszer a **Create Savings** oldalra navigál.

---

# TC13 - A "Deposit" funkció működésének ellenőrzése Savings típusú accountok létrehozásával

## Lépések

1. Navigálás: `https://eng.digitalbank.masterfield.hu/bank/login`

2. Ellenőrzés, hogy a Login oldal minden eleme betöltődött.

3. Érvényes, a rendszerben már regisztrált belépési adatok megadása.

4. Ellenőrzés, hogy a Home Page oldal minden eleme betöltődött.

5. Hozzunk létre 2 db **Savings** típusú accountot a megadott adatok alapján (pl. `SAV1` és `SAV2`).

6. Navigálás a **Deposit** oldalra.

7. Ellenőrzés, hogy a **Deposit** oldal minden eleme megjelenik.

8. A legördülő listából válasszuk ki a **SAV1** nevű Savings accountot.

9. Az **Amount** mezőbe írjunk be `100` értéket.

10. Kattintsunk a **Submit** gombra.

## Elvárt eredmény

- A rendszer sikeresen végrehajtja a befizetést.
- A megjelenő **View Savings Accounts** oldalon a táblázat első sorában az **Amount** oszlop értéke `100`.

---

# TC14 - "Transfer Between Accounts" funkció tesztelése

## Lépések

1. Navigálás: `https://eng.digitalbank.masterfield.hu/bank/login`

2. Ellenőrzés, hogy a Login oldal minden eleme betöltődött.

3. Érvényes, a rendszerben már regisztrált belépési adatok megadása.

4. Ellenőrzés, hogy a Home Page oldal minden eleme betöltődött.

5. Navigálás a **Transfer Between Accounts** oldalra.

6. Ellenőrzés, hogy az oldal minden szükséges eleme megjelenik.

7. Állítsuk be a transfer paramétereit:
    - **From Account:** `SAV1`
    - **To Account:** `SAV2`
    - **Amount:** `500`

8. Kattintsunk a **Submit** gombra.

## Elvárt eredmény

- A rendszer sikeresen végrehajtja az átutalást.
- A megjelenő **View Savings Accounts** oldalon a megfelelő account sorában az **Amount** oszlop értéke `500`.
- A tranzakció sikeres végrehajtásáról visszajelzés jelenik meg.
```**