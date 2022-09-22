package photobucket;

import manager.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends DriverManager {

    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    @FindBy(xpath = "//button[contains(text(), 'Year')]")
    private WebElement yearlyButton;

    @FindBy(xpath = "//button[contains(text(), 'Month')]")
    private WebElement monthlyButton;

    @FindBy(xpath = "//a[contains(@data-test, 'lite')]")
    private WebElement litePurchaseButton;

    @FindBy(xpath = "//a[contains(text(), 'Download') and contains(text(), 'iOS')]")
    private WebElement appDownloadButton;

    @FindBy(xpath = "//p[contains(text(), 'Canvas')]/ancestor::a")
    private WebElement openCanvasShopButton;

    @FindBy(xpath = "//p[contains(text(), 'Books')]/ancestor::a")
    private WebElement openPhotoBooksShopButton;

    @FindBy(xpath = "//button[contains(text(), 'later')]")
    private WebElement closeFeedbackWindowButton;

    @FindBy(xpath = "//a[contains(text(), 'Got it')]")
    private WebElement acceptCookieButton;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public PurchasePage makeYearlySubscription() {
        yearlyButton.click();
        waitPurchaseButtonToBeClickable();
        return new PurchasePage(driver);
    }

    public PurchasePage makeMonthlySubscription() {
        monthlyButton.click();
        waitPurchaseButtonToBeClickable();
        return new PurchasePage(driver);
    }

    public AppPage openAppPage() {
        appDownloadButton.click();
        return new AppPage(driver);
    }

    public ShopPage openShopPage() {
        (new WebDriverWait(driver, 50))
                .until(ExpectedConditions.elementToBeClickable(openCanvasShopButton)).click();
        return new ShopPage(driver);
    }

    public PhotoBooksPage openPhotoBooksPage() {
        (new WebDriverWait(driver, 50))
                .until(ExpectedConditions.elementToBeClickable(openPhotoBooksShopButton)).click();
        return new PhotoBooksPage(driver);
    }

    public MainPage closeFeedbackWindow() {
        (new WebDriverWait(driver, 50))
                .until(ExpectedConditions.elementToBeClickable(closeFeedbackWindowButton)).click();
        return this;
    }

    public MainPage acceptCookie() {
        (new WebDriverWait(driver, 50))
                .until(ExpectedConditions.elementToBeClickable(acceptCookieButton)).click();
        return this;
    }

    private void waitPurchaseButtonToBeClickable() {
        try {
            (new WebDriverWait(driver, 50))
                    .until(ExpectedConditions.elementToBeClickable(litePurchaseButton)).click();
        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
            (new WebDriverWait(driver, 50))
                    .until(ExpectedConditions.elementToBeClickable(litePurchaseButton)).click();
        }
    }
}
