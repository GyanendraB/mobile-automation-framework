package utills;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits {

    private static final int DEFAULT_TIMEOUT = 15;

    public static WebElement waitForVisible(AppiumDriver driver, WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForClickable(AppiumDriver driver, WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForText(AppiumDriver driver, WebElement element, String text) {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public static void waitForInvisibility(AppiumDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
}
