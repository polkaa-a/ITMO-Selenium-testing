package photobucket;

import manager.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MyPhotosPage {

    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    @FindBy(xpath = "//span[contains(text(), 'Upload')]/ancestor::button[contains(@class, 'primary')]")
    private WebElement uploadPhotoButton;

    @FindBy(xpath = "//h4[contains(text(), 'Cloud')]")
    private WebElement cloudStorageButton;

    @FindBy(xpath = "//span[contains(@class, 'unsplash')]")
    private WebElement unsplashLoadButton;

    @FindBy(xpath = "//input[contains(@placeholder, 'Search') and contains(@placeholder, 'photos')]")
    private WebElement inputTextSearch;

    @FindBy(xpath = "//div[contains(@class, 'search')]/ancestor::button")
    private WebElement searchButton;

    @FindBy(xpath = "//span[contains(text(), 'Selected')]/ancestor::span[contains(@class, 'button')]")
    private WebElement selectPhotoButton;

    @FindBy(xpath = "//span[contains(text(), 'Upload')]/ancestor::span[contains(@class, 'button')]")
    private WebElement uploadSelectedPhotoButton;

    @FindBy(xpath = "//ul[contains(@class, 'row')]/li")
    private List<WebElement> listOfPhotos;

    @FindBy(xpath = "//button[contains(@class, 'delete')]")
    private WebElement deleteButton;

    @FindBy(xpath = "//button[contains(text(), 'Delete Photos')]")
    private WebElement confirmationDeleteButton;

    @FindBy(xpath = "//button[contains(@class, 'needsclick') and contains(@class, 'plain')]")
    private WebElement sortButton;

    @FindBy(xpath = "//span[contains(text(), 'Oldest')]")
    private WebElement sortOldestFirstButton;

    @FindBy(xpath = "//ul[contains(@class, 'row')]/li[1]")
    private WebElement firstElementAfterSorting;

    @FindBy(xpath = "//span[contains(text(), 'Name (A-Z)')]")
    private WebElement sortNameAZButton;

    private final String loadingElementPath = "//div[contains(@class, 'fsp-loading')]";

    public MyPhotosPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public MyPhotosPage uploadPhoto(String photoName, String searchText) {

        String photoToUploadPath = "//div[contains(@title, '" + photoName + "')]";
        String uploadedPhotoPath = "//img[contains(@alt, '" + photoName + "')]";

        Waiter.waitElementAppear(By.xpath("//span[contains(text(), 'Upload')]/ancestor::button[contains(@class, 'primary')]"), driver, 50);
        uploadPhotoButton.click();

        cloudStorageButton.click();
        unsplashLoadButton.click();
        inputTextSearch.sendKeys(searchText);
        searchButton.click();

        Waiter.waitElementDisappear(By.xpath(loadingElementPath), driver, 15);
        WebElement photoToUpload = By.xpath(photoToUploadPath).findElement(driver);

        photoToUpload.click();
        selectPhotoButton.click();
        uploadSelectedPhotoButton.click();

        Waiter.waitElementAppear(By.xpath(uploadedPhotoPath), driver, 120);

        return this;
    }

    public MyPhotosPage deletePhoto(String photoName) {

        String photoToDeletePath = "//img[contains(@alt, '" + photoName + "' )]";

        WebElement photoToDelete = By.xpath(photoToDeletePath).findElement(driver);
        Waiter.waitElementAppear(By.xpath(photoToDeletePath), driver, 15);

        photoToDelete.click();
        deleteButton.click();
        confirmationDeleteButton.click();

        Waiter.waitElementDisappear(By.xpath(photoToDeletePath), driver, 15);

        return this;
    }

    public MyPhotosPage sortPhotosOldestFirst() {
        sortButton.click();
        sortOldestFirstButton.click();
        return this;
    }

    public MyPhotosPage sortPhotosByNameAZ() {
        sortButton.click();
        sortNameAZButton.click();
        return this;
    }

    public String getFirstPhotoName() {
        return firstElementAfterSorting.findElement(By.xpath("//img[contains(@class, 'preview')]")).getAttribute("alt");
    }

    public boolean isPhotoUploaded(String name) {

        String photoNamePath = "//span[contains(@class, 'photo-name')]";

        for (WebElement photo : listOfPhotos) {
            if (photo.findElement(By.xpath(photoNamePath)).getText().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
