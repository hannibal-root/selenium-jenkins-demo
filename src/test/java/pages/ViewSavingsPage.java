package pages;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViewSavingsPage extends BasePage{
    static {
        logger = LogManager.getLogger(ViewSavingsPage.class);
    }

    @FindBy(id = "emptyAccounts")
    WebElement noAccountPopup;

    @FindBy(xpath = "//button[@type='button' and @class='btn btn-primary']")
    WebElement continueButton;

    @FindBy(id = "savings-menu")
    WebElement savingMenu;

    @FindBy(id = "new-savings-menu-item")
    WebElement createSavingMenu;

    @FindBy(id = "deposit-menu-item")
    WebElement depositMenu;

    @FindBy(css = "#transactionTable tbody tr")
    List<WebElement> transactionRows;

    public ViewSavingsPage(WebDriver driver) {
        super(driver);
    }

    public void checkingNoAccountsPopup() {
        logger.info("noAccountPopup visibility check started");
        wait.until(ExpectedConditions.visibilityOf(noAccountPopup));
    }

    public CreateSavingsPage clickContinueButton() {
        logger.trace("continueButton.click");
        continueButton.click();

        return new CreateSavingsPage(driver);
    }

    public CreateSavingsPage gotoCreateSavingsPage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("savings-menu")));

        logger.trace("savingMenu.click");
        savingMenu.click();

        logger.trace("createSavingMenu.click");
        createSavingMenu.click();

        return new CreateSavingsPage(driver);
    }

    public DepositPage gotoDepositPage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("deposit-menu-item")));

        logger.trace("depositMenu.click");
        depositMenu.click();

        return new DepositPage(driver);
    }

    public void amountCheck(String amount){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#transactionTable tbody tr")));

        WebElement firstRow = transactionRows.get(0);

        String amountCellText = firstRow.findElements(By.tagName("td")).get(3).getText();

        assertEquals(amount, amountCellText.substring(1, amountCellText.length() - 3));
    }
}
