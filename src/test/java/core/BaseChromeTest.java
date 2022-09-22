package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import manager.DriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import reader.ConfigReader;

public interface BaseChromeTest {
    @BeforeEach
    default void setUpChrome() {
        WebDriverManager.chromedriver().setup();
        WebDriver chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();
        DriverManager.setDriver(chromeDriver);
        chromeDriver.get(ConfigReader.URL);
    }

    @AfterEach
    default void tearDownChrome() {
        DriverManager.closeDrivers();
    }
}
