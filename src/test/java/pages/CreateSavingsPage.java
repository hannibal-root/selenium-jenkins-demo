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

    @FindBy(id = "Savings")
    WebElement savingRadio;

    @FindBy(id = "Individual")
    WebElement individualRadio;

    @FindBy(id = "name")
    WebElement nameInput;

    @FindBy(id = "openingBalance")
    WebElement  openingBalanceInput;

    @FindBy(id = "newSavingsSubmit")
    WebElement  submitButton;

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

    public ViewSavingsPage createAccount(String accountName) {
        savingRadio.click();
        individualRadio.click();
        nameInput.sendKeys(accountName);
        openingBalanceInput.sendKeys("20000");

        submitButton.click();

        return new ViewSavingsPage(driver);
    }
}
