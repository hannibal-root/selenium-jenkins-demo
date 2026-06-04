package pages;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class DepositPage extends BasePage {
    static {
        logger = LogManager.getLogger(DepositPage.class);
    }

    @FindBy(id = "selectedAccount")
    WebElement selectAccount;

    @FindBy(id = "amount")
    WebElement depositInput;

    @FindBy(xpath = "//button[@type='submit' and @class='btn btn-primary btn-sm']")
    WebElement submitButton;

    @FindBy(xpath = "//h1[@id='page-title']")
    WebElement pageTitle;

    public DepositPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        boolean isLoaded = isLoaded(pageTitle) && isLoaded(selectAccount) && isLoaded(depositInput) && isLoaded(submitButton);
        logger.trace("isLoaded=" + isLoaded);
        return isLoaded;
    }

    public ViewSavingsPage createDeposit(String accountName, String amount) {
        Select selectAccountInput = new Select(selectAccount);
        selectAccountInput.selectByVisibleText(accountName + " (Savings)");

        depositInput.sendKeys(amount);

        submitButton.click();

        return new ViewSavingsPage(driver);
    }
}
