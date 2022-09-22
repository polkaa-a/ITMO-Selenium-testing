package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import manager.DriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import reader.ConfigReader;

public interface BaseFirefoxTest {
    @BeforeEach
    default void setUpFirefox() {
        WebDriverManager.firefoxdriver().setup();
        WebDriver firefoxDriver = new FirefoxDriver();
        firefoxDriver.manage().window().maximize();
        DriverManager.setDriver(firefoxDriver);
        firefoxDriver.get(ConfigReader.URL);
    }

    @AfterEach
    default void tearDownFirefox() {
        DriverManager.closeDrivers();
    }
}
