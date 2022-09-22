package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Checker {

    public static boolean isElementPresent(By by, WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        List<WebElement> list = driver.findElements(by);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        try {
            Waiter.waitElementAppear(by, driver, 40);
        } catch (TimeoutException e) {
            return false;
        }

        return list.size() > 0;
    }
}
