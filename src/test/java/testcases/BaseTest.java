package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseTest {

    // driver
    // wait

    public static WebDriver driver;
    public static WebDriverWait wait;

    public BaseTest(WebDriver driver) {
        BaseTest.driver = driver;
        BaseTest.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public BaseTest() {
    }
}
