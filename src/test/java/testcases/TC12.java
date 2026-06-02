package testcases;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class TC12 extends BaseTest {

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

// 5. A jobb felső sarokban található profil ikonra kattintva, a megjelenő menüben kattintsunk a **Delete Data** gombra.
@Test
public void selectDeleteData() {
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

    WebElement profile = driver.findElement(By.cssSelector("img[src='/bank/images/admin.jpg']"));
    profile.click();

    WebElement deleteDataItem = driver.findElement(By.cssSelector("a[href='/bank/user/delete-data']"));
    deleteDataItem.click();

}

//6. Navigálás a **Savings → View Savings** oldalra.
    //
//7. Ellenőrzés, hogy a **View Savings** oldalon megjelenik a **"No Accounts"** figyelmeztetés.
//
//8. Kattintás a **Continue** gombra.
//## Elvárt eredmény
//
//- A **"No Accounts"** figyelmeztetés megjelenik.
//            - A **Continue** gombra kattintva a rendszer a **Create Savings** oldalra navigál.

    @Test
    public void savingsTest() {
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

        WebElement profile = driver.findElement(By.cssSelector("img[src='/bank/images/admin.jpg']"));
        profile.click();

        WebElement deleteDataItem = driver.findElement(By.cssSelector("a[href='/bank/user/delete-data']"));
        deleteDataItem.click();

        WebElement savingsItem = driver.findElement(By.cssSelector("#savings-menu"));
        savingsItem.click();

        WebElement viewSavingsItem = driver.findElement(By.cssSelector("#view-savings-menu-item"));
        viewSavingsItem.click();

        WebElement noAccountsButton = driver.findElement(By.xpath("//*[text() = 'Continue']"));
        Assertions.assertTrue(noAccountsButton.isDisplayed());
        noAccountsButton.click();

        Assertions.assertEquals("https://eng.digitalbank.masterfield.hu/bank/account/savings-add", driver.getCurrentUrl());
        WebElement savingsRadio = driver.findElement(By.cssSelector("#Savings"));
        Assertions.assertTrue(savingsRadio.isDisplayed());

    }




}



