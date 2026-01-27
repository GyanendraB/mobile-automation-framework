package utills;

import driver.DriverFactory;
import driver.DriverManager;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SmartGestures {

    private static AppiumDriver driver() {
        return DriverManager.getDriver();
    }

    /* ---------------- ELEMENT SWIPE (LATEST) ---------------- */

    public static void swipeElementLeft(WebElement element) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("elementId", element);
            params.put("direction", "left");
            params.put("percent", 0.75);

            ((JavascriptExecutor) driver())
                    .executeScript("mobile: swipeGesture", params);

        } catch (Exception e) {
            // 🔁 Fallback for safety
            swipeElementLeftW3C(element);
        }
    }

    /* ---------------- FALLBACK (W3C) ---------------- */

    private static void swipeElementLeftW3C(WebElement element) {

        var rect = element.getRect();

        int startX = rect.getX() + (int)(rect.getWidth() * 0.8);
        int endX   = rect.getX() + (int)(rect.getWidth() * 0.2);
        int y      = rect.getY() + rect.getHeight() / 2;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(
                Duration.ZERO, PointerInput.Origin.viewport(), startX, y));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(
                Duration.ofMillis(600),
                PointerInput.Origin.viewport(), endX, y));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver().perform(Collections.singletonList(swipe));
    }
}
