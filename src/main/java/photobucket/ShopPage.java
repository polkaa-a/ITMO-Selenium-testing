package photobucket;

import manager.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShopPage {

    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    private final String chooseHorizontalCanvasButtonPath = "//div[contains(text(), '7x5')]/ancestor::a";
    private final String choosePhotoWrapButtonPath = "//div[text()='Photo Wrap']/ancestor::a";

    @FindBy(xpath = "//a[contains(text(), 'Wrapped')]/..//a[text()='5x7']")
    private WebElement openWrappedCanvasButton;

    @FindBy(xpath = chooseHorizontalCanvasButtonPath)
    private WebElement chooseHorizontalCanvasButton;

    @FindBy(xpath = choosePhotoWrapButtonPath)
    private WebElement choosePhotoWrapButton;

    @FindBy(xpath = "//span[text()='Next']/ancestor::a")
    private WebElement nextButton;


    public ShopPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public CanvasPage openEditCanvasPage() {
        openWrappedCanvasButton.click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        Waiter.waitElementAppear(By.xpath(chooseHorizontalCanvasButtonPath), driver, 40);
        js.executeScript("arguments[0].click();", chooseHorizontalCanvasButton);
        Waiter.waitElementAppear(By.xpath(choosePhotoWrapButtonPath), driver, 40);
        js.executeScript("arguments[0].click();", choosePhotoWrapButton);

        nextButton.click();

        return new CanvasPage(driver);
    }

}
