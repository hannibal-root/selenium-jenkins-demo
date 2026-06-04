package pages;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class TransferPage extends BasePage {
    static {
        logger = LogManager.getLogger(TransferPage.class);
    }

    @FindBy(id = "fromAccount")
    WebElement selectFromAccount;

    @FindBy(id = "toAccount")
    WebElement selectToAccount;

    @FindBy(id = "amount")
    WebElement transferAmount;

    @FindBy(xpath = "//button[@type='submit' and @class='btn btn-primary btn-sm']")
    WebElement submitButton;

    @FindBy(xpath = "//h1[@id='page-title']")
    WebElement pageTitle;

    public TransferPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        boolean isLoaded = isLoaded(pageTitle) && isLoaded(selectFromAccount) && isLoaded(selectToAccount)
                && isLoaded(transferAmount) && isLoaded(submitButton);
        logger.trace("isLoaded=" + isLoaded);
        return isLoaded;
    }

    public ViewSavingsPage createTransfer(String accountName1, String accountName2, String amount) {
        Select selectFromAccountInput = new Select(selectFromAccount);
        selectFromAccountInput.selectByVisibleText(accountName1 + " (Savings)");

        Select selectToAccountInput = new Select(selectToAccount);
        selectToAccountInput.selectByVisibleText(accountName2 + " (Savings)");

        transferAmount.sendKeys(amount);

        submitButton.click();

        return new ViewSavingsPage(driver);
    }
}
