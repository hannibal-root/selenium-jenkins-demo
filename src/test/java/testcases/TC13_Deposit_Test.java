package testcases;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.*;

public class TC13_Deposit_Test extends BaseTest{
    static {
        logger = LogManager.getLogger(TC13_Deposit_Test.class);
    }

    @Test
    @DisplayName("TC13_Deposit_Test")
    @Tag("TC13")
    public void TC13_Deposittest(TestInfo testInfo) {
        logger.info(testInfo + " started");

        LoginPage loginPage = new LoginPage(driver);

        logger.info("Cookie popup elfogadása");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.freeprivacypolicy-com---nb-interstitial-overlay")))
                .isDisplayed();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='cc-nb-okagree']")))
                .click();

        logger.info("Login folyamat megkezdése");
        loginPage.isLoaded();

        String username = "jsmith";
        String password = "Demo123!";

        HomePage homePage = loginPage.login(username, password);
        homePage.isLoaded();

        logger.info("Navigálás a CreateSavingPage-re");
        CreateSavingsPage createSavingsPage = homePage.gotoCreateSavings();


        logger.info("Savings accountok létrehozása");
        String accountName1 = "SAV1";
        String accountName2 = "SAV2";
        String amount = "100";

        logger.info("Első savings account létrehozása: {}", accountName1);
        ViewSavingsPage viewSavingsPage = createSavingsPage.createAccount(accountName1);

        CreateSavingsPage createSavingsPage1 = viewSavingsPage.gotoCreateSavingsPage();

        logger.info("Második savings account létrehozása: {}", accountName2);
        ViewSavingsPage viewSavingsPage1 = createSavingsPage1.createAccount(accountName2);

        logger.info("Navigálás a DepositPage-re");
        DepositPage depositPage = viewSavingsPage1.gotoDepositPage();
        depositPage.isLoaded();

        logger.info("Deposit létrehozása és validálása");
        ViewSavingsPage viewSavingsPage2 = depositPage.createDeposit(accountName1, amount);
        viewSavingsPage2.amountCheck(amount);
    }
}
