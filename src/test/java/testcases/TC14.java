package testcases;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class TC14 extends BaseTest {

    WebDriver driver;

    @BeforeEach
    void setup() {
        System.setProperty("webdriver.chrome.driver", TestUtils.chromeDriverPath);
        driver = new ChromeDriver();
        driver.get("https://eng.digitalbank.masterfield.hu/bank/login");
        driver.manage().window().maximize();
    }

    @AfterEach
    void teardown() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

  //  # TC12 - "No Accounts" figyelmeztetés ellenőrzése, ha nincs létrehozva "Savings" típusú account

//## Lépések
//
//1. Navigálás: `https://eng.digitalbank.masterfield.hu/bank/login`
//
//            2. Ellenőrzés, hogy a Login oldal minden eleme betöltődött.
    @Test
    public void assertLoginPage() {
       // acceptCookie();
        WebElement userNameInput = driver.findElement(By.cssSelector("#username"));
        WebElement pwdInput = driver.findElement(By.cssSelector("#password"));
        WebElement submit = driver.findElement(By.cssSelector("#submit"));


        Assertions.assertTrue(userNameInput.isDisplayed());
        Assertions.assertTrue(pwdInput.isDisplayed());
        Assertions.assertTrue(submit.isDisplayed());
    }
    //3. Érvényes, a rendszerben már regisztrált belépési adatok megadása.
    @Test
    public void loginWithValidCredentials() {
        WebElement button = driver.findElement(By.xpath("//*[text() = 'OK']"));
        button.click();
        WebElement userNameInput = driver.findElement(By.cssSelector("#username"));
        WebElement pwdInput = driver.findElement(By.cssSelector("#password"));
        WebElement submit = driver.findElement(By.cssSelector("#submit"));

        userNameInput.clear();
        userNameInput.sendKeys("jsmith");
        pwdInput.clear();
        pwdInput.sendKeys("Demo123!");
        submit.click();
    }
    //4. Ellenőrzés, hogy a Home Page oldal minden eleme betöltődött.
    @Test
    public void assertHomePage() {
        WebElement button = driver.findElement(By.xpath("//*[text() = 'OK']"));
        button.click();
        WebElement userNameInput = driver.findElement(By.cssSelector("#username"));
        WebElement pwdInput = driver.findElement(By.cssSelector("#password"));
        WebElement submit = driver.findElement(By.cssSelector("#submit"));

        userNameInput.clear();
        userNameInput.sendKeys("jsmith");
        pwdInput.clear();
        pwdInput.sendKeys("Demo123!");
        submit.click();

        Assertions.assertEquals("https://eng.digitalbank.masterfield.hu/bank/home", driver.getCurrentUrl());
        WebElement homeMenuItem = driver.findElement(By.cssSelector("#home-menu-item"));
        Assertions.assertTrue(homeMenuItem.isDisplayed());

    }

//5. Hozzunk létre 2 db **Savings** típusú accountot a megadott adatok alapján (pl. `SAV1` és `SAV2`).

    @Test
    public void createNewAccount() {
        WebElement button = driver.findElement(By.xpath("//*[text() = 'OK']"));
        button.click();
        WebElement userNameInput = driver.findElement(By.cssSelector("#username"));
        WebElement pwdInput = driver.findElement(By.cssSelector("#password"));
        WebElement submit = driver.findElement(By.cssSelector("#submit"));

        userNameInput.clear();
        userNameInput.sendKeys("jsmith");
        pwdInput.clear();
        pwdInput.sendKeys("Demo123!");
        submit.click();

        WebElement homeMenuItem = driver.findElement(By.cssSelector("#home-menu-item"));

        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(d -> homeMenuItem.isDisplayed());
        Assertions.assertTrue(homeMenuItem.isDisplayed());

        Assertions.assertEquals("https://eng.digitalbank.masterfield.hu/bank/home", driver.getCurrentUrl());


        WebElement profile = driver.findElement(By.cssSelector("img[src='/bank/images/admin.jpg']"));
        profile.click();

        WebElement deleteDataItem = driver.findElement(By.cssSelector("a[href='/bank/user/delete-data']"));
        deleteDataItem.click();

        WebElement savingsItem = driver.findElement(By.cssSelector("#savings-menu"));
        savingsItem.click();

        WebElement viewSavingsItem = driver.findElement(By.cssSelector("#view-savings-menu-item"));
        viewSavingsItem.click();

        WebElement noAccountsButton = driver.findElement(By.xpath("//*[text() = 'Continue']"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Continue']")));
       // Assertions.assertTrue(noAccountsButton.isDisplayed());
        noAccountsButton.click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#Savings")));
        Assertions.assertEquals("https://eng.digitalbank.masterfield.hu/bank/account/savings-add", driver.getCurrentUrl());
        WebElement savingsRadio = driver.findElement(By.cssSelector("#Savings"));
        Assertions.assertTrue(savingsRadio.isDisplayed());
        savingsRadio.click();

        WebElement individualRadio = driver.findElement(By.cssSelector("#Individual"));
        Assertions.assertTrue(savingsRadio.isDisplayed());
        individualRadio.click();

        WebElement accountNameInput = driver.findElement(By.cssSelector("#name"));
        WebElement balance = driver.findElement(By.cssSelector("#openingBalance"));
        WebElement savingSubmit = driver.findElement(By.cssSelector("#newSavingsSubmit"));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#newSavingsSubmit")));

        accountNameInput.clear();
        userNameInput.sendKeys("SAV1");
        balance.clear();
        pwdInput.sendKeys("25");
        savingSubmit.click();

    }


//
//7. Állítsuk be a transfer paramétereit:
//            - **From Account:** `SAV1`
//            - **To Account:** `SAV2`
//            - **Amount:** `500`
//
//            8. Kattintsunk a **Submit** gombra.
//
//## Elvárt eredmény
//
//- A rendszer sikeresen végrehajtja az átutalást.
//            - A megjelenő **View Savings Accounts** oldalon a megfelelő account sorában az **Amount** oszlop értéke `500`.
//            - A tranzakció sikeres végrehajtásáról visszajelzés jelenik meg.
//```**
@Test
public void newDeposit() {
    WebElement button = driver.findElement(By.xpath("//*[text() = 'OK']"));
    button.click();
    WebElement userNameInput = driver.findElement(By.cssSelector("#username"));
    WebElement pwdInput = driver.findElement(By.cssSelector("#password"));
    WebElement submit = driver.findElement(By.cssSelector("#submit"));

    userNameInput.clear();
    userNameInput.sendKeys("jsmith");
    pwdInput.clear();
    pwdInput.sendKeys("Demo123!");
    submit.click();

    WebElement homeMenuItem = driver.findElement(By.cssSelector("#home-menu-item"));

    Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    wait.until(d -> homeMenuItem.isDisplayed());
    Assertions.assertTrue(homeMenuItem.isDisplayed());

    Assertions.assertEquals("https://eng.digitalbank.masterfield.hu/bank/home", driver.getCurrentUrl());

    driver.get("https://eng.digitalbank.masterfield.hu/bank/account/xfer-between");

    // wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#selectedAccount")));

    WebElement selectAccount = driver.findElement(By.cssSelector("#selectedAccount"));
    selectAccount.click();


    WebElement selectFromAccount = driver.findElement(By.name("fromAccount"));
    Select selectFrom = new Select(selectFromAccount);

    WebElement selectFromElement = driver.findElement(By.cssSelector("option[value=226]"));

    selectFrom.selectByIndex(1);
    Assertions.assertTrue(selectFromElement.isSelected());

    WebElement selectToAccount = driver.findElement(By.name("toAccount"));
    Select selectTo = new Select(selectToAccount);

    WebElement selectToElement = driver.findElement(By.cssSelector("option[value=226]"));

    selectTo.selectByIndex(2);
    Assertions.assertTrue(selectToElement.isSelected());

    WebElement transfer = driver.findElement(By.cssSelector("#amount"));

    transfer.clear();
    transfer.sendKeys("500");

    WebElement submitDeposit = driver.findElement(By.xpath("//*[text() = 'submit']"));;
    submitDeposit.click();
}

}



