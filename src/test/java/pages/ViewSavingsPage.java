package pages;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ViewSavingsPage extends BasePage{
    static {
        logger = LogManager.getLogger(ViewSavingsPage.class);
    }

    @FindBy(id = "emptyAccounts")
    WebElement noAccountPopup;

    @FindBy(xpath = "//button[@type='button' and @class='btn btn-primary']")
    WebElement continueButton;

    public ViewSavingsPage(WebDriver driver) {
        super(driver);
    }

    public void checkingNoAccountsPopup() {
        logger.info("noAccountPopup visibility check started");
        wait.until(ExpectedConditions.visibilityOf(noAccountPopup));
    }

    public void clickContinueButton() {
        logger.trace("continueButton.click");
        continueButton.click();
    }

}
