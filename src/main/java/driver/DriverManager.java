package driver;

import io.appium.java_client.AppiumDriver;

public class DriverManager {

    private static final ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    public static void setDriver(AppiumDriver driverInstance) {
        driver.set(driverInstance);
    }

    public static AppiumDriver getDriver() {
        return driver.get();
    }

    public static void unload() {
        driver.remove();
    }
}
