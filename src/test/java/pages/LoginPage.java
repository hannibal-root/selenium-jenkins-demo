package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

//    public static WebDriver driver = BasePage.driver;
    public static WebElement userNameInput = driver.findElement(By.cssSelector("#username"));
    public static WebElement pwdInput = driver.findElement(By.cssSelector("#password"));
    public static WebElement submit = driver.findElement(By.cssSelector("#submit"));

    public LoginPage() {
        this.userNameInput = userNameInput;
        this.pwdInput = pwdInput;
        this.submit = submit;
    }

//    public static void acceptCookie(){
//        WebElement button = driver.findElement(By.xpath("//*[text() = 'OK']"));
//        button.click();
//    }


}
