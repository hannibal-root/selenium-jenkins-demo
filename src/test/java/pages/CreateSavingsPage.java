package pages;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreateSavingsPage extends BasePage{
    static {
        logger = LogManager.getLogger(CreateSavingsPage.class);
    }

    @FindBy(xpath = "//h1[@id='page-title']")
    WebElement pageTitle;

    public CreateSavingsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded(){
        boolean isLoaded = isLoaded(pageTitle);
        logger.trace("isLoaded=" + isLoaded);
        return isLoaded;
    }

}
