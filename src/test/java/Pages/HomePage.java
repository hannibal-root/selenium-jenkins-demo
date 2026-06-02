package Pages;


import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    static {
        logger = LogManager.getLogger(HomePage.class);
    }

    @FindBy(id = "home-menu-item")
    WebElement homeMenu;

    @FindBy(id = "menuToggle")
    WebElement menu;

    //bejelentkezés gomb
    @FindBy(id = "searchLocations")
    WebElement searchButton;

    @FindBy(xpath = "//img[@src=\"/bank/images/admin.jpg\"]")
    WebElement profileAvatar;

    @FindBy(css = "a.nav-link[href='/bank/user/delete-data']")
    WebElement deleteData;

    @FindBy(id = "savings-menu")
    WebElement savingsMenu;

    @FindBy(id = "view-savings-menu-item")
    WebElement viewSavings;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        boolean loaded = isLoaded(homeMenu)
                && isLoaded(menu)
                && isLoaded(searchButton)
                && isLoaded(profileAvatar);

        logger.trace("HomePage isLoaded=" + loaded);
        return loaded;
    }

    public void deleteData(){
        logger.trace("Click profileAvatar");
        profileAvatar.click();

        logger.trace("Click DeleteData");
        deleteData.click();
    }

    public void navigateViewSavings() {
        logger.trace("Click Savings menu");
        savingsMenu.click();

        logger.trace("Click ViewSavings menu");
        viewSavings.click();
    }
}
