package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waiter {

    public static void waitElementDisappear(By by, WebDriver driver, int timeout) {
        (new WebDriverWait(driver, timeout)).until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public static void waitElementAppear(By by, WebDriver driver, int timeout) {
        (new WebDriverWait(driver, timeout)).until(ExpectedConditions.visibilityOfElementLocated(by));
    }
}
