package testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseTest {

    protected static Logger logger = LogManager.getLogger(BaseTest.class);

    protected static WebDriver driver;
    protected static WebDriverWait wait;

    @BeforeAll
    public static void setup() {
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true); //SSL certificate warning figyelmen kívül hagyása
        driver = new ChromeDriver(options);

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String baseURL = "https://eng.digitalbank.masterfield.hu/bank/login";
        driver.get(baseURL);
    }

    @AfterAll
    public static void cleanup() {
        driver.quit();
    }
}
