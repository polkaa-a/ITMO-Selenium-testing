package manager;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class DriverManager {
    protected static List<WebDriver> drivers = new ArrayList<>();

    public static void setDriver(WebDriver webDriver) {
        drivers.add(webDriver);
    }

    public static void closeDrivers() {
        for (WebDriver driver : drivers) {
            driver.close();
        }
        drivers.clear();
    }

    public static List<WebDriver> getDrivers() {
        return drivers;
    }
}
