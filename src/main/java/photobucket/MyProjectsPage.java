package photobucket;

import manager.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MyProjectsPage {

    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    private final String listOfProjectsPath = "//div[@class='project-items']//li[contains(@class, 'project-item')]";

    @FindBy(xpath = listOfProjectsPath)
    private List<WebElement> listOfProjects;

    public MyProjectsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public boolean isExistProject(String size, String name, String style) {

        Waiter.waitElementAppear(By.xpath(listOfProjectsPath), driver, 30);

        for (WebElement project : listOfProjects) {
            String projectStyle = project.findElement(By.xpath("//div[contains(text(), 'Style')]/../div[@class='text']")).getText().trim();
            String[] projectInformation = project.findElement(By.xpath("//div[@class='project-name']/a")).getText().split(" ");

            String projectSize = projectInformation[0].trim();
            String projectName = projectInformation[1].trim();

            if (size.equals(projectSize) & name.equals(projectName) & style.equals(projectStyle)) {
                return true;
            }
        }
        return false;
    }
}
