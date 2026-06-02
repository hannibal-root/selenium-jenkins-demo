package Testcases;

import Pages.HomePage;
import Pages.LoginPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TC12 extends BaseTest{
    @Test
    public void NoAccount() {
        LoginPage loginPage = new LoginPage(driver);
            driver.get("https://eng.digitalbank.masterfield.hu/bank/login");
            assertTrue(loginPage.isLoaded(), "A login nem töltődött be");
            loginPage.login("admin@demo.io", "Demo123!");

        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.isLoaded(), "A Home Page nem töltődött be");
        homePage.navigateViewSavings();
        }

    }
