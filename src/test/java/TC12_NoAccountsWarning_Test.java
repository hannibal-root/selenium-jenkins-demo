import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TC12_NoAccountsWarning_Test extends BaseUI_Test {

    @Test
    public void TC12_NoAccountsWarning() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        // Login oldal
        driver.get("https://eng.digitalbank.masterfield.hu/bank/login");

        // Cookie elfogadás
        try {
            wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.className("cc-nb-okagree")
                    )
            ).click();
        } catch (Exception ignored) {
        }

        // Bejelentkezés
        driver.findElement(By.name("username"))
                .sendKeys("jsmith");

        driver.findElement(By.name("password"))
                .sendKeys("Demo123!");

        driver.findElement(By.id("submit"))
                .click();

        // Home oldal betöltődésének megvárása
        wait.until(
                ExpectedConditions.urlContains("/home")
        );

        // Delete Data oldal közvetlen megnyitása
        driver.get(
                "https://eng.digitalbank.masterfield.hu/bank/user/delete-data"
        );

        // Savings → View Savings oldal
        driver.get(
                "https://eng.digitalbank.masterfield.hu/bank/account/savings-view"
        );

        // No Accounts popup ellenőrzése
        WebElement popup =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.id("emptyAccounts")
                        )
                );

        assertTrue(
                popup.isDisplayed(),
                "A No Accounts figyelmeztetés nem jelent meg!"
        );

        assertTrue(
                popup.getText().contains("No Accounts"),
                "A popup nem tartalmazza a 'No Accounts' szöveget!"
        );

        // Continue gomb
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[text()='Continue']")
                )
        ).click();

        // Create Savings oldal ellenőrzése
        wait.until(
                ExpectedConditions.urlContains("savings-add")
        );

        assertTrue(
                driver.getCurrentUrl().contains("savings-add"),
                "A rendszer nem navigált a Create Savings oldalra!"
        );
    }
}