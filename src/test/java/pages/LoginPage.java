package pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    static {
        logger = LogManager.getLogger(LoginPage.class);
    }

    @FindBy(id = "username")
    WebElement usernameInput;

    @FindBy(id = "password")
    WebElement passwordInput;

    @FindBy(id = "submit")
    WebElement signInButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded(){
        boolean isLoaded = isLoaded(usernameInput) && isLoaded(passwordInput) && isLoaded(signInButton);
        logger.trace("isLoaded=" + isLoaded);
        return isLoaded;
    }

    @Step("Login funkció")
    public HomePage login(String username, String password){
        logger.info("login() called");

        logger.trace("usernameInput.sendKeys");
        usernameInput.sendKeys(username);

        logger.trace("passwordInput.sendKeys");
        passwordInput.sendKeys(password);

        logger.trace("signInButton.click");
        signInButton.click();

        return new HomePage(driver);
    }
}
