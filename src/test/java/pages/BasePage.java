package pages;

import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import testcases.BaseTest;

import java.util.List;

import static testcases.BaseTest.*;

public class BasePage {
    // isLoaded
    // elements
    public static WebDriver driver = BaseTest.driver;

    public BasePage() {
    }

//    public static void assertLoginPageElements(WebElement element){
//        WebElement pageElement = element;
//        Assertions.assertTrue(element.isDisplayed());
//        System.out.println("element: " + element);
//    }
}

