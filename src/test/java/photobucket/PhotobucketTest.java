package photobucket;

import core.BaseChromeTest;
import core.BaseFirefoxTest;
import manager.Checker;
import manager.DriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import reader.ConfigReader;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PhotobucketTest implements BaseChromeTest, BaseFirefoxTest {

    private ArrayList<MainPage> mainPages = new ArrayList<>();

    private final String feedbackPath = "//button[contains(text(), 'later')]";
    private final String cookiePath = "//a[contains(text(), 'Got it')]";

    private void closeExcessWindows(MainPage page) {
        if (Checker.isElementPresent(By.xpath(feedbackPath), page.getDriver())) {
            page.closeFeedbackWindow();
        }
        if (Checker.isElementPresent(By.xpath(cookiePath), page.getDriver())) {
            page.acceptCookie();
        }
    }

    @BeforeEach
    public void setUpMainPage() {
        DriverManager.getDrivers().forEach(driver -> {
            MainPage mainPage = new MainPage(driver);
            mainPages.add(mainPage);
            closeExcessWindows(mainPage);
        });
    }

    @AfterEach
    public void resetSettings() {
        mainPages.clear();
    }

    @Test
    public void yearlySubscriptionCorrectCell() {
        mainPages.forEach(page -> assertEquals(64.68, Double.parseDouble(page
                .makeYearlySubscription()
                .getTotalCell())));
    }

    @Test
    public void monthlySubscriptionCorrectCell() {
        mainPages.forEach(page -> assertEquals(5.99, Double.parseDouble(page
                .makeMonthlySubscription()
                .getTotalCell())));

    }

    @Test
    public void appPageCorrectAppName() {
        mainPages.forEach(page -> {
            AppPage appPage = page.openAppPage();
            assertTrue(page.getDriver().getCurrentUrl().contains(ConfigReader.APPLE_URL));
            assertTrue(appPage.getProductHeader().contains(ConfigReader.APP_NAME));
        });
    }

    @Test
    public void createCanvasCorrectTools() {
        mainPages.forEach(page -> {
            ShopPage shopPage = page.openShopPage();
            assertTrue(page.getDriver().getCurrentUrl().contains(ConfigReader.SHOP_URL));

            MyProjectsPage myProjectsPage = shopPage.openEditCanvasPage().addText(ConfigReader.CANVAS_TEXT).openMyProjectsPage();
            assertTrue(myProjectsPage.isExistProject(ConfigReader.PROJECT_SIZE, ConfigReader.PROJECT_NAME, ConfigReader.PROJECT_STYLE));
        });
    }

    @Test
    public void savePhotoCorrectName() {
        mainPages.forEach(page -> {
            MyPhotosPage myPhotosPage = page.openPhotoBooksPage().openMyPhotosPage().uploadPhoto(ConfigReader.PHOTO_NAME, ConfigReader.PHOTO_SEARCH);
            assertTrue(myPhotosPage.isPhotoUploaded(ConfigReader.PHOTO_NAME));
        });
    }

    @Test
    public void sortPhotosByTimeCorrectOrder() {
        mainPages.forEach(page -> {
            MyPhotosPage myPhotosPage = page
                    .openPhotoBooksPage()
                    .openMyPhotosPage()
                    .uploadPhoto(ConfigReader.SORT_FIRST_PHOTO_NAME, ConfigReader.SORT_FIRST_PHOTO_SEARCH)
                    .uploadPhoto(ConfigReader.SORT_SECOND_PHOTO_NAME, ConfigReader.SORT_SECOND_PHOTO_SEARCH)
                    .sortPhotosOldestFirst();
            assertEquals(myPhotosPage.getFirstPhotoName(), ConfigReader.SORT_FIRST_PHOTO_NAME);
        });
    }

    @Test
    public void sortPhotosByNameCorrectOrder() {
        mainPages.forEach(page -> {
            MyPhotosPage myPhotosPage = page
                    .openPhotoBooksPage()
                    .openMyPhotosPage()
                    .uploadPhoto(ConfigReader.SORT_FIRST_PHOTO_NAME, ConfigReader.SORT_FIRST_PHOTO_SEARCH)
                    .uploadPhoto(ConfigReader.SORT_SECOND_PHOTO_NAME, ConfigReader.SORT_SECOND_PHOTO_SEARCH)
                    .sortPhotosByNameAZ();
            assertEquals(myPhotosPage.getFirstPhotoName(), ConfigReader.SORT_FIRST_PHOTO_NAME);
        });
    }

    @Test
    public void removePhotoCorrectName() {
        mainPages.forEach(page -> {
            MyPhotosPage myPhotosPage = page
                    .openPhotoBooksPage()
                    .openMyPhotosPage()
                    .uploadPhoto(ConfigReader.PHOTO_NAME, ConfigReader.PHOTO_SEARCH)
                    .deletePhoto(ConfigReader.PHOTO_NAME);

            assertFalse(myPhotosPage.isPhotoUploaded(ConfigReader.PHOTO_NAME));
        });
    }
}
