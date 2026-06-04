package pages;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePage extends BasePage{
    static {
        logger = LogManager.getLogger(HomePage.class);
    }

    @FindBy(xpath = "//h1[@id='page-title']")
    WebElement pageTitle;

    @FindBy(css = "li.active")
    WebElement welcomeMessage;

    @FindBy(css = "img.user-avatar.rounded-circle[alt='User Avatar']")
    WebElement profileAvatar;

    @FindBy(css = "a.nav-link[href='/bank/user/delete-data']")
    WebElement deleteDataOption;

    @FindBy(id = "savings-menu")
    WebElement savingMenu;

    @FindBy(id = "view-savings-menu-item")
    WebElement viewSavingsMenu;

    @FindBy(id = "new-savings-menu-item")
    WebElement createSavingMenu;

    @FindBy(id = "transfer-menu-item")
    WebElement transferMenu;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded(){
        boolean isLoaded = isLoaded(pageTitle) && isLoaded(welcomeMessage) && isLoaded(profileAvatar) && isLoaded(savingMenu);
        logger.trace("isLoaded=" + isLoaded);
        return isLoaded;
    }

    public void deleteData(){
        logger.trace("profileAvatar.click");
        profileAvatar.click();

        logger.trace("deleteDataOption.click");
        deleteDataOption.click();

        assertEquals("Dashboard", pageTitle.getText());
        assertTrue(driver.getCurrentUrl().contains("/bank/home"));
    }

    public ViewSavingsPage gotoViewSavings(){
        logger.trace("savingMenu.click");
        savingMenu.click();

        logger.trace("viewSavingsMenu.click");
        viewSavingsMenu.click();

        return new ViewSavingsPage(driver);
    }

    public CreateSavingsPage gotoCreateSavings(){
        logger.trace("savingMenu.click");
        savingMenu.click();

        logger.trace("createSavingMenu.click");
        createSavingMenu.click();

        return new CreateSavingsPage(driver);
    }

    public TransferPage gotoTransferPage(){
        logger.trace("transferMenu.click");
        transferMenu.click();

        return new TransferPage(driver);
    }

}
