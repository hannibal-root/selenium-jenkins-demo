import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TC13_DepositSavingsAccount_Test extends BaseUI_Test {

    @Test
    public void TC13_DepositSavingsAccount() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        String sav1 =
                "SAV1_" + System.currentTimeMillis();

        String sav2 =
                "SAV2_" + System.currentTimeMillis();

        driver.get(
                "https://eng.digitalbank.masterfield.hu/bank/login"
        );

        // Cookie popup
        try {
            wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.className("cc-nb-okagree")
                    )
            ).click();
        } catch (Exception ignored) {
        }

        // Login
        driver.findElement(By.name("username"))
                .sendKeys("jsmith");

        driver.findElement(By.name("password"))
                .sendKeys("Demo123!");

        driver.findElement(By.id("submit"))
                .click();

        wait.until(
                ExpectedConditions.urlContains("/home")
        );

        // =====================
        // SAV1 létrehozása
        // =====================

        driver.get(
                "https://eng.digitalbank.masterfield.hu/bank/account/savings-add"
        );

        driver.findElement(By.id("Savings"))
                .click();

        driver.findElement(By.id("Individual"))
                .click();

        driver.findElement(By.id("name"))
                .sendKeys(sav1);

        driver.findElement(By.id("openingBalance"))
                .sendKeys("100");

        driver.findElement(By.id("newSavingsSubmit"))
                .click();

        wait.until(
                ExpectedConditions.urlContains("savings-view")
        );

        // =====================
        // SAV2 létrehozása
        // =====================

        driver.get(
                "https://eng.digitalbank.masterfield.hu/bank/account/savings-add"
        );

        driver.findElement(By.id("Savings"))
                .click();

        driver.findElement(By.id("Individual"))
                .click();

        driver.findElement(By.id("name"))
                .sendKeys(sav2);

        driver.findElement(By.id("openingBalance"))
                .sendKeys("100");

        driver.findElement(By.id("newSavingsSubmit"))
                .click();

        wait.until(
                ExpectedConditions.urlContains("savings-view")
        );

        // =====================
        // Deposit oldal
        // =====================

        driver.get(
                "https://eng.digitalbank.masterfield.hu/bank/account/deposit"
        );

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("selectedAccount")
                )
        );

        Select accountSelect =
                new Select(
                        driver.findElement(
                                By.id("selectedAccount")
                        )
                );

        // Debug: nézzük milyen accountok vannak
        for (WebElement option : accountSelect.getOptions()) {

            System.out.println(
                    "ACCOUNT: " + option.getText()
            );
        }

        // Ideiglenesen próbáljuk kiválasztani a SAV1-et
        for (WebElement option : accountSelect.getOptions()) {

            if (option.getText().contains(sav1)) {

                accountSelect.selectByVisibleText(
                        option.getText()
                );

                break;
            }
        }

        driver.findElement(By.id("amount"))
                .sendKeys("100");

        driver.findElement(
                By.cssSelector("button[type='submit']")
        ).click();

        // =====================
        // Ellenőrzés
        // =====================

        WebElement body =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.tagName("body")
                        )
                );

        assertTrue(
                body.getText().contains(sav1),
                "A létrehozott account nem található."
        );
    }
}