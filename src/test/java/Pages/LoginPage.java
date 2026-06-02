package Pages;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    static {
        logger = LogManager.getLogger(LoginPage.class);
    }

    //felhasználónév mező
    @FindBy(id = "username")
    WebElement usernameInput;

    //jelszó mező
    @FindBy(id = "password")
    WebElement passwordInput;

    //bejelentkezés gomb
    @FindBy(id = "submit")
    WebElement submitButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        boolean loaded = isLoaded(usernameInput)
                && isLoaded(passwordInput)
                && isLoaded(submitButton);

        logger.trace("LoginPage isLoaded=" + loaded);
        return loaded;
    }

    public void login(String username, String password){
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        submitButton.click();
    }
}

