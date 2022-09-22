package photobucket;

import manager.Waiter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CanvasPage {

    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    @FindBy(xpath = "//div[contains(text(), 'Text')]/ancestor::a")
    private WebElement addTextButton;

    @FindBy(xpath = "//textarea")
    private WebElement addTextInput;

    @FindBy(xpath = "//span[text()='Save']/ancestor::button")
    private WebElement saveCanvasButton;

    @FindBy(xpath = "//span[contains(text(), 'Account')]/ancestor::a")
    private WebElement profileButton;

    @FindBy(xpath = "//a[contains(text(), 'My Projects')]")
    private WebElement projectsButton;

    private final String myProjectsTitlePath = "//div[contains(@class, 'title') and contains(text(), 'Projects')]";
    private final String projectSavingProgressPath = "//div[contains(@class, 'toast')]/div[contains(@class, 'success')]";

    public CanvasPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public CanvasPage addText(String text) {
        addTextButton.click();
        addTextInput.sendKeys(text);
        saveCanvasButton.click();

        Waiter.waitElementDisappear(By.xpath(projectSavingProgressPath), driver, 50);

        return this;
    }

    public MyProjectsPage openMyProjectsPage() {

        profileButton.click();
        try {
            projectsButton.click();
        } catch (UnhandledAlertException e) {
            try {
                Alert alert = driver.switchTo().alert();
                alert.accept();
                projectsButton.click();
            } catch (NoAlertPresentException e2) {
                e2.printStackTrace();
            }
        }

        Waiter.waitElementAppear(By.xpath(myProjectsTitlePath), driver, 40);

        return new MyProjectsPage(driver);
    }
}
