package photobucket;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AppPage {

    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    @FindBy(xpath = "//h1[contains(@class, 'product-header')]")
    private WebElement productHeader;

    public String getProductHeader() {
        return productHeader.getText().trim();
    }

    public AppPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}
