package photobucket;

import manager.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PurchasePage {

    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    private final String totalCellPath = "//div[@class='detailscell' and contains(text(), '$')]";

    @FindBy(xpath = totalCellPath)
    private WebElement totalCell;

    public String getTotalCell() {
        Waiter.waitElementAppear(By.xpath(totalCellPath), driver, 40);
        return totalCell.getText().substring(1);
    }

    public PurchasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

}
