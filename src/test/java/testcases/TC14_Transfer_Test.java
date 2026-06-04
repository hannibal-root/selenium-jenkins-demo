package testcases;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.*;

public class TC14_Transfer_Test extends BaseTest{
    static {
        logger = LogManager.getLogger(TC14_Transfer_Test.class);
    }

    @Test
    @DisplayName("TC14_Transfer_Test")
    @Tag("TC14")
    public void TC14_Transfertest(TestInfo testInfo) {
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

        logger.info("Navigálás a TransferPage-re");
        TransferPage transferPage = homePage.gotoTransferPage();
        transferPage.isLoaded();

        logger.info("Transfer létrehozása");
        String accountName1 = "SAV1";
        String accountName2 = "SAV2";
        String amount = "500";

        ViewSavingsPage viewSavingsPage = transferPage.createTransfer(accountName1, accountName2, amount);
        viewSavingsPage.amountCheck(amount);

        //A tranzakció sikeres végrehajtásáról visszajelzés jelenik meg. -> Nem jelent meg!
    }
}
