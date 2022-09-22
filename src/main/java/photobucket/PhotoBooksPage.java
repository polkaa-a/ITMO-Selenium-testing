package photobucket;

import manager.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PhotoBooksPage {

    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    private final String myPhotosButtonPath = "//a[contains(text(), 'Photos') and contains(text(), 'My')]";

    @FindBy(xpath = myPhotosButtonPath)
    private WebElement myPhotosButton;

    public PhotoBooksPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public MyPhotosPage openMyPhotosPage() {
        Waiter.waitElementAppear(By.xpath(myPhotosButtonPath), driver, 40);
        myPhotosButton.click();
        return new MyPhotosPage(driver);
    }

}
